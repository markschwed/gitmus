package simulation.lib.counter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This class implements a discrete time autocorrelation counter
 */
public class DiscreteAutocorrelationCounter extends DiscreteCounter{
	
	private int maxLag;
	private double[] firstValues;
	private double[] lastValues;
	private double[] squaredSum;
	
	public DiscreteAutocorrelationCounter(String variable, int maxLag) {
		this(variable, "counter type: discrete autocorrelation counter", maxLag);
	}

	public DiscreteAutocorrelationCounter(String variable, String type, int maxLag) {
		super(variable);
		if(maxLag < 0) {
			throw new IllegalArgumentException("maxLag needs to be 0 or greater");
		}
		setMaxLag(maxLag);
		reset();
	}
	
	public long getMaxLag() {
		return maxLag;
	}
	
	public void setMaxLag(long maxLag) {
		this.maxLag = (int) maxLag;
		reset();
	}
	
	public void count(double x) {
		super.count(x);
		
		//TODO
	}
	
	public void reset() {
		super.reset();
		firstValues = new double[maxLag];
		lastValues = new double[maxLag];
		squaredSum = new double[maxLag+1];
	}
	
	public double getAutoCovariance(int lag) {
		if (lag > maxLag || lag > getNumSamples()) {
			throw new IllegalArgumentException("your lag is greater than maxLag or greater than the number of samples");
		}
		
		//TODO
		return 1;
	}

	public double getAutoCorrelation(int lag) {
		if (lag > maxLag) {
			throw new IllegalArgumentException("your lag is greater than maxLag");
		}
		if (getVariance() == 0) {
			return 1;
		}
		return getAutoCovariance(lag) / getVariance();
	}
	
	@Override
	public String report() {
		String out  = super.report();
		out += ("\n\tCorrelation/Covariance:\n");
		for(int i = 0; i <= (getNumSamples() < maxLag ? getNumSamples() : maxLag); i++){
			out += ("\t\tlag = " + i + "   " +
					"covariance = " + getAutoCovariance(i) + "   " +
					"correlation = " + getAutoCorrelation(i)+"\n");
		}
		return out;
	}
	
	@Override
	public void csvReport(String outputdir){
	    String content = "";
        for(int i = 0; i <= (getNumSamples() < maxLag ? getNumSamples() : maxLag); i++) {
            content += observedVariable + " (lag=" + i + ")" + ";" + getNumSamples() + ";" + getMean() + ";" +
                    getVariance() + ";" + getStdDeviation() + ";" + getCvar() + ";" + getMin() + ";" + getMax() + ";" +
                    getAutoCovariance(i) + ";" + getAutoCorrelation(i) + "\n";
        }
        String labels = "#counter ; numSamples ; MEAN; VAR; STD; CVAR; MIN; MAX; COV; CORR\n";
        writeCsv(outputdir, content, labels);
	}
}
