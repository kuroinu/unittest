package ex.practice.calc.calucurator;

import ex.practice.calc.calucurator.Calcurator.OPERATOR;

public class BlacketCalcurator extends AbstractSubCalcurator {

	@Override
	protected String _calcurate(String target) {
		String element = target;
		element = convertPlainFormula(element);
		return new Calcurator().caluculate(element);
	}

	@Override
	protected boolean isTarget(String that) {
		return that.matches("\\([M0-9as×÷]+\\)");
	}

	/**
	 * （）に囲まれた式を平文に変換
	 *
	 * @param target
	 * @return
	 */
	private String convertPlainFormula(String target) {
		return target
				.replaceFirst("\\(([M0-9as×÷]+)\\)", "$1")
				.replace(OPERATOR.ADD.getBrancketVal(), OPERATOR.ADD.getValue())
				.replace(OPERATOR.SUBTRACT.getBrancketVal(),
						OPERATOR.SUBTRACT.getValue());
	}

}
