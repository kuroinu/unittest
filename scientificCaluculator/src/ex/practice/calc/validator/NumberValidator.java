package ex.practice.calc.validator;

/**
 * 数値判定
 * @author mitsunorinaraoka
 *
 */
final public class NumberValidator extends Validator {

	@Override
	protected boolean valid(String target) {
		return target.matches("[0-9]+");
	}

}
