/**
 * 
 */
package simulation.lib.randVars.continous;

import simulation.lib.randVars.RandVar;
import simulation.lib.rng.RNG;

/*
 * Problem 2.3.2 - implement this class (section 3.2.2 in course syllabus)
 * !!! If an abstract class method does not make sense to be implemented in a particular RandVar class,
 * an UnsupportedOperationException should be thrown !!!
 *
 * Exponential distributed random variable.
 */
public class Exponential extends RandVar {
	//Mark
	private double lambda;
	//Mark out
	
	//Mark
	/**
	 * Constructor
	 * @param rng random number generator
	 * @param lambd lambda
	 */
	public Exponential(RNG rng, double lambd) {
		super(rng);
		// Auto-generated constructor stub
		lambda = lambd;
	}
	//Mark out	

	@Override
	public double getRV() {
		//Auto-generated method stub
		//Mark
		double u = rng.rnd();
		return -Math.log(u)/lambda;
		//Mark out
	}

	@Override
	public double getMean() {
		//Auto-generated method stub
		//Mark
		return 1/lambda;
		//Mark out
	}

	@Override
	public double getVariance() {
		//Auto-generated method stub
		//Mark
		return 1/(lambda*lambda);
		//Mark out
	}

	@Override
	public void setMean(double m) {
		// Auto-generated method stub
		//Mark
		if (m<=0) {
			throw new IllegalArgumentException("mean cannot be lower or equal to 0.");
		} else {
			lambda = 1/m;
		}
		//Mark out
	}

	@Override
	public void setStdDeviation(double s) {
		// Auto-generated method stub
		//Mark
		setMean(s);
		//Mark out
	}

	@Override
	public void setMeanAndStdDeviation(double m, double s) {
		// Auto-generated method stub
		//Mark
		if (m!=s) {
			throw new IllegalArgumentException("mean cannot be different from stddev.");
		} else {
			setMean(m);
		}
		//Mark out

	}

	@Override
	public String getType() {
		// Auto-generated method stub
		//Mark
		return "Exponential";
		//Mark out
	}

	@Override
	public String toString() {
		// Auto-generated method stub
		return super.toString() + 
		"\tlambda: " + lambda + "\n";
	}
	
}
