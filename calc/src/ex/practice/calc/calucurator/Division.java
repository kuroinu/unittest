package ex.practice.calc.calucurator;

import java.math.BigDecimal;

public class Division extends Calc {

	Division(String value) {
		super(value);
	}

	@Override
	protected String _execute(String value) {
		return canDivide(value) ? transValue(getValue()).divide(
				transValue(value), 5, BigDecimal.ROUND_UP).toPlainString()
				: "0";
	}

	private boolean canDivide(String value) {
		return 0 != Integer.valueOf(value);
	}
}
