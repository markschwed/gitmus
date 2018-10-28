package simulation.lib.randVars.continous;

import simulation.lib.randVars.RandVar;
import simulation.lib.rng.RNG;
import static java.lang.Math.*;

/*
 * Problem 2.3.2 - implement this class (section 3.2.4 in course syllabus)
 * !!! If an abstract class method does not make sense to be implemented in a particular RandVar class,
 * an UnsupportedOperationException should be thrown !!!
 *
 * Hyperexponential distributed random variable.
 */
public class HyperExponential extends RandVar {
	//Mark
	private double lambda1;
	private double lambda2;
	private double p1;
	private double p2;
	//Mark out

	//Mark
	/**
	 * Constructor
	 * @param rng random number generator
	 * @param lambd1 lambda1
	 * @param lambd2 lambda2
	 * @param pe1 p1
	 * @param pe1 p2
	 */
	public HyperExponential(RNG rng, double lambd1, double lambd2, double pe1, double pe2) {
		super(rng);
		// Auto-generated constructor stub
		if (lambd1 == 0 || lambd2 == 0 || pe1/lambd1 != pe2/lambd2) {
			throw new IllegalArgumentException("p1/lambda1 needs to be = p2/lambda2 and lambda1, lambda2 != 0");
		} else {
			lambda1 = lambd1;
			lambda2 = lambd2;
			p1 = pe1;
			p2 = pe2;
		}
	}
	//Mark out

	@Override
	public double getRV() {
		// Auto-generated method stub
		//Mark
		double u1 = rng.rnd();
		double u2 = rng.rnd();
		if(u1 <= p1) {
			return -Math.log(u2)/lambda1;
		} else {
			return -Math.log(u2)/lambda2;
		}
		//Mark out
	}

	@Override
	public double getMean() {
		// Auto-generated method stub
		//Mark
		return p1/lambda1 + p2/lambda2;
		//Mark out
	}

	@Override
	public double getVariance() {
		//Auto-generated method stub
		//Mark
		double mean = getMean();
		double m2 = 2 * p1 / Math.pow(lambda1, 2) + 2 * p2 / Math.pow(lambda2, 2);
		return m2 - mean*mean;
		//Mark out
	}

	@Override
	public void setMean(double m) {
		// Auto-generated method stub
		//Mark
		if (m<=0) {
			throw new IllegalArgumentException("mean cannot be lower or equal to 0.");
		} else {
			double std = getStdDeviation();
			lambda1 = 1 / m * (1 + Math.sqrt((Math.pow(std/m, 2) - 1) / (Math.pow(std/m, 2) + 1)));
			lambda2 = 1 / m * (1 - Math.sqrt((Math.pow(std/m, 2) - 1) / (Math.pow(std/m, 2) + 1)));
			p1 = 1 / (1 + lambda2/lambda1);
			p2 = 1 - p1;
		}
		//Mark out

	}

	@Override
	public void setStdDeviation(double s) {
		// Auto-generated method stub
		//Mark
		double mean = getMean();
		lambda1 = 1 / mean * (1 + Math.sqrt((Math.pow(s/mean, 2) - 1) / (Math.pow(s/mean, 2) + 1)));
		lambda2 = 1 / mean * (1 - Math.sqrt((Math.pow(s/mean, 2) - 1) / (Math.pow(s/mean, 2) + 1)));
		p1 = 1 / (1 + lambda2/lambda1);
		p2 = 1 - p1;
		//Mark out

	}

	@Override
	public void setMeanAndStdDeviation(double m, double s) {
		// Auto-generated method stub
		//Mark
		if (m<=0) {
			throw new IllegalArgumentException("mean cannot be lower or equal to 0.");
		} else {
			lambda1 = 1 / m * (1 + Math.sqrt((Math.pow(s/m, 2) - 1) / (Math.pow(s/m, 2) + 1)));
			lambda2 = 1 / m * (1 - Math.sqrt((Math.pow(s/m, 2) - 1) / (Math.pow(s/m, 2) + 1)));
			p1 = 1 / (1 + lambda2/lambda1);
			p2 = 1 - p1;
		}
		//Mark out
	}

	@Override
	public String getType() {
		// Auto-generated method stub
		//Mark
		return "Hyperexponential";
		//Mark out
	}

	@Override
	public String toString() {
		// Auto-generated method stub
		return super.toString() + 
		"\tlambda1: " + lambda1 + "\n" + 
		"\tlambda2: " + lambda2 + "\n" + 
		"\tp1: " + p1 + "\n" + 
		"\tp2: " + p2 + "\n";
	}
}
