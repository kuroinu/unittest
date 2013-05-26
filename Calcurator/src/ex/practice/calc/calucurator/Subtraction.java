package ex.practice.calc.calucurator;

/**
 * 除算
 * @author mitsunorinaraoka
 *
 */
final public class Subtraction extends Calc {

	Subtraction(String value) {
		super(value);
	}

	@Override
	protected String _execute(String value) {
		return transValue(getValue()).subtract(transValue(value))
				.toPlainString();
	}

}
