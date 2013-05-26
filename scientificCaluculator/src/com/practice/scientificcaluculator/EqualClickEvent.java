package com.practice.scientificcaluculator;

import android.view.View;
import ex.practice.calc.calucurator.Calcurator;

final class EqualClickEvent implements IClickEvent {

	@Override
	public void registClickEvent(final MainActivity activity) {
		// =キー設定
		activity.findViewById(R.id.key_equal).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						if (activity.inputData.blacketCnt <= 0) {
							Calcurator calculator = new Calcurator();
							activity.calcView.write(calculator
									.caluculate(activity.inputData.get()));
							activity.inputData.memorize();
							activity.inputData.initialize();
						}
					}
				});
	}

}
