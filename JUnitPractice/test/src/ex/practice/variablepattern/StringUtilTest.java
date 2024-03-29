package ex.practice.variablepattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import ex.practice.categories.categories.AllTest;
import ex.practice.categories.categories.SecondTest;

/**
 * 第2回目のテスト<br/>
 * <ul>
 * <li>キャメルスタイルのテスト</li>
 * <li>例外のテスト</li>
 * </ul>
 *
 * @author mitsunorinaraoka
 *
 */
@RunWith(Enclosed.class)
public class StringUtilTest {

	@RunWith(Theories.class)
	public static class convertCamelCaseで文字列を変換する場合 {
		StringUtil strUtil;

		@Before
		public void setUp() throws Exception {
			strUtil = new StringUtil();
		}

		@DataPoints
		public static Fixture[] DATAS = { new Fixture("test", "test"),
				new Fixture("test_one", "testOne"),
				new Fixture("Test_one", "testOne"),
				new Fixture("Test_one_tow", "testOneTwo"),};

		@Category({ AllTest.class, SecondTest.class })
		@Theory
		public void キャメルスタイル形式に変換される(Fixture data) throws ParseException {
			String actual = strUtil.convertCamelCase(data.parameter);
			assertThat(data.expected, is(actual));
		}

		static class Fixture {
			String parameter;
			String expected;

			Fixture(String parameter, String expected) {
				this.parameter = parameter;
				this.expected = expected;
			}
		}
	}

	public static class convertCamelCaseでフォーマット不正な文字列を入力する場合 {
		StringUtil strUtil;

		@Before
		public void setUp() throws Exception {
			strUtil = new StringUtil();
		}

		@Category({ AllTest.class, SecondTest.class })
		@Test(expected = ParseException.class)
		public void 例外が発生する() throws ParseException {
			strUtil.convertCamelCase("test__test");
		}

	}
}
