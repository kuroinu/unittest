package ex.practice.calc.validator;

import junit.framework.TestCase;

public class NumberValidatorTest extends TestCase {
	private Validator validator;

	public void setUp() throws Exception {
		super.setUp();
		validator = new NumberValidator();
	}

	public void test数値形式を正しく判定していることの確認() {
		assertTrue(validator.isCongruence("156"));
	}

	public void test数値形式以外をFalseに判定していることの確認() {
		assertFalse(validator.isCongruence("142.5"));
	}
}
