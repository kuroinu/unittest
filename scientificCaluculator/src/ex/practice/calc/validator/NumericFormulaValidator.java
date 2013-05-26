package ex.practice.calc.validator;

/**
 * 数値形式チェック
 * @author mitsunorinaraoka
 *
 */
final public class NumericFormulaValidator extends Validator {

	@Override
	protected boolean valid(String target) {
		return target.matches("^[0-9]+.+[0-9]+$");
	}

}
