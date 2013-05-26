package ex.practice.calc.calucurator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ex.practice.calc.calucurator.Calcurator.CODE;
import ex.practice.calc.calucurator.Calcurator.OPERATOR;

public class MultiAndDivideCalcurator extends AbstractSubCalcurator {

	@Override
	protected String _calcurate(String target) {
		OPERATOR[] operators = pickOutHighPriorityOperators(target).toArray(
				new OPERATOR[0]);
		String[] elements = pickOutSecondInterpretNumbers(target).toArray(
				new String[0]);
		Calc calc = null;
		for (int i = 0; i < elements.length; i++) {
			String element = elements[i];
			// ()を計算する
			element = new BlacketCalcurator().calcurate(element);
			Calc tmpCalc = operators[i].createFormula(CODE.MINUS
					.viewFromCode(element));
			if (calc == null) {
				calc = tmpCalc;
			} else {
				calc.join(tmpCalc);
			}
		}
		return calc.interpret();
	}

	@Override
	protected boolean isTarget(String that) {
		return that.matches(".*[" + highPriorityOperators() + "].*");
	}

	/**
	 * ×÷%^の文字列取得
	 *
	 * @return
	 */
	private String highPriorityOperators() {
		return OPERATOR.MULTI.getValue() + OPERATOR.DIVIDE.getValue()
				+ OPERATOR.MOD.getValue() + OPERATOR.POW.getValue();
	}

	/**
	 * 優先順位高で分割した演算子
	 *
	 * @param originalString
	 * @return
	 */
	private List<OPERATOR> pickOutHighPriorityOperators(String originalString) {
		List<OPERATOR> list = new ArrayList<OPERATOR>();
		Matcher m = Pattern.compile("([" + highPriorityOperators() + "])")
				.matcher(originalString);
		while (m.find()) {
			OPERATOR o;
			if (m.group(1).equals(OPERATOR.MULTI.getValue())) {
				o = OPERATOR.MULTI;
			} else if (m.group(1).equals(OPERATOR.DIVIDE.getValue())) {
				o = OPERATOR.DIVIDE;
			} else if (m.group(1).equals(OPERATOR.MOD.getValue())) {
				o = OPERATOR.MOD;
			} else {
				o = OPERATOR.POW;
			}
			list.add(o);
		}
		list.add(OPERATOR.EQUALS);
		return list;
	}

	/**
	 * 文字列を優先順位の高い演算子で分割
	 *
	 * @param target
	 * @return
	 */
	private List<String> pickOutSecondInterpretNumbers(String target) {
		return PickOutFromTarget.newInstance().numbers(
				target.split("([" + highPriorityOperators() + "])"));
	}

}
