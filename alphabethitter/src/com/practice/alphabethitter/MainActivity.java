package com.practice.alphabethitter;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.practice.model.db.ResultModel;
import com.practice.randomizer.AlphabetRandomizer;

public class MainActivity extends Activity {
	enum TIMER_STATUS {
		WORKING, IDLE
	}

	enum GAME_LEVEL {
		NORMAL, HARD
	}

	enum INPUT_KEY {
		KEY_1(R.id.key_1), KEY_2(R.id.key_2), KEY_3(R.id.key_3), KEY_4(
				R.id.key_4), KEY_5(R.id.key_5), KEY_6(R.id.key_6), KEY_7(
				R.id.key_7), KEY_8(R.id.key_8), KEY_9(R.id.key_9), KEY_10(
				R.id.key_10), KEY_11(R.id.key_11), KEY_12(R.id.key_12), KEY_13(
				R.id.key_13), KEY_14(R.id.key_14), KEY_15(R.id.key_15), KEY_16(
				R.id.key_16), KEY_17(R.id.key_17), KEY_18(R.id.key_18), KEY_19(
				R.id.key_19), KEY_20(R.id.key_20), KEY_21(R.id.key_21), KEY_22(
				R.id.key_22), KEY_23(R.id.key_23), KEY_24(R.id.key_24), KEY_25(
				R.id.key_25), KEY_26(R.id.key_26);
		private final int id;

		INPUT_KEY(int id) {
			this.id = id;
		}
	}

	enum DIALOG_ACTION {
		YES, NO
	}

	// 文字入力キー
	private static final INPUT_KEY[] INPUT_KEYS = { INPUT_KEY.KEY_1,
			INPUT_KEY.KEY_2, INPUT_KEY.KEY_3, INPUT_KEY.KEY_4, INPUT_KEY.KEY_5,
			INPUT_KEY.KEY_6, INPUT_KEY.KEY_7, INPUT_KEY.KEY_8, INPUT_KEY.KEY_9,
			INPUT_KEY.KEY_10, INPUT_KEY.KEY_11, INPUT_KEY.KEY_12,
			INPUT_KEY.KEY_13, INPUT_KEY.KEY_14, INPUT_KEY.KEY_15,
			INPUT_KEY.KEY_16, INPUT_KEY.KEY_17, INPUT_KEY.KEY_18,
			INPUT_KEY.KEY_19, INPUT_KEY.KEY_20, INPUT_KEY.KEY_21,
			INPUT_KEY.KEY_22, INPUT_KEY.KEY_23, INPUT_KEY.KEY_24,
			INPUT_KEY.KEY_25, INPUT_KEY.KEY_26 };

	private TimeManager timeManager;
	private OrderManager orderManager;
	private ScoreManager scoreManager;
	private InputKeyManager inputKeyManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initalize();

