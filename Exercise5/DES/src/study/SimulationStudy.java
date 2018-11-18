package study;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import simulation.lib.Simulator;
import simulation.lib.counter.ContinuousCounter;
import simulation.lib.counter.Counter;
import simulation.lib.counter.DiscreteAutocorrelationCounter;
import simulation.lib.counter.DiscreteConfidenceCounterWithRelativeError;
import simulation.lib.counter.DiscreteCounter;
import simulation.lib.histogram.ContinuousHistogram;
import simulation.lib.histogram.DiscreteHistogram;
import simulation.lib.randVars.RandVar;
import simulation.lib.randVars.continous.ErlangK;
import simulation.lib.randVars.continous.Exponential;
import simulation.lib.randVars.continous.HyperExponential;
import simulation.lib.rng.StdRNG;
import simulation.lib.statistic.IStatisticObject;

/**
 * Represents a simulation study. Contains diverse counters for statistics and
 * program/simulator parameters. Starts the simulation.
 */
public class SimulationStudy {
	 /*
	 * Problem 5.1 - Configure program arguments here
	 * Problem 5.1.1 - nInit and lBatch
	 */
	long setNInit = 100;
	long setLBatch = 100;
	
	/*
	 * Problem 5.1.3 - Add attributes to configure your E[ST] and E[IAT] for the simulation
	 * Here you can set the different parameters for your simulation
	 * Note: Units are real time units (seconds).
	 * They get converted to simulation time units in setSimulationParameters.
	 */
	 // e.g. protected cNInit = ...
	 //protected cCvar = ... <- configuration Parameter for cVar[IAT]
	double setRho = 0.5;
	double setIATCvar = 2;
	
	/**
	 * Main method
	 * 
	 * @param args
	 *            - none
	 */
	public static void main(String[] args) {
		/*
		 * create simulation object
		 */
		Simulator sim = new Simulator();
		/*
		 * run simulation
		 */
		sim.start();
		/*
		 * print out report
		 */
		sim.report();
	}

	// PARAMETERS
	/**
	 * Turn on/off debug report in console.
	 */
	protected boolean isDebugReport = true;

	/**
	 * Turn on/off report in csv-files.
	 */
	protected boolean isCsvReport = true;

	/**
	 * inter arrival time of customers (in simulation time).
	 */
	public long interArrivalTime;

	/**
	 * service time of a customer (in simulation time).
	 */
	public long serviceTime;

	/**
	 * Number of customers for initialization.
	 */
	public long nInit;

	/**
	 * Length of batches.
	 */
	public long batchLength;

	/**
	 * Coefficient of variation.
	 */
	public double cVar;

	/**
	 * Random number generator for inter arrival times.
	 */
	public RandVar randVarInterArrivalTime;

	/**
	 * random number generator for service times
	 */
	public RandVar randVarServiceTime;

	// STATISTICS
	/**
	 * Map that contains all statistical relevant object such as counters and
	 * histograms.
	 */
	public HashMap<String, IStatisticObject> statisticObjects;

	/**
	 * Maximum queue size.
	 */
	public long maxQS;

	/**
	 * Minimum queue size.
	 */
	public long minQS;

	/**
	 * Number of batches in simulation.
	 */
	public long numBatches;

	/*
	 * Problem 5.1 - naming your statistic objects
	 * Here you have to set some names (as Sting objects) for all your statistic objects
	 * They are later used to retrieve them from the dictionary
	 */
	// Strings used for receiving statisticobjects later in the dictionary.
	public String dtcWaitingTime = "discreteTimeCounterWaitingTime";
	public String dthWaitingTime = "discreteTimeHistogramWaitingTime";
	public String dtcServiceTime = "discreteTimeCounterServiceTime";
	public String dthServiceTime = "discreteTimeHistogramServiceTime";
	public String ctcQueueOccupancy = "continuousTimeCounterQueueOccupancy";
	public String cthQueueOccupancy = "continuousTimeHistogramQueueOccupancy";
	public String ctcServerUtilization = "continuousTimeCounterServerUtilization";
	public String cthServerUtilization = "continuousTimeHistogramServerUtilization";
	
