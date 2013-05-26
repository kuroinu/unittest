package ex.practice.calc.calucurator;

import java.math.BigDecimal;

/**
 * 余算
 *
 * @author mitsunorinaraoka
 *
 */
final public class Division extends Calc {

	private static final int SCALE = 10;

	Division(String value) {
		super(value);
	}

	@Override
	protected String _execute(String value) {
		return canDivide(value) ? transValue(getValue()).divide(
				transValue(value), SCALE, BigDecimal.ROUND_UP).toPlainString()
				: "0";
	}

	private boolean canDivide(String value) {
		return 0 != Integer.valueOf(value);
	}
}
