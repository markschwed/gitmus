package study;

import simulation.lib.counter.DiscreteConfidenceCounter;
import simulation.lib.randVars.continous.ErlangK;
import simulation.lib.randVars.continous.Exponential;
import simulation.lib.randVars.continous.HyperExponential;
import simulation.lib.randVars.continous.Normal;
import simulation.lib.rng.StdRNG;

public class DCCTest {

    public static void main(String[] args) {
        testDCC();
    }

    public static void testDCC() {
    	
        // 3.1.3
    	StdRNG rng = new StdRNG();
    	double mean = 10;
    	double[] cvar = {0.25, 0.5, 1, 2, 4};
    	int[] n = {5, 10, 50, 100};
    	double[] alpha = {0.1,0.05};
    	int numberexperiments = 500;

    	for (double alph : alpha) {
    		DiscreteConfidenceCounter dcc = new DiscreteConfidenceCounter("DiscreteConfidenceCounter", alph);
    		System.out.println("Normal distribution");
    		System.out.println("Confidence interval: " + (1-alph) + "; Alpha: " + alph);
    		System.out.println("Number of experiments: " + numberexperiments);
    		System.out.println();
			Normal rvNormal = new Normal(rng);
			for (double cv : cvar) {
				rvNormal.setMeanAndCvar(mean, cv);
				System.out.println("        Mean: " + mean);
				System.out.println("        Cvar: " + cv);
				for (int nsamples : n) {
	    			System.out.println("    Numer of samples: " + nsamples);
    				int successfulexperiments = 0;
    				for (int j=0; j<numberexperiments; j++) {
    					dcc.reset();
        				for (int i=0; i<nsamples; i++) {
        					dcc.count(rvNormal.getRV());
        				}
        				//System.out.println("            " + dcc.report());
        				//System.out.println();        				
        				if (mean >= dcc.getLowerBound() && mean <= dcc.getUpperBound()) {
        					successfulexperiments++;
        				}
    				}  				
    				System.out.println("        Fraction of successful experiments: " + (double) successfulexperiments/numberexperiments);
				}
				System.out.println();
			}		
    	}
    	
    	// 3.1.4
    	cvar = new double[] {0.25, 0.5, 1};
    	for (double alph : alpha) {
    		DiscreteConfidenceCounter dccerlang = new DiscreteConfidenceCounter("DiscreteConfidenceCounter", alph);
    		System.out.println("ErlangK distribution");
    		System.out.println("Confidence interval: " + (1-alph) + "; Alpha: " + alph);
    		System.out.println("Number of experiments: " + numberexperiments);
    		System.out.println();
    		ErlangK rvErlangK = new ErlangK(rng, 10, 1);
			for (double cv : cvar) {
				rvErlangK.setMeanAndCvar(mean, cv);
				System.out.println("        Mean: " + mean);
				System.out.println("        Cvar: " + cv);
				for (int nsamples : n) {
	    			System.out.println("    Numer of samples: " + nsamples);
    				int successfulexperiments = 0;
    				for (int j=0; j<numberexperiments; j++) {
    					dccerlang.reset();
        				for (int i=0; i<nsamples; i++) {
        					dccerlang.count(rvErlangK.getRV());
        				}
        				//System.out.println("            " + dcc.report());
        				//System.out.println();        				
        				if (mean >= dccerlang.getLowerBound() && mean <= dccerlang.getUpperBound()) {
        					successfulexperiments++;
        				}
    				}  				
    				System.out.println("        Fraction of successful experiments: " + (double) successfulexperiments/numberexperiments);
				}
				System.out.println();
			}		
    	}
    	
    	cvar = new double[] {1};
    	for (double alph : alpha) {
    		DiscreteConfidenceCounter dccexponential = new DiscreteConfidenceCounter("DiscreteConfidenceCounter", alph);
    		System.out.println("Exponential distribution");
    		System.out.println("Confidence interval: " + (1-alph) + "; Alpha: " + alph);
    		System.out.println("Number of experiments: " + numberexperiments);
    		System.out.println();
    		Exponential rvExponential = new Exponential(rng, 10);
			for (double cv : cvar) {
				rvExponential.setMeanAndCvar(mean, cv);
				System.out.println("        Mean: " + mean);
				System.out.println("        Cvar: " + cv);
				for (int nsamples : n) {
	    			System.out.println("    Numer of samples: " + nsamples);
    				int successfulexperiments = 0;
    				for (int j=0; j<numberexperiments; j++) {
    					dccexponential.reset();
        				for (int i=0; i<nsamples; i++) {
        					dccexponential.count(rvExponential.getRV());
        				}
        				//System.out.println("            " + dcc.report());
        				//System.out.println();        				
        				if (mean >= dccexponential.getLowerBound() && mean <= dccexponential.getUpperBound()) {
        					successfulexperiments++;
        				}
    				}  				
    				System.out.println("        Fraction of successful experiments: " + (double) successfulexperiments/numberexperiments);
				}
				System.out.println();
			}		
    	}
    	
    	cvar = new double[] {1, 2, 4};
    	for (double alph : alpha) {
    		DiscreteConfidenceCounter dcchyper = new DiscreteConfidenceCounter("DiscreteConfidenceCounter", alph);
    		System.out.println("Hyperexponential distribution");
    		System.out.println("Confidence interval: " + (1-alph) + "; Alpha: " + alph);
    		System.out.println("Number of experiments: " + numberexperiments);
    		System.out.println();
    		HyperExponential rvHyper;
			for (double cv : cvar) {
				rvHyper = new HyperExponential(rng, mean, cv);
				System.out.println("        Mean: " + mean);
				System.out.println("        Cvar: " + cv);
				for (int nsamples : n) {
	    			System.out.println("    Numer of samples: " + nsamples);
    				int successfulexperiments = 0;
    				for (int j=0; j<numberexperiments; j++) {
    					dcchyper.reset();
        				for (int i=0; i<nsamples; i++) {
        					dcchyper.count(rvHyper.getRV());
        				}
        				//System.out.println("            " + dcc.report());
        				//System.out.println();        				
        				if (mean >= dcchyper.getLowerBound() && mean <= dcchyper.getUpperBound()) {
        					successfulexperiments++;
        				}
    				}  				
    				System.out.println("        Fraction of successful experiments: " + (double) successfulexperiments/numberexperiments);
				}
				System.out.println();
			}		
    	}
    }
}
