package com.practice.scientificcaluculator;

import android.view.View;

final class ClearKeyEvent implements IClickEvent {

	@Override
	public void registClickEvent(final MainActivity activity) {
		// クリアキー設定
		activity.findViewById(R.id.key_clear).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						activity.calcView.write(activity.inputData.initialize().get());
					}
				});
	}

}