	public String tempdtcBatchWaitingTime = "temporaryDiscreteTimeCounterBatchWaitingTime";
	public String ccreBatchWaitingTime = "confidenceCounterWithRelativeErrorBatchWaitingTime";
	
	public String tempdtcBatchServiceTime = "temporaryDiscreteTimeCounterBatchServiceTime";	
	public String ccreBatchServiceTime = "confidenceCounterWithRelativeErrorBatchServiceTime";
	
	public String ccreWaitingTime = "confidenceCounterWithRelativeErrorWaitingTime";

	public long numWaitingTimeExceeds5TimesServiceTime;
	public long numBatchWaitingTimeExceeds5TimesBatchServiceTime;
	public long numWaitingTimeExceeds0;
	public String dtacBatchWaitingTime = "discreteTimeAutocorrelationCounterBatchWaitingTime";

	private Simulator simulator;

	/**
	 * Constructor
	 * @param sim Simulator instance.
	 */
	public SimulationStudy(Simulator sim) {
		simulator = sim;
		simulator.setSimTimeInRealTime(1000);
		setSimulationParameters();
		initStatistics();
	}

	/**
	 * Sets simulation parameters, converts real time to simulation time if
	 * needed.
	 */
	private void setSimulationParameters() {

		/*
		 * Problem 5.1.1 - Set simulation parameters
		 * Hint: Take a look at the attributes of this class which have no usages yet (This may be indicated by your IDE)
		 */
		// this.nInit = cNInit;
		// this.cVar = ...
		if (setNInit >= 0) {
			this.nInit = setNInit;
		} else {
			throw new IllegalArgumentException("Your parameter setNInit is small than 0.");
		}
		if (setLBatch >= 1) {
			this.batchLength = setLBatch;
		} else {
			throw new IllegalArgumentException("Your parameter setLBatch is small than 0.");
		}
		


		/*
		 * Problem 5.1.2 - Create random variables for IAT and ST
		 * You may use different random variables for this.randVarInterArrivalTime, since Cvar[IAT] = {0.5, 1, 2}
		 * You can use this.cVar as a configuration parameter for Cvar[IAT]
		 * !!! Make sure to use StdRNG objects with different seeds !!!
		 */
		StdRNG randST = new StdRNG();
		randST.setSeed(0);
		this.randVarServiceTime = new Exponential(randST, simulator.realTimeToSimTime(1));

		// Modulo for granularity 0.05
		double rho = setRho - (setRho % 0.05);
		if (rho > 0.95 || rho < 0.05) {
			throw new IllegalArgumentException("Your parameter setRho is smaller than 0.05 or larger than 0.95.");
		}
		double iat = 1 / rho;
		
		StdRNG randIAT = new StdRNG();
		randIAT.setSeed(1);
		if (setIATCvar < 1) {
			this.randVarInterArrivalTime = new ErlangK(randIAT, simulator.realTimeToSimTime(iat), setIATCvar*simulator.realTimeToSimTime(iat));			
		} else if (setIATCvar == 1) {
			this.randVarInterArrivalTime = new Exponential(randIAT, simulator.realTimeToSimTime(iat));
		} else {
			this.randVarInterArrivalTime = new HyperExponential(randIAT, simulator.realTimeToSimTime(iat), setIATCvar);
		}
		
		
	}

