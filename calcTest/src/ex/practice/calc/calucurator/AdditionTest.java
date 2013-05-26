package ex.practice.calc.calucurator;

import java.lang.reflect.Method;

import junit.framework.TestCase;

public class AdditionTest extends TestCase {

	public void testSetAndGgetValue() {
		Calc calc = new Addition("10");
		assertEquals("10", calc.getValue());
	}

	public void testSetNextElement() {
		Calc calc = new Addition("10");
		try {
			Method method = Calc.class.getDeclaredMethod("hasNextElement");
			method.setAccessible(true);
			assertFalse((Boolean) method.invoke(calc));
			calc.setNextElement(new Addition("10"));
			assertTrue((Boolean) method.invoke(calc));
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}

	public void testInterpret() {
		Calc calc = (new Addition("10")).setNextElement(new Addition("100"));
		assertEquals("110", calc.interpret());
	}

}
