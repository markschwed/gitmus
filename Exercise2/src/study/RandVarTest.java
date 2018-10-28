package study;

import simulation.lib.counter.DiscreteCounter;
import simulation.lib.randVars.continous.Uniform;
import simulation.lib.rng.RNG;
import simulation.lib.rng.StdRNG;

/*
 * TODO Problem 2.3.3 and 2.3.4[Bonus] - implement this class
 * You can call your test from the main-method in SimulationStudy
 */
public class RandVarTest {

    public static void testRandVars() {
        // Auto-generated method stub
    	//Mark
    	System.out.println("Test of random variables:");
    	RNG rng = new StdRNG();
    	
    	//Uniform1
    	Uniform randVarUniform1 = new Uniform(rng, 0, 1);
    	randVarUniform1.setMeanAndCvar(1, 0.1);
    	System.out.println(randVarUniform1.toString());
    	DiscreteCounter dcUniform1mean = new DiscreteCounter("Uniform 1 mean");
    	for (int i=0;i<1000000;i++) {
    		double rv = randVarUniform1.getRV();
    		dcUniform1mean.count(rv);
    	}
    	System.out.println("\tUniform 1 Number of Samples:\t " + dcUniform1mean.getNumSamples());
    	System.out.println("\tUniform 1 Mean:\t\t\t " + dcUniform1mean.getMean());
    	System.out.println("\tUniform 1 Variance:\t\t " + dcUniform1mean.getVariance());
    	System.out.println("\tUniform 1 Cvar:\t\t\t " + dcUniform1mean.getStdDeviation()/dcUniform1mean.getMean());
    	
    	//Uniform2
    	Uniform randVarUniform2 = new Uniform(rng, 0, 1);
    	randVarUniform2.setMeanAndCvar(1, 1);
    	System.out.println(randVarUniform2.toString());
    	DiscreteCounter dcUniform2mean = new DiscreteCounter("Uniform 2 mean");
    	for (int i=0;i<1000000;i++) {
    		double rv = randVarUniform2.getRV();
    		dcUniform2mean.count(rv);
    	}
    	System.out.println("\tUniform 2 Number of Samples:\t " + dcUniform2mean.getNumSamples());
    	System.out.println("\tUniform 2 Mean:\t\t\t " + dcUniform2mean.getMean());
    	System.out.println("\tUniform 2 Variance:\t\t " + dcUniform2mean.getVariance());
    	System.out.println("\tUniform 2 Cvar:\t\t\t " + dcUniform2mean.getStdDeviation()/dcUniform2mean.getMean());

    	//Uniform3
    	Uniform randVarUniform3 = new Uniform(rng, 0, 1);
    	randVarUniform3.setMeanAndCvar(1, 2);
    	System.out.println(randVarUniform3.toString());
    	DiscreteCounter dcUniform3mean = new DiscreteCounter("Uniform 3 mean");
    	for (int i=0;i<1000000;i++) {
    		double rv = randVarUniform3.getRV();
    		dcUniform3mean.count(rv);
    	}
    	System.out.println("\tUniform 3 Number of Samples:\t " + dcUniform3mean.getNumSamples());
    	System.out.println("\tUniform 3 Mean:\t\t\t " + dcUniform3mean.getMean());
    	System.out.println("\tUniform 3 Variance:\t\t " + dcUniform3mean.getVariance());
    	System.out.println("\tUniform 3 Cvar:\t\t\t " + dcUniform3mean.getStdDeviation()/dcUniform3mean.getMean());

    	
    	System.out.println();
    	System.exit(0);
    	//Mark out
    }
}
