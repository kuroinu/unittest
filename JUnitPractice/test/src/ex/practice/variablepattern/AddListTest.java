package ex.practice.variablepattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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
 * voidのテスト
 *
 * @author mitsunorinaraoka
 *
 */
@RunWith(Enclosed.class)
public class AddListTest {

	@RunWith(Theories.class)
	public static class add処理でデータを追加する場合 {
		AddList addList;

		@Before
		public void setUp() throws Exception {
			addList = new AddList();
		}

		@DataPoints
		public static Fixture[] DATAS = { new Fixture(1), new Fixture(2), };

		@Category({ AllTest.class, SecondTest.class })
		@Theory
		public void コールされるたびにデータが追加される(Fixture data) throws ParseException {
			for (int i = 0; i < data.addCnt; i++) {
				addList.add();
			}
			List<Integer> actual = addList.getList();
			assertThat(data.expected, is(actual));
		}

		static class Fixture {
			int addCnt;
			List<Integer> expected;

			Fixture(int addCnt) {
				this.addCnt = addCnt;
				expected = new ArrayList<Integer>(addCnt);
				for (int i = 0; i < addCnt; i++) {
					expected.add(i);
				}
			}
		}

	}

}
