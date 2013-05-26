package ex.practice.calc.calucurator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ex.practice.calc.calucurator.Calcurator.OPERATOR;

import junit.framework.TestCase;

public class CalcuratorTest extends TestCase {

	private class PickOutPriorityOperatorsDataProvider {
		private String target;
		private List<Calcurator.OPERATOR> expected;

		PickOutPriorityOperatorsDataProvider(String target,
				List<Calcurator.OPERATOR> expected) {
			this.target = target;
			this.expected = expected;
		}
	}

	@SuppressWarnings("unchecked")
	public void testPickOutLowPriorityOperatorsが加算減算のみピックアップすることの確認() {
		try {
			Method method = Calcurator.class.getDeclaredMethod(
					"pickOutLowPriorityOperators", String.class);
			method.setAccessible(true);
			ArrayList<PickOutPriorityOperatorsDataProvider> testlist = new ArrayList<PickOutPriorityOperatorsDataProvider>();
			List<OPERATOR> case1 = new ArrayList<OPERATOR>();
			testlist.add(new PickOutPriorityOperatorsDataProvider("1+1", case1));
			case1.add(Calcurator.OPERATOR.ADD);
			case1.add(Calcurator.OPERATOR.EQUALS);

			List<OPERATOR> case2 = new ArrayList<OPERATOR>();
			testlist.add(new PickOutPriorityOperatorsDataProvider("1+1-5",
					case2));
			case2.add(Calcurator.OPERATOR.ADD);
			case2.add(Calcurator.OPERATOR.SUBTRACT);
			case2.add(Calcurator.OPERATOR.EQUALS);

			List<OPERATOR> case3 = new ArrayList<OPERATOR>();
			testlist.add(new PickOutPriorityOperatorsDataProvider("1÷1-5×6",
					case3));
			case3.add(Calcurator.OPERATOR.SUBTRACT);
			case3.add(Calcurator.OPERATOR.EQUALS);

			for (PickOutPriorityOperatorsDataProvider dataProvider : testlist) {
				List<OPERATOR> actual = (List<OPERATOR>) method.invoke(
						new Calcurator(), dataProvider.target);
				assertEquals(dataProvider.expected, actual);
			}
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void testPickOutHighPriorityOperatorsが乗算余算のみピックアップすることの確認() {
		try {
			Method method = Calcurator.class.getDeclaredMethod(
					"pickOutHighPriorityOperators", String.class);
			method.setAccessible(true);
			ArrayList<PickOutPriorityOperatorsDataProvider> testlist = new ArrayList<PickOutPriorityOperatorsDataProvider>();
			List<OPERATOR> case1 = new ArrayList<OPERATOR>();
			testlist.add(new PickOutPriorityOperatorsDataProvider("1×1", case1));
			case1.add(Calcurator.OPERATOR.MULTI);
			case1.add(Calcurator.OPERATOR.EQUALS);

			List<OPERATOR> case2 = new ArrayList<OPERATOR>();
			testlist.add(new PickOutPriorityOperatorsDataProvider("1×1÷5",
					case2));
			case2.add(Calcurator.OPERATOR.MULTI);
			case2.add(Calcurator.OPERATOR.DIVIDE);
			case2.add(Calcurator.OPERATOR.EQUALS);

			List<OPERATOR> case3 = new ArrayList<OPERATOR>();
			testlist.add(new PickOutPriorityOperatorsDataProvider("1÷1-5×6",
					case3));
			case3.add(Calcurator.OPERATOR.DIVIDE);
			case3.add(Calcurator.OPERATOR.MULTI);
			case3.add(Calcurator.OPERATOR.EQUALS);

			for (PickOutPriorityOperatorsDataProvider dataProvider : testlist) {
				List<OPERATOR> actual = (List<OPERATOR>) method.invoke(
						new Calcurator(), dataProvider.target);
				assertEquals(dataProvider.expected, actual);
			}
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}

	private class PickOutInterpretNumbersDataProvider {
		private String target;
		private List<String> expected;

		PickOutInterpretNumbersDataProvider(String target, List<String> expected) {
			this.target = target;
			this.expected = expected;
		}
	}

	@SuppressWarnings("unchecked")
	public void testPickOutFirstInterpretNumbersが加算減算で式を分割してることの確認() {
		try {
			Method method = Calcurator.class.getDeclaredMethod(
					"pickOutFirstInterpretNumbers", String.class);
			method.setAccessible(true);
			ArrayList<PickOutInterpretNumbersDataProvider> testlist = new ArrayList<PickOutInterpretNumbersDataProvider>();
			List<String> 加算で数値が分割されることの確認 = new ArrayList<String>();
			testlist.add(new PickOutInterpretNumbersDataProvider("1+1",
					加算で数値が分割されることの確認));
			加算で数値が分割されることの確認.add("1");
			加算で数値が分割されることの確認.add("1");

			List<String> 加算と減算で数値が分割されることの確認 = new ArrayList<String>();
			testlist.add(new PickOutInterpretNumbersDataProvider("1+2-3",
					加算と減算で数値が分割されることの確認));
			加算と減算で数値が分割されることの確認.add("1");
			加算と減算で数値が分割されることの確認.add("2");
			加算と減算で数値が分割されることの確認.add("3");

			List<String> 乗算と序算が分割されないことの確認 = new ArrayList<String>();
			testlist.add(new PickOutInterpretNumbersDataProvider("5×6-1÷1+2",
					乗算と序算が分割されないことの確認));
			乗算と序算が分割されないことの確認.add("5×6");
			乗算と序算が分割されないことの確認.add("1÷1");
			乗算と序算が分割されないことの確認.add("2");
			for (PickOutInterpretNumbersDataProvider dataProvider : testlist) {
				List<String> actual = (List<String>) method.invoke(
						new Calcurator(), dataProvider.target);
				assertEquals(dataProvider.expected, actual);
			}
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void testPickOutSecondInterpretNumbersが乗算余算で式を分割してることの確認() {
		try {
			Method method = Calcurator.class.getDeclaredMethod(
					"pickOutSecondInterpretNumbers", String.class);
			method.setAccessible(true);
			ArrayList<PickOutInterpretNumbersDataProvider> testlist = new ArrayList<PickOutInterpretNumbersDataProvider>();
			List<String> 乗算で数値が分割されることの確認 = new ArrayList<String>();
			testlist.add(new PickOutInterpretNumbersDataProvider("1×1",
					乗算で数値が分割されることの確認));
			乗算で数値が分割されることの確認.add("1");
			乗算で数値が分割されることの確認.add("1");

			List<String> 乗算と余算で数値が分割されることの確認 = new ArrayList<String>();
			testlist.add(new PickOutInterpretNumbersDataProvider("1×2÷3",
					乗算と余算で数値が分割されることの確認));
			乗算と余算で数値が分割されることの確認.add("1");
			乗算と余算で数値が分割されることの確認.add("2");
			乗算と余算で数値が分割されることの確認.add("3");

			List<String> 加算と減算が分割されないことの確認 = new ArrayList<String>();
			testlist.add(new PickOutInterpretNumbersDataProvider("5×6-1÷2+2",
					加算と減算が分割されないことの確認));
			加算と減算が分割されないことの確認.add("5");
			加算と減算が分割されないことの確認.add("6-1");
			加算と減算が分割されないことの確認.add("2+2");
			for (PickOutInterpretNumbersDataProvider dataProvider : testlist) {
				List<String> actual = (List<String>) method.invoke(
						new Calcurator(), dataProvider.target);
				assertEquals(dataProvider.expected, actual);
			}
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}

	public void testContainsPrioritOperatorが正しく乗余を判定することの確認() {

		try {
			Method method = Calcurator.class.getDeclaredMethod(
					"containsPrioritOperator", String.class);
			method.setAccessible(true);
			assertTrue((Boolean) method.invoke(new Calcurator(), "1×5"));
			assertTrue((Boolean) method.invoke(new Calcurator(), "3÷2"));
			assertTrue((Boolean) method.invoke(new Calcurator(), "3÷2×5"));
			assertFalse((Boolean) method.invoke(new Calcurator(), "1"));

		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}

	}

	private class CalcFactoryDataProvider {
		private String target;
		private String expected;

		CalcFactoryDataProvider(String target, String expected) {
			this.target = target;
			this.expected = expected;
		}
	}

	public void testCalcFactoryが正しく加減乗余のチェインインスタンスを作成していることの確認() {
		Method method;
		try {
			method = Calcurator.class.getDeclaredMethod("calcFactory",
					String.class);
			method.setAccessible(true);
			List<CalcFactoryDataProvider> list = new ArrayList<CalcFactoryDataProvider>();
			list.add(new CalcFactoryDataProvider("1+2", "3"));
			list.add(new CalcFactoryDataProvider("1+2-3", "0"));
			list.add(new CalcFactoryDataProvider("1×2", "2"));
			list.add(new CalcFactoryDataProvider("10÷2", "5"));
			list.add(new CalcFactoryDataProvider("10÷2×5", "25"));
			for (CalcFactoryDataProvider dataProvider : list) {
				Calc actual = (Calc) method.invoke(new Calcurator(),
						dataProvider.target);
				assertEquals(dataProvider.expected, actual.interpret());
			}

		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}

	private class CalcMultiAndDivideDataProvider {
		private String target;
		private String expected;

		CalcMultiAndDivideDataProvider(String target, String expected) {
			this.target = target;
			this.expected = expected;
		}
	}

	public void testCalcMultiAndDivideが正しく乗余の計算ができることの確認() {
		Method method;
		try {
			method = Calcurator.class.getDeclaredMethod("calcMultiAndDivide",
					String.class);
			method.setAccessible(true);
			List<CalcMultiAndDivideDataProvider> list = new ArrayList<CalcMultiAndDivideDataProvider>();
			list.add(new CalcMultiAndDivideDataProvider("1×2", "2"));
			list.add(new CalcMultiAndDivideDataProvider("10÷2", "5"));
			list.add(new CalcMultiAndDivideDataProvider("10÷2×5", "25"));
			for (CalcMultiAndDivideDataProvider dataProvider : list) {
				String actual = (String) method.invoke(new Calcurator(),
						dataProvider.target);
				assertEquals(dataProvider.expected, actual);
			}

		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}

	public void testCaluculateで正しく計算されることの確認() {
		Calcurator calculator = new Calcurator();
		assertEquals("7", calculator.caluculate("2+5"));
		assertEquals("-3", calculator.caluculate("2-5"));
		assertEquals("10", calculator.caluculate("2×5"));
		assertEquals("5", calculator.caluculate("10÷2"));
	}

}
