package com.practice.scientificcaluculator;

import android.view.View;
import ex.practice.calc.calucurator.Calcurator.PRECALFORMULA;

final class RootClickEvent implements IClickEvent {

	@Override
	public void registClickEvent(final MainActivity activity) {
		// √キー設定
		activity.findViewById(R.id.key_root).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						activity.calcView.write(activity.inputData.set(
								PRECALFORMULA.ROOT.getValue())
								.get());
					}
				});
	}

}
