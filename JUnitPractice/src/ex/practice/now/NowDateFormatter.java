package ex.practice.now;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TDD２回目のサンプル<br/>
 * 現在日付をフォーマット変換する
 *
 * @author mitsunorinaraoka
 *
 */
public class NowDateFormatter {
	public String nowDateFormatJapaneseStyle() throws ParseException {
		Date date = DateFormat.getDateInstance().parse(createNowDate());
		return new SimpleDateFormat("yyyy年MM月dd日").format(date);
	}

	/**
	 * テスタブルコードとして切り出し
	 * @return
	 */
	public String createNowDate() {
		return new NowDate().whatDayIsItToday();
	}
}
