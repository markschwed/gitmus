package simulation.lib.counter;


/**
 * This class implements a discrete time counter
 */
public class DiscreteCounter extends Counter {

	/**
	 * Basic constructor
	 * @param variable the variable to observe
	 */
	public DiscreteCounter(String variable) {
		super(variable, "counter type: discrete-time counter");
	}
	
	/**
	 * Basic constructor
	 * @param variable the variable to observe
	 */
	protected DiscreteCounter(String variable, String type) {
		super(variable, type);
	}	
	
	/**
	 * @see Counter#getMean()
	 */
	@Override
	public double getMean() {
		/**
		 * Problem 2.1.1 - getMean
		 * Implement this function!
		 * Hint: See course syllabus 1.4.1 ff.
		 */
		//Mark
		long sam = getNumSamples();
		if(sam <= 0) {
			return 0;
		} else {
			return sumPowerOne/sam;
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
		 * Hint: See course syllabus 1.4.1 ff.
		 */
		//Mark
		long sam = getNumSamples();
		double mean = getMean();
		if(sam <= 1) {
			return 0;
		} else {
			return (sam/(sam-1))*(sumPowerTwo/sam - mean*mean);
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
		 * Hint: See course syllabus 1.5.1
		 */
		//Mark
		increaseSumPowerOne(x);
		increaseSumPowerTwo(x);
		//Mark out
	}
}
