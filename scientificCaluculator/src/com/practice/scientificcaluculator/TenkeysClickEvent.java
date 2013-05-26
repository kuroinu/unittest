package com.practice.scientificcaluculator;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.view.View;

@SuppressLint("UseSparseArrays")
final class TenkeysClickEvent implements IClickEvent {
	/**
	 * テンキーのIDマッパー
	 */
	private static final Map<Integer, String> ten_keys_def;
	static {
		ten_keys_def = new HashMap<Integer, String>();
		int[] ten_key_list = { R.id.key_zero, R.id.key_one, R.id.key_two,
				R.id.key_three, R.id.key_four, R.id.key_five, R.id.key_six,
				R.id.key_seven, R.id.key_eight, R.id.key_nine };
		for (int i = 0; i < 10; i++) {
			ten_keys_def.put(ten_key_list[i], String.valueOf(i));
		}
	}

	@Override
	public void registClickEvent(final MainActivity activity) {
		// テンキー動作指定
		for (final Map.Entry<Integer, String> e : ten_keys_def.entrySet()) {
			activity.findViewById(e.getKey()).setOnClickListener(
					new View.OnClickListener() {
						public void onClick(View v) {
							activity.calcView.write(activity.inputData.set(
									e.getValue()).get());
						}
					});
		}
	}

}
