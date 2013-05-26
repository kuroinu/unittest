package ex.practice.categories.suites;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import ex.practice.categories.categories.SecondTest;
import ex.practice.variablepattern.StringUtilTest;

@RunWith(Categories.class)
@IncludeCategory(SecondTest.class)
@SuiteClasses({ StringUtilTest.class})
public class SecondTestSuite {

}
