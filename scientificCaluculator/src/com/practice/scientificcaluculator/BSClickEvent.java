package com.practice.scientificcaluculator;

import android.view.View;

final class BSClickEvent implements IClickEvent {

	@Override
	public void registClickEvent(final MainActivity activity) {
		// クリックキー設定
		activity.findViewById(R.id.key_back).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						activity.calcView
								.write(activity.inputData.del(1).get());
					}
				});
	}

}
