package ex.practice.calc.calucurator;

import java.math.BigDecimal;

public abstract class Calc {
	private Calc nextElement;
	private String value = "0";

	Calc(String value) {
		this.setValue(value);
	}

	/**
	 * 連結式の計算実行
	 * @return
	 */
	final public String interpret() {
		if (hasNextElement()) {
			nextElement.setValue(_execute(nextElement.getValue()));
			return getIntegralPart(nextElement.interpret());
		} else {
			return getIntegralPart(value);
		}
	}

	/**
	 * 自身の値を取得
	 * @return
	 */
	final public String getValue() {
		return value;
	}

	final public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 次式と連結
	 * @param nextElement
	 * @return
	 */
	final public Calc join(Calc nextElement) {
		if (hasNextElement()) {
			this.nextElement.join(nextElement);
		} else {
			this.nextElement = nextElement;
		}
		return this;
	}

	abstract protected String _execute(String value);

	private Boolean hasNextElement() {
		return nextElement != null;
	}

	/**
	 * 少数部分が0のみの場合は切り捨て
	 * @param target
	 * @return
	 */
	private String getIntegralPart(String target) {
		return target.replaceAll("(\\d+)\\.0+$", "$1");
	}

	/**
	 * BigDecimalに変換
	 * @param value
	 * @return
	 */
	protected BigDecimal transValue(String value) {
		return new BigDecimal(value);
	}
}
