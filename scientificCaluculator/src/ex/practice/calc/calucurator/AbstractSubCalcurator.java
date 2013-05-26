package ex.practice.calc.calucurator;

abstract class AbstractSubCalcurator {

	private AbstractSubCalcurator next;

	/**
	 * 計算実施
	 *
	 * @param target
	 * @return
	 */
	String calcurate(String target) {
		String that = target;
		if (isTarget(that)) {
			that = _calcurate(that);
		}
		if (hasNext()) {
			that = next.calcurate(that);
		}
		return that;
	}

	private boolean hasNext() {
		return next != null;
	}

	/**
	 * 計算式同士を連結
	 *
	 * @param next
	 */
	AbstractSubCalcurator join(AbstractSubCalcurator next) {
		if (hasNext()) {
			this.next.join(next);
		} else {
			this.next = next;
		}
		return this;
	}

	/**
	 * 実際の計算処理
	 *
	 * @param target
	 * @return
	 */
	protected abstract String _calcurate(String target);

	/**
	 * 対象をターゲットとするか
	 *
	 * @param that
	 * @return
	 */
	protected abstract boolean isTarget(String that);
}
