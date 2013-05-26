package ex.practice.categories.suites;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import ex.practice.categories.categories.AllTest;
import ex.practice.fizzbuzz.FizzBuzzTest;
import ex.practice.now.NowDateFormatterTest;
import ex.practice.now.NowDateTest;

/**
 * 全テストケース実行
 * @author mitsunorinaraoka
 *
 */
@RunWith(Categories.class)
@IncludeCategory(AllTest.class)
@SuiteClasses({ FizzBuzzTest.class, NowDateFormatterTest.class,NowDateTest.class})
public class AllTestSuite {
	public AllTestSuite() {

	}
}
