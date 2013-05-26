package ex.practice.calc.calucurator;

public class Subtraction extends Calc {

	Subtraction(String value) {
		super(value);
	}

	@Override
	protected String _execute(String value) {
		return transValue(getValue()).subtract(transValue(value))
				.toPlainString();
	}

}
