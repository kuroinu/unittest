package ex.practice.calc.validator;

import junit.framework.TestCase;

public class LastCharIsNumberValidatorTest extends TestCase {

	public void testSetNextHandlerの次項目設定の確認() {
		try {
			new LastCharIsNumberValidator()
					.setNextHandler(new LastCharIsNumberValidator());
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	public void testIsCongruenceで数値で終わっていればTrueとなることの確認() {
		assertTrue(new LastCharIsNumberValidator().isCongruence("102"));
		assertTrue(new LastCharIsNumberValidator().isCongruence("0"));
		assertTrue(new LastCharIsNumberValidator().isCongruence("100+10"));
	}

	public void testIsCongruenceで数値いがいで終わっていればFalsとなることの確認() {
		assertFalse(new LastCharIsNumberValidator().isCongruence("."));
		assertFalse(new LastCharIsNumberValidator().isCongruence(""));
		assertFalse(new LastCharIsNumberValidator().isCongruence("+"));
		assertFalse(new LastCharIsNumberValidator().isCongruence("100+"));
	}

}
