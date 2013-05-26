package ex.practice.calc.calucurator;

/**
 * 乗算
 * @author mitsunorinaraoka
 *
 */
final public class Multiplication extends Calc {

	Multiplication(String value) {
		super(value);
	}

	@Override
	protected String _execute(String value) {
		return transValue(getValue()).multiply(transValue(value))
				.toPlainString();
	}

}