	/**
	 * Initializes statistic objects. Adds them into Hashmap.
	 */
	private void initStatistics() {
		maxQS = Long.MIN_VALUE;
		minQS = Long.MAX_VALUE;

		// Init numBatches
		numBatches = 0;

		statisticObjects = new HashMap<>();
		statisticObjects.put(dtcWaitingTime, new DiscreteCounter("waiting time/customer"));
		statisticObjects.put(dthWaitingTime, new DiscreteHistogram("waiting_time_per_customer", 80, 0, 80));

		statisticObjects.put(dtcServiceTime, new DiscreteCounter("service time/customer"));
		statisticObjects.put(dthServiceTime, new DiscreteHistogram("service_time_per_customer", 80, 0, 80));

		statisticObjects.put(ctcQueueOccupancy, new ContinuousCounter("queue occupancy/time", simulator));
		statisticObjects.put(cthQueueOccupancy,
				new ContinuousHistogram("queue_occupancy_over_time", 80, 0, 80, simulator));

		statisticObjects.put(ctcServerUtilization, new ContinuousCounter("server utilization/time", simulator));
		statisticObjects.put(cthServerUtilization,
				new ContinuousHistogram("server_utilization_over_time", 80, 0, 80, simulator));

		/*
		 * Problem 5.1.1 - Create a DiscreteConfidenceCounterWithRelativeError
		 * In order to check later if the simulation can be terminated according to the condition
		 */
		statisticObjects.put(ccreBatchWaitingTime, new DiscreteConfidenceCounterWithRelativeError("waiting time/customer with batch means", 0.1));
		statisticObjects.put(ccreBatchServiceTime, new DiscreteConfidenceCounterWithRelativeError("service time/customer with batch means", 0.1));
		
		/*
		 * Problem 5.1.4 - Create counter to calculate the mean waiting time with batch means method
		 */
		// Why? Isnt this the ccreBatchWaitingTime?
		
		/*
		 * Problem 5.1.4 - Provide means to keep track of E[WT] > 5 * E[ST]
		 * !!! This is also called "waiting probability" in the sheet !!!
		 */
		statisticObjects.put(tempdtcBatchWaitingTime, new DiscreteCounter("waiting time of last batch"));
		statisticObjects.put(tempdtcBatchServiceTime, new DiscreteCounter("service time of last batch"));
		
		/*
		 * Problem 5.1.4 - Create confidence counter for individual waiting time samples
		 */
		statisticObjects.put(ccreWaitingTime, new DiscreteConfidenceCounterWithRelativeError("waiting time/customer", 0.1));
				
		/*
		 * Problem 5.1.4 - Create confidence counter for to count waiting times with batch means method
		 */
		// Why? Isnt this the ccreBatchWaitingTime?
		
		/*
		 * Problem 5.1.5 - Create a DiscreteAutocorrelationCounter for batch means
		 */
		statisticObjects.put(dtacBatchWaitingTime, new DiscreteAutocorrelationCounter("mean_batch_waiting_time", 10));
	}


	/**
	 * Report results. Print to console if isDebugReport = true. Print to csv
	 * files if isCsvReport = true. Note: Histogramms are only printed to csv
	 * files.
	 */
	public void report() {
		String sd = new SimpleDateFormat("yyyyMMdd_HHmmss_").format(new Date(System.currentTimeMillis()));
		String destination = sd + this.getClass().getSimpleName();

		if (isCsvReport) {
			File file = new File(destination);
			file.mkdir();
			for (IStatisticObject so : statisticObjects.values()) {
				so.csvReport(destination);
			}
		}
		if (isDebugReport) {
			/*
			 * Problem 5.1 - Output reporting information!
			 * Print your statistic objects which are needed to answer the questions in the exercise sheet
			 */
			System.out.println(this.statisticObjects.get(this.ccreBatchWaitingTime).report());
			System.out.println(this.statisticObjects.get(this.ccreBatchServiceTime).report());
			System.out.println(this.statisticObjects.get(this.ccreWaitingTime).report());
			System.out.println(this.statisticObjects.get(this.dtacBatchWaitingTime).report());
			System.out.println(this.statisticObjects.get(this.ctcServerUtilization).report());
			System.out.println(this.statisticObjects.get(this.ctcQueueOccupancy).report());

			System.out.println("Waiting time > 5 times service time: " + numWaitingTimeExceeds5TimesServiceTime);
			System.out.println("Waiting time > 5 times service time (batch): " + numBatchWaitingTimeExceeds5TimesBatchServiceTime);


		}

	}
}
