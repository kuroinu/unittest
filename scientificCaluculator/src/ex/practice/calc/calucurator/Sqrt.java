package ex.practice.calc.calucurator;

public class Sqrt extends Calc {
	Sqrt(String value) {
		super(value);
	}

	@Override
	protected String _execute(String value) {
		return Double.toString(Math.sqrt(Double.valueOf(getValue())));
	}

}
