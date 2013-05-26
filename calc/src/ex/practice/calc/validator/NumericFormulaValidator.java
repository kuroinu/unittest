package ex.practice.calc.validator;

public class NumericFormulaValidator extends Validator {

	@Override
	protected boolean valid(String target) {
		return target.matches("^[0-9]+.+[0-9]+$");
	}

}
