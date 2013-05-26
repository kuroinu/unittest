package ex.practice.calc.calucurator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calcurator {
	public enum OPERATOR {
		ADD("+") {
			public Calc createFormula(String value) {
				return new Addition(value);
			}
		},
		SUBTRACT("-") {
			public Calc createFormula(String value) {
				return new Subtraction(value);
			}
		},
		MULTI("×") {
			public Calc createFormula(String value) {
				return new Multiplication(value);
			}
		},
		DIVIDE("÷") {
			public Calc createFormula(String value) {
				return new Division(value);
			}
		},
		EQUALS("=") {
			public Calc createFormula(String value) {
				return new Equals(value);
			}
		};
		private String value;

		private OPERATOR(String n) {
			this.value = n;
		}

		public String getValue() {
			return this.value;
		}

		public Calc createFormula(String value) {
			return null;
		}

	}

	public String caluculate(String evalTarget) {
		return calcFactory(evalTarget).interpret();
	}

	private Calc calcFactory(String originalString) {
		OPERATOR[] operators = pickOutLowPriorityOperators(originalString)
				.toArray(new OPERATOR[0]);
		String[] elements = pickOutFirstInterpretNumbers(originalString)
				.toArray(new String[0]);
		Calc calc = null;
		for (int i = 0; i < elements.length; i++) {
			String element = elements[i];
			if (containsPrioritOperator(elements[i])) {
				element = calcMultiAndDivide(element);
			}
			Calc tmpCalc = operators[i].createFormula(element);
			if (calc == null) {
				calc = tmpCalc;
			} else {
				calc.setNextElement(tmpCalc);
			}
		}
		return calc;
	}

	private List<OPERATOR> pickOutLowPriorityOperators(String originalString) {
		List<OPERATOR> list = new ArrayList<OPERATOR>();
		Matcher m = Pattern.compile("([" + lowPriorityOperators() + "])")
				.matcher(originalString);
		while (m.find()) {
			list.add(m.group(1).equals(OPERATOR.ADD.getValue()) ? OPERATOR.ADD
					: OPERATOR.SUBTRACT);
		}
		list.add(OPERATOR.EQUALS);
		return list;
	}

	private List<OPERATOR> pickOutHighPriorityOperators(String originalString) {
		List<OPERATOR> list = new ArrayList<OPERATOR>();
		Matcher m = Pattern.compile("([" + highPriorityOperators() + "])")
				.matcher(originalString);
		while (m.find()) {
			list.add(m.group(1).equals(OPERATOR.MULTI.getValue()) ? OPERATOR.MULTI
					: OPERATOR.DIVIDE);
		}
		list.add(OPERATOR.EQUALS);
		return list;
	}

	private String lowPriorityOperators() {
		return "\\" + OPERATOR.ADD.getValue() + "\\"
				+ OPERATOR.SUBTRACT.getValue();
	}

	private String highPriorityOperators() {
		return OPERATOR.MULTI.getValue() + OPERATOR.DIVIDE.getValue();
	}

	private List<String> pickOutFirstInterpretNumbers(String target) {
		return pickOutInterpretNumbers(target.split("(["
				+ lowPriorityOperators() + "])"));
	}

	private List<String> pickOutSecondInterpretNumbers(String target) {
		return pickOutInterpretNumbers(target.split("(["
				+ highPriorityOperators() + "])"));
	}

	private List<String> pickOutInterpretNumbers(String[] target) {
		List<String> list = new ArrayList<String>();
		for (String str : target) {
			list.add(str);
		}
		return list;
	}

	private Boolean containsPrioritOperator(String target) {
		return target.matches(".*[" + highPriorityOperators() + "].*");
	}

	private String calcMultiAndDivide(String equation) {
		OPERATOR[] operators = pickOutHighPriorityOperators(equation).toArray(
				new OPERATOR[0]);
		String[] elements = pickOutSecondInterpretNumbers(equation).toArray(
				new String[0]);
		Calc calc = null;
		for (int i = 0; i < elements.length; i++) {
			Calc tmpCalc = operators[i].createFormula(elements[i]);
			if (calc == null) {
				calc = tmpCalc;
			} else {
				calc.setNextElement(tmpCalc);
			}
		}
		return calc.interpret();
	}

}
