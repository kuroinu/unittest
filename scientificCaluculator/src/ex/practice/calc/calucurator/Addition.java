package ex.practice.calc.calucurator;

/**
 * 加算
 * @author mitsunorinaraoka
 *
 */
final public class Addition extends Calc {

	Addition(String value) {
		super(value);
	}

	@Override
	protected String _execute(String value) {
		return transValue(getValue()).add(transValue(value)).toPlainString();
	}
}
