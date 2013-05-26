package ex.practice.fizzbuzz;

import java.util.Random;

public class Randomizer implements IRandmizer {
	private static final int SEED = 10000;

	/* (非 Javadoc)
	 * @see ex.practice.fizzbuzz.IRandmizer#generic()
	 */
	@Override
	public int generic() {
		return new Random().nextInt(SEED);
	}
}
