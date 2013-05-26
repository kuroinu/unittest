package ex.practice.calc.calucurator;

import junit.framework.TestCase;

public class MultiplicationTest extends TestCase {
	public void testInterpret() {
		Calc calc = (new Multiplication("10"))
				.setNextElement(new Multiplication("100"));
		assertEquals("1000", calc.interpret());
	}
}
