package ex.practice.calc.validator;

/**
 * 最後が大文字か判定
 *
 * @author mitsunorinaraoka
 *
 */
public class LastCharIsCapitalChar extends Validator {

	@Override
	protected boolean valid(String target) {
		return target.matches(".*[A-Z]+$");
	}

}
