package ex.practice.now;

import static matches.RegMatcher.regMatcher;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import ex.practice.categories.categories.AllTest;
import ex.practice.categories.categories.ThirdTest;

/**
 * TDD勉強会２回目<br/>
 * テストダブルの実践
 *
 * @author mitsunorinaraoka
 *
 */
@RunWith(Enclosed.class)
public class NowDateFormatterTest {
	@RunWith(Theories.class)
	public static class createNowDateがコールされた場合 {
		NowDateFormatter formatter;

		@Before
		public void setUp() {
			formatter = new NowDateFormatter();
		}

		@Category({AllTest.class,ThirdTest.class})
		@Test
		public void yyyyスラッシュmmスラッシュdd形式で日付を返却する() {
			assertThat(
					formatter.createNowDate(),
					regMatcher("^[1-2]\\d{3}\\/(0{0,1}[1-9]|1[012])\\/([0-2]{0,1}\\d|3[0-1])"));
		}
	}

	@RunWith(Theories.class)
	public static class nowDateFormatJapaneseStyleがコールされた場合 {
		NowDateFormatter formatter;
		NowDate now;

		@Before
		public void setUp() {
			formatter = new NowDateFormatter();
		}

		@Category({AllTest.class,ThirdTest.class})
		@Test
		public void 現在日付をYYYY年MM月DD日形式に変換して返却する() throws ParseException {
			// 　モックに差し替えて現在日付を固定値に
			NowDateFormatter mock = spy(formatter);
			when(mock.createNowDate()).thenReturn("2013/12/31");
			assertThat(mock.nowDateFormatJapaneseStyle(), is("2013年12月31日"));
		}

		@Test
		public void モック置換なしでの確認() throws ParseException {
			assertThat(
					formatter.nowDateFormatJapaneseStyle(),
					regMatcher("^[1-2]\\d{3}年(0{0,1}[1-9]|1[012])月([0-2]{0,1}\\d|3[0-1])日"));
		}
	}

}
