package ex.practice.calc.validator;

import junit.framework.TestCase;

public class NumericFormulaValidatorTest extends TestCase {

	private Validator validator;

	public void setUp() throws Exception {
		super.setUp();
		validator = new NumericFormulaValidator();
	}

	public void testValidが数式を正しく判定することの確認() {
		String[] testData = { "1+1", "2-4", "3×5", "4÷2", "10+5-100" };
		for (String data : testData) {
			assertTrue(validator.isCongruence(data));
		}
	}
	public void testValidが数式でないものをfalseで判定することの確認() {
		String[] testData = { "1+1-", "2-4+", "3×5×", "4÷2÷", "10+5-100=" };
		for (String data : testData) {
			assertFalse(validator.isCongruence(data));
		}
	}

}
