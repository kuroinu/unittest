package com.practice.scientificcaluculator;

import android.view.View;
import ex.practice.calc.calucurator.Calcurator.BLACKET;

final class LeftBlacketClickEvent implements IClickEvent {

	@Override
	public void registClickEvent(final MainActivity activity) {
		// (キー設定
		activity.findViewById(R.id.key_leftblacket).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						if (activity.inputData.blacketCnt <= 0) {
							activity.calcView
									.write(activity.inputData.set(
									BLACKET.LEFT
													.getValue()).get());
							activity.inputData.blacketCnt++;
						}
					}
				});
	}

}
