package com.practice.scientificcaluculator;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.view.View;
import ex.practice.calc.calucurator.Calcurator.OPERATOR;
import ex.practice.calc.validator.CanPutOperatorValidator;

@SuppressLint("UseSparseArrays")
final class OperatorClickEvent implements IClickEvent {

	/**
	 * 演算子キーのIDマッパー
	 */
	private static final Map<Integer, OPERATOR> OPERATOR_KEYS;
	static {
		OPERATOR_KEYS = new HashMap<Integer, OPERATOR>();
		OPERATOR_KEYS.put(R.id.key_plus, OPERATOR.ADD);
		OPERATOR_KEYS.put(R.id.key_minus, OPERATOR.SUBTRACT);
		OPERATOR_KEYS.put(R.id.key_multi, OPERATOR.MULTI);
		OPERATOR_KEYS.put(R.id.key_divide, OPERATOR.DIVIDE);
		OPERATOR_KEYS.put(R.id.key_mod, OPERATOR.MOD);
		OPERATOR_KEYS.put(R.id.key_power, OPERATOR.POW);
	}

	@Override
	public void registClickEvent(final MainActivity activity) {
		// 演算子キー設定
		for (final Map.Entry<Integer, OPERATOR> e : OPERATOR_KEYS.entrySet()) {
			activity.findViewById(e.getKey()).setOnClickListener(
					new View.OnClickListener() {
						public void onClick(View v) {
							if (isPuttable(activity.inputData.get())) {
								String operator = activity.inputData.blacketCnt > 0 ? e
										.getValue().getBrancketVal() : e
										.getValue().getValue();
								activity.calcView.write(activity.inputData.set(
										operator).get());
							}
						}

						private boolean isPuttable(String target) {
							return target.length() > 0
									&& new CanPutOperatorValidator()
											.isCongruence(target);
						}
					});
		}
	}

}
