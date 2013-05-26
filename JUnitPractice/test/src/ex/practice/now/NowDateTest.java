package ex.practice.now;

import static org.junit.Assert.*;
import static matches.RegMatcher.regMatcher;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import ex.practice.categories.categories.AllTest;
import ex.practice.categories.categories.ThirdTest;

@RunWith(Enclosed.class)
public class NowDateTest {
	@RunWith(Theories.class)
	public static class whatDayIsItTodayがコールされた場合 {
		@Category({ AllTest.class, ThirdTest.class })
		@Test
		public void yyyymmddのスラッシュ区切りで返却される() {
			assertThat(
					new NowDate().whatDayIsItToday(),
					regMatcher("^[1-2]\\d{3}\\/(0{0,1}[1-9]|1[012])\\/([0-2]{0,1}\\d|3[0-1])"));
		}
	}
}
