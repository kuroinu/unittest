package ex.practice.calc.calucurator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 計算取りまとめ
 *
 * @author mitsunorinaraoka
 *
 */
public class Calcurator {

	/**
	 * 演算子
	 *
	 * @author mitsunorinaraoka
	 *
	 */
	public enum OPERATOR {
		ADD("+", "a") {
			public Calc createFormula(String value) {
				return new Addition(value);
			}
		},
		SUBTRACT("-", "s") {
			public Calc createFormula(String value) {
				return new Subtraction(value);
			}
		},
		MULTI("×", "×") {
			public Calc createFormula(String value) {
				return new Multiplication(value);
			}
		},
		DIVIDE("÷", "÷") {
			public Calc createFormula(String value) {
				return new Division(value);
			}
		},
		MOD("%", "%") {
			public Calc createFormula(String value) {
				return new Mod(value);
			}
		},
		POW("O", "O") {
			public Calc createFormula(String value) {
				return new Power(value);
			}
		},
		EQUALS("=", "=") {
			public Calc createFormula(String value) {
				return new Equals(value);
			}
		};
		private String value;
		private String brancketVal;

		public String getBrancketVal() {
			return brancketVal;
		}

		private OPERATOR(String n, String brancketVal) {
			this.value = n;
			this.brancketVal = brancketVal;
		}

		public String getValue() {
			return this.value;
		}

		public Calc createFormula(String value) {
			return null;
		}

	}

	/**
	 * +-コード
	 *
	 * @author mitsunorinaraoka
	 *
	 */
	public enum CODE {
		MINUS(OPERATOR.SUBTRACT.getValue(), "M"), POWER(
				OPERATOR.POW.getValue(), "^"), LOG(
				PRECALFORMULA.LOG.getValue(), "log");
		private String view;
		private String code;

		CODE(String original, String disp) {
			this.view = original;
			this.code = disp;
		}

		public String original() {
			return view;
		}

		public String disp() {
			return code;
		}

		public String viewFromCode(String target) {
			return target.replaceAll(code, view);
		}

		public String codeFromView(String target) {
			return target.replaceAll(view, code);
		}
	}

	/**
	 * ()を定義
	 *
	 * @author mitsunorinaraoka
	 *
	 */
	public enum BLACKET {
		LEFT("("), RIGHT(")");
		private String value;

		BLACKET(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum PRECALFORMULA {
		ROOT("√"), LOG("L");
		private String value;

		PRECALFORMULA(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 計算実行
	 *
	 * @param evalTarget
	 * @return
	 */
	public String caluculate(String evalTarget) {
		return calcFactory(evalTarget).interpret();
	}

	/**
	 * 計算メソッド組み立て
	 *
	 * @param originalString
	 * @return
	 */
	private Calc calcFactory(String originalString) {
		// 演算子配列
		OPERATOR[] operators = pickOutLowPriorityOperators(originalString)
				.toArray(new OPERATOR[0]);
		// 　数値配列
		String[] elements = pickOutFirstInterpretNumbers(originalString)
				.toArray(new String[0]);
		Calc calc = null;
		for (int i = 0; i < elements.length; i++) {
			String element = elements[i];

			element = highPriorityCalcurate().calcurate(element);

			// 計算式の実施
			Calc tmpCalc = operators[i].createFormula(CODE.MINUS
					.viewFromCode(element));
			if (calc == null) {
				calc = tmpCalc;
			} else {
				calc.join(tmpCalc);
			}
		}
		return calc;
	}

	private AbstractSubCalcurator highPriorityCalcurate() {
		return new BlacketCalcurator().join(new RootCalcurator())
				.join(new LogCalcurator()).join(new MultiAndDivideCalcurator());
	}

	/**
	 * +-で分割
	 *
	 * @param originalString
	 * @return
	 */
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

	/**
	 * +-の文字列取得
	 *
	 * @return
	 */
	private String lowPriorityOperators() {
		return "\\" + OPERATOR.ADD.getValue() + "\\"
				+ OPERATOR.SUBTRACT.getValue();
	}

	/**
	 * 文字列を+-で分割
	 *
	 * @param target
	 * @return
	 */
	private List<String> pickOutFirstInterpretNumbers(String target) {
		return PickOutFromTarget.newInstance().numbers(
				target.split("([" + lowPriorityOperators() + "])"));
	}

}
