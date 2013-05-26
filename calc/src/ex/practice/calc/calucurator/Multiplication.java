package ex.practice.calc.calucurator;

public class Multiplication extends Calc {

	Multiplication(String value) {
		super(value);
	}

	@Override
	protected String _execute(String value) {
		return transValue(getValue()).multiply(transValue(value))
				.toPlainString();
	}

}
