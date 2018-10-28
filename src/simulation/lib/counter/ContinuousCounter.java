package simulation.lib.counter;

import simulation.lib.Simulator;

/**
 * This class implements a continuous time counter / time weighted counter
 */
public class ContinuousCounter extends Counter {
	private long lastSampleTime;
	private long firstSampleTime;
	private double lastSampleSize;
	private Simulator sim;
	
	/**
	 * Constructor
	 * @param variable the variable to observe
	 * @param sim the considered simulator
	 */	
	public ContinuousCounter(String variable, Simulator sim) {
		super(variable, "counter type: continuous-time counter");
		this.sim = sim;
	}
	
	/**
	 * @see Counter#getMean()
	 */
	@Override
	public double getMean() {
		/**
		 * Problem 2.1.1 - getMean
		 * Implement this function!
		 * Hint: See course syllabus 1.5.3.2
		 */
		//Mark
		long interval = lastSampleTime - firstSampleTime;
		if(interval <= 0) {
			return 0;
		} else {
			return sumPowerOne/interval;
		}
		//Mark out
	}
	
	/**
	 * @see Counter#getVariance()
	 */
	@Override
	public double getVariance() {
		/**
		 * Problem 2.1.1 - getVariance
		 * Implement this function!
		 * Hint: See course syllabus 1.5.3.2 and 1.4.1 ff.
		 */
		//Mark
		long interval = lastSampleTime - firstSampleTime;
		double mean = getMean();
		if(interval <= 0) {
			return 0;
		} else {
			return sumPowerTwo/interval - mean*mean;
		}
		//Mark out
	}

	/**
	 * @see Counter#count(double)
	 */
	@Override
	public void count(double x) {
		super.count(x);
		/**
		 * Problem 2.1.1 - count
		 * Implement this function!
		 * Update the counter's internal data for the calculation of empirical moments
		 * Also update lastSampleSize and lastSampleTime
		 * Hint: See course syllabus 1.5.3.2
		 */
		//Mark
		long ti = sim.getSimTime();
		increaseSumPowerOne(lastSampleSize * (ti - lastSampleTime));
		increaseSumPowerTwo(lastSampleSize * lastSampleSize * (ti - lastSampleTime));
		lastSampleSize = x;
		lastSampleTime = ti;
		//Mark out
	}

	/**
	 * @see Counter#reset()
	 */
	@Override
	public void reset() {
		super.reset();
		firstSampleTime = sim.getSimTime();
		lastSampleTime = sim.getSimTime();
		lastSampleSize = 0;
	}
}
