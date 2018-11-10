package study;

import simulation.lib.counter.DiscreteAutocorrelationCounter;
import simulation.lib.rng.StdRNG;

public class AutocorrelationTest {

    public static void testAutocorrelation() {
    	
    	DiscreteAutocorrelationCounter DACTest;
    	double[] list;
    	
    	DACTest = new DiscreteAutocorrelationCounter("Skript example case one: {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}" ,5);
    	list = new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8,9};
    	for (int i=0; i < list.length; i++) {
    		DACTest.count(list[i]);
		}
    	System.out.println(DACTest.report());
    	DACTest.reset();
    	
    	DACTest = new DiscreteAutocorrelationCounter("Problem 4.1.2 case two: {2, 2, 2, 2, 2, 2, 2, 2, 2, ...}" ,5) ;
    	list = new double[2000];
    	for (int i = 0; i < 2000; i++) {
    		list[i] = 2;
		}
    	for (int i=0; i < list.length; i++) {
    		DACTest.count(list[i]);
		}
    	System.out.println(DACTest.report());
    	DACTest.reset();
    	
    	DACTest = new DiscreteAutocorrelationCounter("additional case three: {2, -2, 2, -2, 2, -2, 2, -2, 2, ...}" ,5) ;
    	list = new double[2000];
    	for (int i = 0; i < 2000; i++) {
    		if((i%2) == 1) {
    			list[i] = -2;
    		} else {
    			list[i] = 2;
    		}
		}
    	for (int i=0; i < list.length; i++) {
    		DACTest.count(list[i]);
		}
    	System.out.println(DACTest.report());
    	DACTest.reset();
    	
    	DACTest = new DiscreteAutocorrelationCounter("Problem 4.1.2 case four: {2, 2, -2, 2, 2, -2, 2, 2, -2, ...}" ,5) ;
    	list = new double[2000];
    	for (int i = 0; i < 2000; i++) {
    		if((i%3) == 2) {
    			list[i] = -2;
    		} else {
    			list[i] = 2;
    		}
		}
    	for (int i=0; i < list.length; i++) {
    		DACTest.count(list[i]);
		}
    	System.out.println(DACTest.report());
    	DACTest.reset();
    	
    	DACTest = new DiscreteAutocorrelationCounter("case four: {1999,1998,..., 0}" ,5) ;
    	list = new double[2000];
    	for (int i = 1999; i >= 0; i--) {
    		list[i] = i;
		}
    	for (int i=0; i < list.length; i++) {
    		DACTest.count(list[i]);
		}
    	System.out.println(DACTest.report());
    	DACTest.reset();

    	
    	DACTest = new DiscreteAutocorrelationCounter("case six: Random numbers between 0 and 10000" ,5) ;
    	list = new double[2000];
    	StdRNG rng = new StdRNG();
    	for (int i = 0; i < 2000; i++) {
    		list[i] = (int) (rng.rnd() * 10000);
		}
    	for (int i=0; i < list.length; i++) {
    		DACTest.count(list[i]);
		}
    	System.out.println(DACTest.report());
    	
    }
}
