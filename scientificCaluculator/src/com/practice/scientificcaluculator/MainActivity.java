package com.practice.scientificcaluculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import ex.practice.calc.calucurator.Calcurator.CODE;
import ex.practice.calc.calucurator.Calcurator.OPERATOR;
import ex.practice.calc.validator.NumberValidator;

@SuppressLint("UseSparseArrays")
public class MainActivity extends Activity {
	private static final List<IClickEvent> eventList;
	static {
		eventList = new ArrayList<IClickEvent>();
		eventList.add(new TenkeysClickEvent());
		eventList.add(new ClearKeyEvent());
		eventList.add(new OperatorClickEvent());
		eventList.add(new EqualClickEvent());
		eventList.add(new DotClickEvent());
		eventList.add(new UndoClickEvent());
		eventList.add(new PlusMinusClickEvent());
		eventList.add(new LeftBlacketClickEvent());
		eventList.add(new RightBlacketClickEvent());
		eventList.add(new BSClickEvent());
		eventList.add(new RootClickEvent());
		eventList.add(new LogClickEvent());
	}
	final InputData inputData = new InputData();
	CalcView calcView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		calcView = new CalcView();
		for (IClickEvent event : eventList) {
			event.registClickEvent(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * ビューエリアへのアクセス取りまとめ暮らす
	 *
	 * @author mitsunorinaraoka
	 *
	 */
	class CalcView {
		private TextView calcdisparea;

		CalcView() {
			calcdisparea = (TextView) findViewById(R.id.textView1);
		}

		void write(String data) {
			calcdisparea.setText(convertForViewing(data));
		}

		private String convertForViewing(String target) {
			return CODE.LOG.codeFromView(operatorInBlacketConverter(
					operatorInBlacketConverter(CODE.POWER
							.codeFromView(CODE.MINUS.viewFromCode(target)),
							OPERATOR.ADD), OPERATOR.SUBTRACT));
		}

		private String getLastElement(String data) {
			String[] elements = data.split("[^(0-9\\.)]");
			return elements[elements.length - 1];
		}

		boolean isLastElementOfCalcViewAreaNumber(String data) {
			return new NumberValidator().isCongruence(getLastElement(data));
		}

		private String operatorInBlacketConverter(String target,
				OPERATOR operator) {
			return target.replace(operator.getBrancketVal(),
					operator.getValue());
		}

	}

	/**
	 * 入力データのとりまとめクラス
	 *
	 * @author mitsunorinaraoka
	 *
	 */
	class InputData {
		private final StringBuilder data = new StringBuilder();
		private final LinkedList<String> memento = new LinkedList<String>();
		int blacketCnt = 0;

		InputData initialize() {
			data.setLength(0);
			initializeBlacket();
			return this;
		}

		private void initializeBlacket() {
			blacketCnt = 0;
		}

		InputData del(int num) {
			int delLength = data.length() >= num ? data.length() - num : 0;
			data.setLength(delLength);
			return this;
		}

		void memorize() {
			memento.push(data.toString());
		}

		String recollect() {
			if (memento.isEmpty()) {
				return "";
			} else {
				return memento.pop();
			}
		}

		InputData set(String value) {
			data.append(value);
			return this;
		}

		String get() {
			return data.toString();
		}
	}

}
