package com.practice.scientificcaluculator;

import android.view.View;

final class DotClickEvent implements IClickEvent {

	@Override
	public void registClickEvent(final MainActivity activity) {
		// .キー設定
		activity.findViewById(R.id.key_dot).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						if (activity.calcView
								.isLastElementOfCalcViewAreaNumber(activity.inputData
										.get())) {
							activity.calcView.write(activity.inputData.set(".").get());
						}
					}
				});
	}

}
