package ex.practice.calc.validator;

public class LastCharIsNumberValidator extends Validator {

	@Override
	protected boolean valid(String target) {
		return target.matches(".*[0-9]+$");
	}

}
