package com.practice.alphabethitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		// レベル選択
		findViewById(R.id.normalLevel).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						goToMain(MainActivity.GAME_LEVEL.NORMAL);
					}
				});
		findViewById(R.id.hardLevel).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						goToMain(MainActivity.GAME_LEVEL.HARD);
					}
				});
		// 得点ランキングへ
		findViewById(R.id.lanking).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						goToLanking();
					}
				});
	}

	private void goToMain(MainActivity.GAME_LEVEL level) {
		Intent intent = new Intent(MenuActivity.this, MainActivity.class);
		intent.putExtra("level", level);
		startActivity(intent);
	}

	private void goToLanking() {
		startActivity(new Intent(MenuActivity.this, LankingActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
