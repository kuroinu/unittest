package ex.practice.calc.calucurator;

public class Mod extends Calc {

	public Mod(String value) {
		super(value);
	}

	@Override
	protected String _execute(String value) {
		return String.valueOf(Double.valueOf(getValue())
				% Double.valueOf(value));
	}

}
