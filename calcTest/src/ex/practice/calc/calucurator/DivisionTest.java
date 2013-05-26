package ex.practice.calc.calucurator;

import junit.framework.TestCase;

public class DivisionTest extends TestCase {

	public void testInterpret() {
		assertEquals("10",
				(new Division("1000")).setNextElement(new Division("100"))
						.interpret());
		assertEquals("0",
				(new Division("1000")).setNextElement(new Division("0"))
						.interpret());
	}

}
