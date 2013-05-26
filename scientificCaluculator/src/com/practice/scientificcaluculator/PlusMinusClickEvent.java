package com.practice.scientificcaluculator;

import android.view.View;
import ex.practice.calc.calucurator.Calcurator.CODE;
import ex.practice.calc.validator.CanPutMinusCodeValidator;

final class PlusMinusClickEvent implements IClickEvent {

	@Override
	public void registClickEvent(final MainActivity activity) {
		// ±キー設定
		activity.findViewById(R.id.key_plusminus).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						if (isPlusPuttable(activity)) {
							activity.calcView.write(activity.inputData.del(1)
									.get());
						} else if (isMinusPuttable(activity)) {
							activity.calcView.write(activity.inputData.set(
									CODE.MINUS.disp()).get());
						}
					}
				});
	}

	private boolean isPlusPuttable(final MainActivity activity) {
		String target = activity.inputData.get();
		if (target.length() > 0) {
			return CODE.MINUS.disp().equals(
					target.substring(target.length() - 1));
		} else {
			return false;
		}
	}

	private boolean isMinusPuttable(final MainActivity activity) {
		String target = activity.inputData.get();
		if (target.length() > 0) {
			return new CanPutMinusCodeValidator().isCongruence(target);
		} else {
			return true;
		}
	}

}
