package ex.practice.categories.suites;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import ex.practice.categories.categories.FirstTest;
import ex.practice.fizzbuzz.FizzBuzzTest;

/**
 * TDD勉強会第一回のテストスイーツ<br/>
 * FizzBuzzを使用したテストケース
 * @author mitsunorinaraoka
 *
 */
@RunWith(Categories.class)
@IncludeCategory(FirstTest.class)
@SuiteClasses({ FizzBuzzTest.class})
public class FirstTestSuite {

}
