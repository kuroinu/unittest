package ex.practice.categories.suites;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import ex.practice.categories.categories.ThirdTest;
import ex.practice.now.NowDateFormatterTest;
import ex.practice.now.NowDateTest;

@RunWith(Categories.class)
@IncludeCategory(ThirdTest.class)
@SuiteClasses({ NowDateFormatterTest.class, NowDateTest.class })
public class ThirdTestSuite {

}
