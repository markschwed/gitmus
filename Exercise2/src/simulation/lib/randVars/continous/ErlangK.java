package simulation.lib.randVars.continous;

import simulation.lib.randVars.RandVar;
import simulation.lib.rng.RNG;

/*
 * Problem 2.3.2 - implement this class (section 3.2.3 in course syllabus)
 * !!! If an abstract class method does not make sense to be implemented in a particular RandVar class,
 * an UnsupportedOperationException should be thrown !!!
 *
 * Erlang-k distributed random variable.
 */
public class ErlangK extends RandVar {
	//Mark
	private double lambda;
	private double k;
	//Mark out

	//Mark
	/**
	 * Constructor
	 * @param rng random number generator
	 * @param lambd lambda
	 * @param ka k
	 */
	public ErlangK(RNG rng, double lambd, double ka) {
		super(rng);
		// Auto-generated constructor stub
		lambda = lambd;
		k = ka;
	}
	//Mark out

	@Override
	public double getRV() {
		//Auto-generated method stub
		//Mark	
		double tmp = 1;
        for (int i = 0; i < k; i++) {
            tmp *= rng.rnd();
        }
        return -Math.log(tmp)/lambda;
		//Mark out
	}

	@Override
	public double getMean() {
		//Auto-generated method stub
		//Mark
		return k/lambda;
		//Mark out
	}

	@Override
	public double getVariance() {
		//Auto-generated method stub
		//Mark
		return k/(lambda*lambda);
		//Mark out
	}

	@Override
	public void setMean(double m) {
		//Auto-generated method stub
		//Mark
		if (m<=0) {
			throw new IllegalArgumentException("mean cannot be lower or equal to 0.");
		} else {
			lambda = k/m;
		}
		//Mark out
	}

	@Override
	public void setStdDeviation(double s) {
		// Auto-generated method stub
		//Mark
		if (s<=0) {
			throw new IllegalArgumentException("std dev cannot be lower or equal to 0.");
		} else {
			lambda = Math.sqrt(k/(s*s));
		}
		//Mark out
	}

	@Override
	public void setMeanAndStdDeviation(double m, double s) {
		// Auto-generated method stub
		if (m<=0 || s<=0) {
			throw new IllegalArgumentException("mean or std dev cannot be lower or equal to 0.");
		} else {
			k = (int) Math.floor(Math.pow(m/s, 2));
			lambda = k/m;
		}
	}

	@Override
	public String getType() {
		// Auto-generated method stub
		//Mark
		return "ErlangK";
		//Mark out
	}

	@Override
	public String toString() {
		// Auto-generated method stub
		return super.toString() + 
		"\tlambda: " + lambda + "\n" + 
		"\tk: " + k + "\n";
	}		
}
