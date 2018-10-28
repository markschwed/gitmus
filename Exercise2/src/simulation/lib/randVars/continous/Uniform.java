package simulation.lib.randVars.continous;

import simulation.lib.randVars.RandVar;
import simulation.lib.rng.RNG;

/*
 * Problem 2.3.2 - implement this class (section 3.2.1 in course syllabus)
 * !!! If an abstract class method does not make sense to be implemented in a particular RandVar class,
 * an UnsupportedOperationException should be thrown !!!
 *
 * Uniform distributed random variable.
 */
public class Uniform extends RandVar {
	//Mark
	private double lowerBound;
	private double upperBound;
	//Mark out
	
	//Mark
	/**
	 * Constructor
	 * @param rng random number generator
	 * @param lowerB lowerBound
	 * @param upperB upperBound
	 */
	public Uniform(RNG rng, double lowerB, double upperB) {
		super(rng);
		if (lowerB<=upperB) {
			lowerBound = lowerB;
			upperBound = upperB;
		} else {
			throw new UnsupportedOperationException("lowerB is higher than upperB");
		}
	}
	//Mark out

	@Override
	public double getRV() {
		// Auto-generated method stub
		//Mark
		double u = rng.rnd();
		return lowerBound+(upperBound-lowerBound)*u;
		//Mark out
	}

	@Override
	public double getMean() {
		// Auto-generated method stub
		//Mark
		return (lowerBound+upperBound)/2;
		//Mark out
	}

	@Override
	public double getVariance() {
		// Auto-generated method stub
		//Mark
		return Math.pow((upperBound-lowerBound),2)/12;
		//Mark out
	}

	@Override
	public void setMean(double m) {
		// Auto-generated method stub
		//Mark
		setMeanAndStdDeviation(m, Math.sqrt(getVariance()));
		//Mark out
	}

	@Override
	public void setStdDeviation(double s) {
		// Auto-generated method stub
		//Mark
		setMeanAndStdDeviation(getMean(), s);
		//Mark out
	}

	@Override
	public void setMeanAndStdDeviation(double m, double s) {
		// Auto-generated method stub
		//Mark
		lowerBound = m-Math.sqrt(3)*s;
		upperBound = m+Math.sqrt(3)*s;
		//Mark out
	}

	@Override
	public String getType() {
		// Auto-generated method stub
		//Mark
		return "Uniform";
		//Mark out
	}

	@Override
	public String toString() {
		// Auto-generated method stub
		return super.toString() + 
		"\tlowerBound: " + lowerBound + "\n" +
		"\tupperBound: " + upperBound + "\n";
	}
	
}
