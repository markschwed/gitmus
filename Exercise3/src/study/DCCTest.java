package study;

import simulation.lib.counter.DiscreteConfidenceCounter;

/*
 * TODO Problem 3.1.3 and 3.1.4 - implement this class
 */
public class DCCTest {

    public static void main(String[] args) {
    	//Mark
    	DiscreteConfidenceCounter test = new DiscreteConfidenceCounter("Marks Test",0.06);
    	test.getT(3);
    	test.report();
    	System.exit(0);
    	//Mark out
        testDCC();
    }

    public static void testDCC() {
        // TODO Auto-generated method stub
    }
}
