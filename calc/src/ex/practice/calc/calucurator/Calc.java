package ex.practice.calc.calucurator;

import java.math.BigDecimal;

public abstract class Calc {
	private Calc nextElement;
	private String value = "0";

	Calc(String value) {
		this.setValue(value);
	}

	public String interpret() {
		if (hasNextElement()) {
			nextElement.setValue(_execute(nextElement.getValue()));
			return getIntegralPart(nextElement.interpret());
		} else {
			return getIntegralPart(value);
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Calc setNextElement(Calc nextElement) {
		if (hasNextElement()) {
			this.nextElement.setNextElement(nextElement);
		} else {
			this.nextElement = nextElement;
		}
		return this;
	}

	abstract protected String _execute(String value);

	private Boolean hasNextElement() {
		return nextElement != null;
	}

	private String getIntegralPart(String target) {
		return target.replaceAll("(\\d+)\\.0+$", "$1");
	}

	protected BigDecimal transValue(String value) {
		return new BigDecimal(value);
	}
}
