package ex.practice.calc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ex.practice.calc.calucurator.Calcurator;
import ex.practice.calc.validator.LastCharIsNumberValidator;
import ex.practice.calc.validator.NumberValidator;

@SuppressLint("UseSparseArrays")
public class CalcActivity extends Activity {
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
	private static final Map<Integer, String> operator_keys_def;
	static {
		operator_keys_def = new HashMap<Integer, String>();
		operator_keys_def
				.put(R.id.key_plus, Calcurator.OPERATOR.ADD.getValue());
		operator_keys_def.put(R.id.key_minus,
				Calcurator.OPERATOR.SUBTRACT.getValue());
		operator_keys_def.put(R.id.key_multi,
				Calcurator.OPERATOR.MULTI.getValue());
		operator_keys_def.put(R.id.key_divide,
				Calcurator.OPERATOR.DIVIDE.getValue());
	}
	private final InputData inputData = new InputData();
	private CalcView calcView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc);
		calcView = new CalcView();
		// テンキー動作指定
		clickTenKeys();
		// クリアキー動作指定
		clickClearKey();
		// 演算子動作指定
		clickOperatorKeys();
		// =キー動作指定
		clickEqualKey();
		// .キー動作指定
		clickDotKey();
		// UNDOキー動作指定
		clickUndoKey();
	}

	private void clickTenKeys() {
		// テンキー動作指定
		for (final Map.Entry<Integer, String> e : ten_keys_def.entrySet()) {
			((Button) findViewById(e.getKey()))
					.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							calcView.write(inputData.set(e.getValue()).get());
						}
					});
		}
	}

	private void clickClearKey() {
		// クリアキー設定
		findViewById(R.id.key_clear).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						calcView.write(inputData.initialize().get());
					}
				});
	}

	private void clickOperatorKeys() {
		// 演算子キー設定
		for (final Map.Entry<Integer, String> e : operator_keys_def.entrySet()) {
			((Button) findViewById(e.getKey()))
					.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							if (calcView
									.isLastCharOfCalcViewAreaNumber(inputData
											.get())) {
								calcView.write(inputData.set(e.getValue())
										.get());
							}
						}
					});
		}
	}

	private void clickEqualKey() {
		// =キー設定
		((Button) findViewById(R.id.key_equal))
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						Calcurator calculator = new Calcurator();
						calcView.write(calculator.caluculate(inputData.get()));
						inputData.memorize();
						inputData.initialize();
					}
				});
	}

	private void clickDotKey() {
		// .キー設定
		((Button) findViewById(R.id.key_dot))
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (calcView
								.isLastElementOfCalcViewAreaNumber(inputData
										.get())) {
							calcView.write(inputData.set(".").get());
						}
					}
				});
	}

	private void clickUndoKey() {
		// undoキー設定
		((Button) findViewById(R.id.key_undo))
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						calcView.write(inputData.initialize()
								.set(inputData.recollect()).get());
					}
				});
	}

	private class CalcView {
		private TextView calcdisparea;

		CalcView() {
			calcdisparea = (TextView) findViewById(R.id.calcdisparea);
		}

		private void write(String data) {
			calcdisparea.setText(data);
		}

		private boolean isLastCharOfCalcViewAreaNumber(String data) {
			return new LastCharIsNumberValidator().isCongruence(data);
		}

		private String getLastElement(String data) {
			String[] elements = data.split("[^(0-9\\.)]");
			return elements[elements.length - 1];
		}

		private boolean isLastElementOfCalcViewAreaNumber(String data) {
			return new NumberValidator().isCongruence(getLastElement(data));
		}

	}

	private class InputData {
		private final StringBuilder data = new StringBuilder();
		private final LinkedList<String> memento = new LinkedList<String>();

		private InputData initialize() {
			data.setLength(0);
			return this;
		}

		private void memorize() {
			memento.push(data.toString());
		}

		private String recollect() {
			if (memento.isEmpty()) {
				return "";
			} else {
				return memento.pop();
			}
		}

		private InputData set(String value) {
			data.append(value);
			return this;
		}

		private String get() {
			return data.toString();
		}
	}
}
