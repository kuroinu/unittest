package ex.practice.now;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TDD勉強会２回目のサンプル<br/>
 * 現在日付をyyyy/mm/dd形式で表示する
 * @author mitsunorinaraoka
 *
 */
public class NowDate {

	public String whatDayIsItToday() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}
}
