package ex.practice.calc.validator;

/**
 * 最後が数字か判定
 * @author mitsunorinaraoka
 *
 */
final public class LastCharIsNumberValidator extends Validator {

	@Override
	protected boolean valid(String target) {
		return target.matches(".*[0-9]+$");
	}

}
