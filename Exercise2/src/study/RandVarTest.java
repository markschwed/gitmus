package study;

import simulation.lib.counter.DiscreteCounter;
import simulation.lib.randVars.continous.ErlangK;
import simulation.lib.randVars.continous.Exponential;
import simulation.lib.randVars.continous.HyperExponential;
import simulation.lib.randVars.continous.Uniform;
import simulation.lib.rng.RNG;
import simulation.lib.rng.StdRNG;

/*
 * Problem 2.3.3 and 2.3.4[Bonus] - implement this class
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
    	DiscreteCounter dcUniform1 = new DiscreteCounter("Uniform 1");
    	for (int i=0;i<1000000;i++) {
    		double rv = randVarUniform1.getRV();
    		dcUniform1.count(rv);
    	}
    	System.out.println("\tUniform 1 Number of Samples:\t " + dcUniform1.getNumSamples());
    	System.out.println("\tUniform 1 Mean:\t\t\t " + dcUniform1.getMean());
    	System.out.println("\tUniform 1 Variance:\t\t " + dcUniform1.getVariance());
    	System.out.println("\tUniform 1 Cvar:\t\t\t " + dcUniform1.getStdDeviation()/dcUniform1.getMean());
    	
    	//Uniform2
    	Uniform randVarUniform2 = new Uniform(rng, 0, 1);
    	randVarUniform2.setMeanAndCvar(1, 1);
    	System.out.println(randVarUniform2.toString());
    	DiscreteCounter dcUniform2 = new DiscreteCounter("Uniform 2");
    	for (int i=0;i<1000000;i++) {
    		double rv = randVarUniform2.getRV();
    		dcUniform2.count(rv);
    	}
    	System.out.println("\tUniform 2 Number of Samples:\t " + dcUniform2.getNumSamples());
    	System.out.println("\tUniform 2 Mean:\t\t\t " + dcUniform2.getMean());
    	System.out.println("\tUniform 2 Variance:\t\t " + dcUniform2.getVariance());
    	System.out.println("\tUniform 2 Cvar:\t\t\t " + dcUniform2.getStdDeviation()/dcUniform2.getMean());

    	//Uniform3
    	Uniform randVarUniform3 = new Uniform(rng, 0, 1);
    	randVarUniform3.setMeanAndCvar(1, 2);
    	System.out.println(randVarUniform3.toString());
    	DiscreteCounter dcUniform3 = new DiscreteCounter("Uniform 3");
    	for (int i=0;i<1000000;i++) {
    		double rv = randVarUniform3.getRV();
    		dcUniform3.count(rv);
    	}
    	System.out.println("\tUniform 3 Number of Samples:\t " + dcUniform3.getNumSamples());
    	System.out.println("\tUniform 3 Mean:\t\t\t " + dcUniform3.getMean());
    	System.out.println("\tUniform 3 Variance:\t\t " + dcUniform3.getVariance());
    	System.out.println("\tUniform 3 Cvar:\t\t\t " + dcUniform3.getStdDeviation()/dcUniform3.getMean());
    	
    	//Exponential1 cannot be calculated because Cvar needs to be 1 for Exponential
    	System.out.println();
    	System.out.println("Exponential1 cannot be calculated because Cvar needs to be 1 for Exponential.");
    	
    	//Exponential2
    	Exponential randVarExp2 = new Exponential(rng, 1);
    	randVarExp2.setMeanAndCvar(1, 1);
    	System.out.println(randVarExp2.toString());
    	DiscreteCounter dcExp2 = new DiscreteCounter("Exponential 2");
    	for (int i=0;i<1000000;i++) {
    		double rv = randVarExp2.getRV();
    		dcExp2.count(rv);
    	}
    	System.out.println("\tExponential 2 Number of Samples:\t " + dcExp2.getNumSamples());
    	System.out.println("\tExponential 2 Mean:\t\t\t " + dcExp2.getMean());
    	System.out.println("\tExponential 2 Variance:\t\t\t " + dcExp2.getVariance());
    	System.out.println("\tExponential 2 Cvar:\t\t\t " + dcExp2.getStdDeviation()/dcExp2.getMean());
    	
    	//Exponential3 cannot be calculated because Cvar needs to be 1 for Exponential
    	System.out.println();
    	System.out.println("Exponential3 cannot be calculated because Cvar needs to be 1 for Exponential.");
    	
    	//ErlangK1
    	ErlangK randVarErlangK1 = new ErlangK(rng, 0, 1);
    	randVarErlangK1.setMeanAndCvar(1, 0.1);
    	System.out.println(randVarErlangK1.toString());
    	DiscreteCounter dcErlangK1 = new DiscreteCounter("ErlangK 1");
    	for (int i=0;i<1000000;i++) {
    		double rv = randVarErlangK1.getRV();
    		dcErlangK1.count(rv);
    	}
    	System.out.println("\tErlangK 1 Number of Samples:\t " + dcErlangK1.getNumSamples());
    	System.out.println("\tErlangK 1 Mean:\t\t\t " + dcErlangK1.getMean());
    	System.out.println("\tErlangK 1 Variance:\t\t " + dcErlangK1.getVariance());
    	System.out.println("\tErlangK 1 Cvar:\t\t\t " + dcErlangK1.getStdDeviation()/dcErlangK1.getMean());
    	
    	//ErlangK2
    	ErlangK randVarErlangK2 = new ErlangK(rng, 0, 1);
    	randVarErlangK2.setMeanAndCvar(1, 1);
    	System.out.println(randVarErlangK2.toString());
    	DiscreteCounter dcErlangK2 = new DiscreteCounter("ErlangK 2");
    	for (int i=0;i<1000000;i++) {
    		double rv = randVarErlangK2.getRV();
    		dcErlangK2.count(rv);
    	}
    	System.out.println("\tErlangK 2 Number of Samples:\t " + dcErlangK2.getNumSamples());
    	System.out.println("\tErlangK 2 Mean:\t\t\t " + dcErlangK2.getMean());
    	System.out.println("\tErlangK 2 Variance:\t\t " + dcErlangK2.getVariance());
    	System.out.println("\tErlangK 2 Cvar:\t\t\t " + dcErlangK2.getStdDeviation()/dcErlangK2.getMean());
    	
    	//ErlangK3 cannot be calculated because Cvar needs to be 1 or lower for ErlangK
    	System.out.println();
    	System.out.println("ErlangK3 cannot be calculated because Cvar needs to be 1 or lower for ErlangK.");
    	
    	//HyperExponential1 cannot be calculated because Cvar needs to be 1 or larger for HyperExponential
    	System.out.println();
    	System.out.println("HyperExponential1 cannot be calculated because Cvar needs to be 1 or larger for HyperExponential.");
    	
    	//HyperExponential2
    	HyperExponential randVarHyperExponential2 = new HyperExponential(rng, 1, 1, 1, 1);
    	randVarHyperExponential2.setMeanAndCvar(1, 1);
    	System.out.println(randVarHyperExponential2.toString());
    	DiscreteCounter dcHyperExponential2 = new DiscreteCounter("HyperExponential 2");
    	for (int i=0;i<1000000;i++) {
    		double rv = randVarHyperExponential2.getRV();
    		dcHyperExponential2.count(rv);
    	}
    	System.out.println("\tHyperExponential 2 Number of Samples:\t " + dcHyperExponential2.getNumSamples());
    	System.out.println("\tHyperExponential 2 Mean:\t\t\t " + dcHyperExponential2.getMean());
    	System.out.println("\tHyperExponential 2 Variance:\t\t " + dcHyperExponential2.getVariance());
    	System.out.println("\tHyperExponential 2 Cvar:\t\t\t " + dcHyperExponential2.getStdDeviation()/dcHyperExponential2.getMean());

    	//HyperExponential3
    	HyperExponential randVarHyperExponential3 = new HyperExponential(rng, 1, 1, 1, 1);
    	randVarHyperExponential3.setMeanAndCvar(1, 2);
    	System.out.println(randVarHyperExponential3.toString());
    	DiscreteCounter dcHyperExponential3 = new DiscreteCounter("HyperExponential 3");
    	for (int i=0;i<1000000;i++) {
    		double rv = randVarHyperExponential3.getRV();
    		dcHyperExponential3.count(rv);
    	}
    	System.out.println("\tHyperExponential 3 Number of Samples:\t " + dcHyperExponential3.getNumSamples());
    	System.out.println("\tHyperExponential 3 Mean:\t\t\t " + dcHyperExponential3.getMean());
    	System.out.println("\tHyperExponential 3 Variance:\t\t " + dcHyperExponential3.getVariance());
    	System.out.println("\tHyperExponential 3 Cvar:\t\t\t " + dcHyperExponential3.getStdDeviation()/dcHyperExponential3.getMean());
	
    	System.out.println();
    	System.exit(0);
    	//Mark out
    }
}
