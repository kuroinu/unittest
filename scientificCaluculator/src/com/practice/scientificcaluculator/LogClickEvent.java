package com.practice.scientificcaluculator;

import android.view.View;
import ex.practice.calc.calucurator.Calcurator.PRECALFORMULA;

final class LogClickEvent implements IClickEvent {

	@Override
	public void registClickEvent(final MainActivity activity) {
		// logキー設定
		activity.findViewById(R.id.key_log).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						activity.calcView.write(activity.inputData.set(
								PRECALFORMULA.LOG.getValue())
								.get());
					}
				});
	}

}
