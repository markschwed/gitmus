package study;

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
    	Uniform randVarUniform = new Uniform(rng, 0, 1);
    	randVarUniform.setMeanAndCvar(1, 0.1);
    	System.out.println(randVarUniform.toString());
    	
    	randVarUniform.setMeanAndCvar(1, 1);
    	System.out.println(randVarUniform.toString());
    	
    	randVarUniform.setMeanAndCvar(1, 2);
    	System.out.println(randVarUniform.toString());
    	
    	
    	System.exit(0);
    	//Mark out
    }
}
