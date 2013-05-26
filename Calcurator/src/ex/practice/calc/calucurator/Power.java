package ex.practice.calc.calucurator;

public class Power extends Calc {

	public Power(String value) {
		super(value);
	}

	@Override
	protected String _execute(String value) {
		return String.valueOf(Math.pow(Double.valueOf(getValue()),
				Double.valueOf(value)));
	}

}
