package ex.practice.calc.calucurator;

/**
 * 計算実行
 * @author mitsunorinaraoka
 *
 */
final public class Equals extends Calc {

	Equals(String value) {
		super(value);
	}

	@Override
	protected String _execute(String value) {
		return getValue();
	}

}
