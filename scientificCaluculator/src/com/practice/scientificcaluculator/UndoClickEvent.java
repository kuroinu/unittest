package com.practice.scientificcaluculator;

import android.view.View;

final class UndoClickEvent implements IClickEvent {

	@Override
	public void registClickEvent(final MainActivity activity) {
		// undoキー設定
		activity.findViewById(R.id.key_undo).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						activity.calcView.write(activity.inputData.initialize()
								.set(activity.inputData.recollect()).get());
					}
				});
	}

}
