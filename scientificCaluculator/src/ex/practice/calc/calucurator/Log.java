package ex.practice.calc.calucurator;

public class Log extends Calc {

	public Log(String value) {
		super(value);
	}

	@Override
	protected String _execute(String value) {
		return Double.toString(Math.log(Double.valueOf(getValue())));
	}

}
