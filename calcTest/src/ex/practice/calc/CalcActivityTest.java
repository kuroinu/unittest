package ex.practice.calc;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

public class CalcActivityTest extends
		ActivityInstrumentationTestCase2<CalcActivity> {

	private Activity activity;

	private Instrumentation instrumentation;

	public CalcActivityTest() {
		super(CalcActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		instrumentation = getInstrumentation();
		setActivityInitialTouchMode(false);
	}

	public void testテンキー押下() {
		List<KeyTestDataProvider> testList = new ArrayList<KeyTestDataProvider>();
		testList.add(new KeyTestDataProvider(R.id.key_one, "1"));
		testList.add(new KeyTestDataProvider(R.id.key_two, "12"));
		testList.add(new KeyTestDataProvider(R.id.key_three, "123"));
		testList.add(new KeyTestDataProvider(R.id.key_four, "1234"));
		testList.add(new KeyTestDataProvider(R.id.key_five, "12345"));
		testList.add(new KeyTestDataProvider(R.id.key_six, "123456"));
		testList.add(new KeyTestDataProvider(R.id.key_seven, "1234567"));
		testList.add(new KeyTestDataProvider(R.id.key_eight, "12345678"));
		testList.add(new KeyTestDataProvider(R.id.key_nine, "123456789"));
		testList.add(new KeyTestDataProvider(R.id.key_zero, "1234567890"));
		for (KeyTestDataProvider dataProvider : testList) {
			keyAction(dataProvider.id);
			// TextView の値をテストする
			TextView textView = (TextView) activity
					.findViewById(R.id.calcdisparea);
			assertEquals(dataProvider.expected, textView.getText());
		}
	}

	private void keyAction(int id) {
		final Button button = (Button) activity.findViewById(id);
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				button.performClick();
			}
		});
		// UIとの同期をはかる
		instrumentation.waitForIdleSync();

	}

	private class KeyTestDataProvider {
		int id;
		String expected;

		KeyTestDataProvider(int id, String expected) {
			this.id = id;
			this.expected = expected;
		}
	}

	public void testクリアキー押下時にテキストがクリアされることを確認() {
		keyAction(R.id.key_one);
		keyAction(R.id.key_five);
		// TextView の値をテストする
		TextView textView = (TextView) activity.findViewById(R.id.calcdisparea);
		assertEquals("15", textView.getText());
		keyAction(R.id.key_clear);
		assertEquals("", textView.getText());
	}

	public void test先頭で演算子が押せないことの確認() {
		List<KeyTestDataProvider> testList = new ArrayList<KeyTestDataProvider>();
		testList.add(new KeyTestDataProvider(R.id.key_plus, ""));
		testList.add(new KeyTestDataProvider(R.id.key_minus, ""));
		testList.add(new KeyTestDataProvider(R.id.key_multi, ""));
		testList.add(new KeyTestDataProvider(R.id.key_divide, ""));
		for (KeyTestDataProvider dataProvider : testList) {
			keyAction(dataProvider.id);
			// TextView の値をテストする
			TextView textView = (TextView) activity
					.findViewById(R.id.calcdisparea);
			assertEquals(dataProvider.expected, textView.getText());
		}
	}

	public void test数値の後で演算子が押せることの確認() {
		List<KeyTestDataProvider> testList = new ArrayList<KeyTestDataProvider>();
		testList.add(new KeyTestDataProvider(R.id.key_plus, "8+"));
		testList.add(new KeyTestDataProvider(R.id.key_minus, "8-"));
		testList.add(new KeyTestDataProvider(R.id.key_multi, "8×"));
		testList.add(new KeyTestDataProvider(R.id.key_divide, "8÷"));
		for (KeyTestDataProvider dataProvider : testList) {
			// 数値入力
			keyAction(R.id.key_eight);
			// 演算子
			keyAction(dataProvider.id);
			// TextView の値をテストする
			TextView textView = (TextView) activity
					.findViewById(R.id.calcdisparea);
			assertEquals(dataProvider.expected, textView.getText());
			// テキストクリア
			keyAction(R.id.key_clear);
		}
	}

	public void testプラス演算子の後で演算子が押せないことの確認() {
		int[] testList = { R.id.key_plus, R.id.key_minus, R.id.key_multi,
				R.id.key_divide };
		for (int id : testList) {
			keyAction(R.id.key_clear);
			// 数値入力
			keyAction(R.id.key_eight);
			// 演算子入力
			keyAction(R.id.key_plus);
			// TextView の値をテストする
			TextView textView = (TextView) activity
					.findViewById(R.id.calcdisparea);
			CharSequence beforStr = textView.getText();
			// 演算子入力
			keyAction(id);
			assertEquals(beforStr, textView.getText());
		}
	}

	public void testイコールキーを使用した足し算が正しいことの確認() {
		// 数値入力
		keyAction(R.id.key_eight);
		keyAction(R.id.key_plus);
		keyAction(R.id.key_two);
		// =入力
		keyAction(R.id.key_equal);
		TextView textView = (TextView) activity.findViewById(R.id.calcdisparea);
		assertEquals("10", textView.getText().toString());
	}

	public void testイコールキーを使用した引き算が正しいことの確認() {
		// 数値入力
		keyAction(R.id.key_eight);
		keyAction(R.id.key_minus);
		keyAction(R.id.key_two);
		// =入力
		keyAction(R.id.key_equal);
		TextView textView = (TextView) activity.findViewById(R.id.calcdisparea);
		assertEquals("6", textView.getText().toString());
	}

	public void testイコールキーを使用したかけ算が正しいことの確認() {
		// 数値入力
		keyAction(R.id.key_eight);
		keyAction(R.id.key_multi);
		keyAction(R.id.key_two);
		// =入力
		keyAction(R.id.key_equal);
		TextView textView = (TextView) activity.findViewById(R.id.calcdisparea);
		assertEquals("16", textView.getText().toString());
	}

	public void testイコールキーを使用した割り算が正しいことの確認() {
		// 数値入力
		keyAction(R.id.key_eight);
		keyAction(R.id.key_divide);
		keyAction(R.id.key_two);
		// =入力
		keyAction(R.id.key_equal);
		TextView textView = (TextView) activity.findViewById(R.id.calcdisparea);
		assertEquals("4", textView.getText().toString());
	}

	public void test四則演算を織り交ぜた結果が正しいことの確認() {
		// 数値入力
		keyAction(R.id.key_eight);
		keyAction(R.id.key_plus);
		keyAction(R.id.key_two);
		keyAction(R.id.key_multi);
		keyAction(R.id.key_six);
		// =入力
		keyAction(R.id.key_equal);
		TextView textView = (TextView) activity.findViewById(R.id.calcdisparea);
		assertEquals("20", textView.getText().toString());
	}

	public void test数値の後に小数点ボタンを押せることの確認() {
		// 数値入力
		keyAction(R.id.key_eight);
		keyAction(R.id.key_dot);
		TextView textView = (TextView) activity.findViewById(R.id.calcdisparea);
		assertEquals("8.", textView.getText().toString());
	}

	public void test数値以外の後で小数点ボタンが押せないことの確認() {
		// 数値入力
		keyAction(R.id.key_eight);
		keyAction(R.id.key_dot);
		keyAction(R.id.key_dot);
		TextView textView = (TextView) activity.findViewById(R.id.calcdisparea);
		assertEquals("8.", textView.getText().toString());
	}

	public void test小数点を含んだ計算結果が正しいことの確認() {
		// 数値入力
		keyAction(R.id.key_eight);
		keyAction(R.id.key_dot);
		keyAction(R.id.key_five);
		keyAction(R.id.key_plus);
		keyAction(R.id.key_two);
		keyAction(R.id.key_multi);
		keyAction(R.id.key_six);
		// =入力
		keyAction(R.id.key_equal);
		TextView textView = (TextView) activity.findViewById(R.id.calcdisparea);
		assertEquals("20.5", textView.getText().toString());
	}

	public void testイコールキーを使用した後数値が入れ直しになることの確認() {
		// 数値入力
		keyAction(R.id.key_eight);
		keyAction(R.id.key_divide);
		keyAction(R.id.key_two);
		// =入力
		keyAction(R.id.key_equal);
		keyAction(R.id.key_eight);
		TextView textView = (TextView) activity.findViewById(R.id.calcdisparea);
		assertEquals("8", textView.getText().toString());
	}

	public void testUndoキーを押して一つ前の数式を呼び出すことの確認() {
		// 数値入力
		keyAction(R.id.key_eight);
		keyAction(R.id.key_divide);
		keyAction(R.id.key_two);
		// =入力
		keyAction(R.id.key_equal);
		// undo
		keyAction(R.id.key_undo);
		TextView textView = (TextView) activity.findViewById(R.id.calcdisparea);
		assertEquals("8÷2", textView.getText().toString());
	}

	public void testUndoキーを押してふたつ前の数式を呼び出すことの確認() {
		// 数値入力
		keyAction(R.id.key_five);
		keyAction(R.id.key_minus);
		keyAction(R.id.key_five);
		// =入力
		keyAction(R.id.key_equal);
		// 数値入力
		keyAction(R.id.key_eight);
		keyAction(R.id.key_divide);
		keyAction(R.id.key_two);
		// =入力
		keyAction(R.id.key_equal);

		// undo
		keyAction(R.id.key_undo);
		keyAction(R.id.key_undo);
		TextView textView = (TextView) activity.findViewById(R.id.calcdisparea);
		assertEquals("5-5", textView.getText().toString());
	}
}