		registEvntListner();
	}

	/**
	 * 初期化
	 */
	private void initalize() {

		// フィールド管理オブジェクト作成
		timeManager = new TimeManager();
		orderManager = new OrderManager();
		scoreManager = new ScoreManager();
		inputKeyManager = new InputKeyManager();
		// 最初の指令を入力
		orderManager.newOrder();
		// スコア設定
		scoreManager.updateScore();
		// ハードレベルなら入力キーをシャッフル
		GAME_LEVEL level = (GAME_LEVEL) getIntent().getSerializableExtra(
				"level");
		if (level == GAME_LEVEL.HARD) {
			inputKeyManager.keyShuffle();
		}
	}

	private void dispMessage(String message) {
		Toast toast = Toast.makeText(MainActivity.this, message,
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP | Gravity.LEFT, 40, 40);
		toast.show();
	}

	/**
	 * イベント設定
	 */
	private void registEvntListner() {
		// タイマー動作
		timeManager.setEvent();
		// 入力キー押下
		inputKeyManager.setEvent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 入力キーの管理
	 *
	 * @author mitsunorinaraoka
	 *
	 */
	private final class InputKeyManager {
		private void keyShuffle() {
			AlphabetRandomizer generator = AlphabetRandomizer.newInstance();
			// キーシャッフル
			for (INPUT_KEY key : INPUT_KEYS) {
				Button btn = (Button) findViewById(key.id);
				btn.setText(generator.getUniqueRandomAlphabet());
			}
		}

		private void setEvent() {
			// 入力キー押下
			for (INPUT_KEY key : INPUT_KEYS) {
				clickCharKey(key.id);
			}
		}

		private void clickCharKey(int id) {
			findViewById(id).setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					if (timeManager.timer_status == TIMER_STATUS.WORKING) {
						Button b = (Button) findViewById(v.getId());
						// 入力結果が一致
						if (orderManager.isSameStr(b.getText().toString())) {
							scoreManager.addScore();
							scoreManager.updateScore();
							// クリア判定
							if (scoreManager.isGameCompleted()) {
								completeAction();
							} else {
								orderManager.newOrder();
							}
						} else {
							dispMessage("Miss");
						}
					}
				}
			});
		}

		/**
		 * クリア時の処理
		 */
		private void completeAction() {
			ResultModel resultModel = new ResultModel(MainActivity.this);
			List<String> l = resultModel.selectTop3();
			if(l.size() < 3) {
				resultModel.insert(timeManager.getTime());
			}
			timeManager.fallAsleep();
			dialogAction();
		}

		/**
		 * ダイアログ表示
		 */
		private void dialogAction() {
			AlertDialog.Builder builder = new AlertDialog.Builder(
					MainActivity.this);
			builder.setTitle("Game Clear")
					.setMessage(timeManager.getTime() + " continue?")
					.setIcon(R.drawable.ic_launcher)
					.setPositiveButton("はい",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									scoreManager.initScore();
									orderManager.resetOrder();
									orderManager.newOrder();
								}
							})
					.setNegativeButton("いいえ",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// ランキングに遷移
									startActivity(new Intent(MainActivity.this,
											LankingActivity.class));
								}
							}).show();

		}
	}

	/**
	 * オーダー領域の管理
	 *
	 * @author mitsunorinaraoka
	 *
	 */
	private final class OrderManager {
		final TextView orderField;
		final AlphabetRandomizer generator;

		private OrderManager() {
			orderField = (TextView) findViewById(R.id.textView1);
			generator = AlphabetRandomizer.newInstance();
		}

		private void newOrder() {
			orderField.setText(generator.getUniqueRandomAlphabet());
		}

		private boolean isSameStr(String target) {
			return orderField.getText().toString().equals(target);
		}

		private void resetOrder() {
			generator.clearance();
		}
	}

	/**
	 * スコアフィールド管理
	 *
	 * @author mitsunorinaraoka
	 *
	 */
	private class ScoreManager {
		final TextView scoreField;
		int score = 0;

		private ScoreManager() {
			scoreField = (TextView) findViewById(R.id.textView2);
		}

		private void addScore() {
			score++;
		}

		private void initScore() {
			score = 0;
			updateScore();
		}

		private void updateScore() {
			scoreField.setText(Integer.toString(score) + "/"
					+ Integer.toString(INPUT_KEYS.length));
		}

		private boolean isGameCompleted() {
			return score >= INPUT_KEYS.length;
		}
	}

	/**
	 * タイマー管理
	 *
	 * @author mitsunorinaraoka
	 *
	 */
	private final class TimeManager {

		final Chronometer chronometer;
		TIMER_STATUS timer_status = TIMER_STATUS.IDLE;

		private TimeManager() {
			chronometer = (Chronometer) findViewById(R.id.chronometer1);
			chronometer.setFormat("%s");
		}

		private void setEvent() {
			findViewById(R.id.key_timer_action).setOnClickListener(
					new View.OnClickListener() {
						public void onClick(View v) {
							if (timer_status == TIMER_STATUS.IDLE) {
								wakeUp();
							} else {
								// メニューに戻る
								startActivity(new Intent(MainActivity.this,
										MenuActivity.class));
							}
						}
					});
		}

		private void wakeUp() {
			Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer1);
			chronometer.setFormat("%s");
			chronometer.setBase(SystemClock.elapsedRealtime());
			chronometer.start();
			timer_status = TIMER_STATUS.WORKING;
			Button actionBtn = (Button) findViewById(R.id.key_timer_action);
			actionBtn.setText("STOP");
		}

		private void fallAsleep() {
			Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer1);
			chronometer.stop();
			timer_status = TIMER_STATUS.IDLE;
			Button actionBtn = (Button) findViewById(R.id.key_timer_action);
			actionBtn.setText("START");
		}

		private String getTime() {
			return chronometer.getText().toString();
		}
	}

}
