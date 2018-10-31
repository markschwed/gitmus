package simulation.lib.counter;

/**
 * This class implements a discrete time confidence counter
 */
public class DiscreteConfidenceCounter extends DiscreteCounter{
	/*	Row 1: degrees of freedom
     *  Row 2: alpha 0.01
     *  Row 3: alpha 0.05
     *  Row 4: alpha 0.10
     */
    private double tAlphaMatrix[][] = new double[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 1000000},
            {63.657, 9.925, 5.841, 4.604, 4.032, 3.707, 3.499, 3.355, 3.250, 3.169, 2.845, 2.750, 2.704, 2.678, 2.660, 2.648, 2.639, 2.632, 2.626, 2.576},
            {12.706, 4.303, 3.182, 2.776, 2.571, 2.447, 2.365, 2.306, 2.262, 2.228, 2.086, 2.042, 2.021, 2.009, 2.000, 1.994, 1.990, 1.987, 1.984, 1.960},
            {6.314, 2.920, 2.353, 2.132, 2.015, 1.943, 1.895, 1.860, 1.833, 1.812, 1.725, 1.697, 1.684, 1.676, 1.671, 1.667, 1.664, 1.662, 1.660, 1.645}};
	
    private double alpha;
	
    /**
     * Basic constructor
     * @param variable - the variable to observe
     */
    public DiscreteConfidenceCounter(String variable) {
		// "Unless otherwise specified, a default value of 0.05 for alpha should be chosen."
    	this(variable, 0.05);
	}
    
    /**
     * Basic constructor
     * @param variable - the variable to observe
     * @param alpha - level of significance
     */
    public DiscreteConfidenceCounter(String variable, double alpha) {
		// Setting the default type
    	this(variable, "counter type: discrete-time confidence counter", alpha);
	}
    
    /**
     * Basic constructor
     * @param variable - the variable to observe
     * @param type - type of counter
     * @param alpha - level of significance
     */
    public DiscreteConfidenceCounter(String variable, String type, double alpha) {
		super(variable, type);
		if (alpha < 0 || alpha > 1) {
			throw new IllegalArgumentException("The provided alpha is not between zero and one.");
		}
		this.alpha=alpha;
	}

    /**
     * Returns the t_{n-1, (1-alpha/2)} value for a given degree of freedom.
     * 
     * @param degsOfFreedom - number of samples n minus one
     */
    public double getT(long degsOfFreedom) {
    	// Have at least a degree of freedom of one
    	if (degsOfFreedom < 1) {
    		System.out.println("The provided degrees of freedom cannot be lower than 1.");
    		return 0;
    	}
    	
    	// "If degsOfFreedom is out of the values range, the maximum number for degsOfFreedom 
    	//  in the quantile array should be chosen."
    	if(degsOfFreedom > tAlphaMatrix[0][tAlphaMatrix[0].length-1]) {
    		//System.out.println("The provided degrees of freedom are out of range and are now capped.");
    		degsOfFreedom = (long) tAlphaMatrix[0][tAlphaMatrix[0].length-1];
    	}
    	//System.out.println("Degrees of freedom: " + degsOfFreedom);
    	
    	// Get the row of the table based on the alpha value
    	int row = getRow();
    	//System.out.println("The most suitable alpha value is provided for row " + row + ".");
    	
    	// Search if the array contains the degree of freedom provided
    	int column = -1;
    	for (int i=0; i<tAlphaMatrix[0].length; i++) {
    		if (tAlphaMatrix[0][i] == degsOfFreedom) {
    			column = i;
    			break;
    		}
    	}
    	if (column >=0) {
    		//System.out.println("Exact degrees of freedom are found in the given table in column " + column + ".");
    		double t = tAlphaMatrix[row - 1][column];
    		//System.out.println("T: " + t);
    		return t;
    	}
    	
    	// "If degsOfFreedom is between two entries in the array, linearInterpol should return
    	//  a linearly interpolated value."
    	//System.out.println("Did not find exact degrees of freedom in the given table so going for interpolation.");
    	int indexlow = 0;
    	while (tAlphaMatrix[0][indexlow] < degsOfFreedom) {
    		indexlow++;
    	}
    	double t = linearInterpol(tAlphaMatrix[0][indexlow-1], tAlphaMatrix[0][indexlow], tAlphaMatrix[row - 1][indexlow-1], tAlphaMatrix[row - 1][indexlow], degsOfFreedom);
    	//System.out.println("T: " + t);
		return t;
	}
    
    /**
     * "linearInterpol should return a linearly interpolated value."
     * @param dflow - xxx
     * @param dfhigh - xxx
     * @param tlow - xxx
     * @param thigh - xxx
     * @param degsOfFreedom - xxx
     */
    private double linearInterpol(double dflow, double dfhigh, double tlow, double thigh, long degsOfFreedom) {
		return tlow + (thigh-tlow)/(dfhigh-dflow) * (degsOfFreedom-dflow);
	}
    
    /**
     * "getRow should select the most suitable quantile row for a value of alpha. Choose
     *	the quantiles for 0.01 if alpha < 0.05, the quantiles for 0:05 if 0:05 <= alpha <
     *  0.1, and the quantiles for 0.1 otherwise!"
     */
    private int getRow() {
		if (alpha < 0.05) {
			//System.out.println("We now calculate with alpha: 0.01");
			return 2;
		} else if (alpha < 0.1) {
			//System.out.println("We now calculate with alpha: 0.05");
			return 3;
		} else {
			//System.out.println("We now calculate with alpha: 0.1");
			return 4;
		}
	}
    
    /**
     * xxx
     */
    public double getLowerBound() {
    	return getMean() - getBound();
	}
    
    /**
     * xxx
     */
    public double getUpperBound() {
    	return getMean() + getBound();
	}
    
    /**
     * xxx
     */
    public double getBound() {
		return getT(getNumSamples()-1) * Math.sqrt(getVariance()/getNumSamples());
	}
    
    /**
     * xxx
     */
    @Override
    public String report() {
        String out = super.report();
        out += ("\n" + "\talpha = " + alpha + "\n" +
                "\tt(1-alpha/2) = " + getT(getNumSamples() - 1) + "\n" +
                "\tlower bound = " + getLowerBound() + "\n" +
                "\tupper bound = " + getUpperBound());
        return out;
    }
    
    /**
     * xxx
     * @param outputdir - xxx
     */
    @Override
    public void csvReport(String outputdir) {
        String content = observedVariable + ";" + getNumSamples() + ";" + getMean() + ";" + getVariance() + ";" + getStdDeviation() + ";" +
                getCvar() + ";" + getMin() + ";" + getMax() + ";" + alpha + ";" + getT(getNumSamples() - 1) + ";" + getLowerBound() + ";" +
                getUpperBound() + "\n";
        String labels = "#counter ; numSamples ; MEAN; VAR; STD; CVAR; MIN; MAX;alpha;t(1-alpha/2);lowerBound;upperBound\n";
        writeCsv(outputdir, content, labels);
    }

}
