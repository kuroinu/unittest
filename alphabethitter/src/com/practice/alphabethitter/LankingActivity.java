package com.practice.alphabethitter;

import com.practice.model.db.ResultModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class LankingActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lanking);
		final int[] lankingIds = { R.id.textView1, R.id.textView2,
				R.id.textView3 };
		int index = 0;
		final ResultModel resultTable = new ResultModel(this);
		for (String data : resultTable.selectTop3()) {
			TextView txt = (TextView) findViewById(lankingIds[index++]);
			txt.setText(data);
		}
		// メニューへ
		findViewById(R.id.button1).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						startActivity(new Intent(LankingActivity.this,
								MenuActivity.class));
					}
				});

		// 履歴クリア
		findViewById(R.id.button2).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						resultTable.deleteAll();
						for (int id : lankingIds) {
							TextView txt = (TextView) findViewById(id);
							txt.setText("");
						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
