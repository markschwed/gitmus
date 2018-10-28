package simulation.lib.rng;

import java.util.Random;

/**
 * Standard random number generator
 */
public class StdRNG extends RNG {
	Random rng;
	
	public StdRNG() {
		super();
	}
	/**
	 * Constructor with current milliseconds as seed
	 * @param currentTimeMillis
	 */
	public StdRNG(long currentTimeMillis) {
		super(currentTimeMillis);
	}

	/**
	 * @see RNG#setSeed(long)
	 */
	@Override
	public void setSeed(long seed) {
		/*
		 * Problem 2.3.1 - setSeed
		 * Update the seed of your random number generator rng (Hint: rng can be null @see RNG)
		 */
		
		//Mark
		if (rng == null) {
			rng = new Random(seed);
		} else {
			rng.setSeed(seed);
		}
		//Mark out
	}

	/**
	 * @see RNG#rnd()
	 */
	@Override
	public double rnd() {
		/*
		 * Problem 2.3.1 - rnd
		 * Create a random value: 0 < value < 1
		 */
		double tmp;
		//Mark
		do {
			tmp = rng.nextDouble();
		} while (tmp == 0);
		return tmp;
		//Mark out
	}
}
