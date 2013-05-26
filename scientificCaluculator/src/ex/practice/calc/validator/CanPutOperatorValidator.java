package ex.practice.calc.validator;

import ex.practice.calc.calucurator.Calcurator.BLACKET;

public class CanPutOperatorValidator extends Validator {

	@Override
	protected boolean valid(String target) {
		return isLastCharNumber(target) || isLastCharBackPuttable(target);
	}

	private boolean isLastCharNumber(String target) {
		return new LastCharIsNumberValidator().isCongruence(target);
	}

	private boolean isLastCharBackPuttable(String target) {
		if (target.length() > 1) {
			return BLACKET.RIGHT.getValue().equals(
					target.substring(target.length() - 1));
		} else {
			return true;
		}
	}
}
