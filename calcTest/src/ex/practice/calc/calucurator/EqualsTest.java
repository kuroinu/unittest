package ex.practice.calc.calucurator;

import junit.framework.TestCase;

public class EqualsTest extends TestCase {

	public void testInterpret() {
		assertEquals("100", new Equals("100").interpret());
	}

}
