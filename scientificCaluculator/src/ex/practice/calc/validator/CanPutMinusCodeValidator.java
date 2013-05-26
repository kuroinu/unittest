package ex.practice.calc.validator;

import ex.practice.calc.calucurator.Calcurator.BLACKET;
import ex.practice.calc.calucurator.Calcurator.OPERATOR;

public class CanPutMinusCodeValidator extends Validator {

	@Override
	protected boolean valid(String target) {
		if (target.length() > 0) {
			String lastChar = target.substring(target.length() - 1);
			return BLACKET.LEFT.getValue().equals(lastChar)
					|| OPERATOR.ADD.getValue().equals(lastChar)
					|| OPERATOR.SUBTRACT.getValue().equals(lastChar)
					|| OPERATOR.DIVIDE.equals(lastChar)
					|| OPERATOR.MULTI.getValue().equals(lastChar);
		} else {
			return true;
		}
	}

}
