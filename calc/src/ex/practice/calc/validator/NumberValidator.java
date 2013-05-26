package ex.practice.calc.validator;

public class NumberValidator extends Validator {

	@Override
	protected boolean valid(String target) {
		return target.matches("[0-9]+");
	}

}
