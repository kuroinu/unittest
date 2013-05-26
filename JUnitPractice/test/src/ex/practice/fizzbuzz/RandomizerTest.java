package ex.practice.fizzbuzz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RandomizerTest extends Randomizer {
	Randomizer randomizer;

	@Before
	public void setUp() throws Exception {
		randomizer = new Randomizer();
	}

	@Test
	public void genericで数値が生成される() {
		try {
			randomizer.generic();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
