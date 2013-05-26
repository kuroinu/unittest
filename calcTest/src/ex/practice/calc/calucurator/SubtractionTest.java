package ex.practice.calc.calucurator;

import junit.framework.TestCase;

public class SubtractionTest extends TestCase {
	public void testInterpret() {
		Calc calc = (new Subtraction("10")).setNextElement(new Subtraction(
				"100"));
		assertEquals("-90", calc.interpret());
	}
}
