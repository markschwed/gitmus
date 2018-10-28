package study;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import simulation.lib.Simulator;
import simulation.lib.counter.ContinuousCounter;
import simulation.lib.counter.DiscreteCounter;
import simulation.lib.histogram.ContinuousHistogram;
import simulation.lib.histogram.DiscreteHistogram;
import simulation.lib.statistic.IStatisticObject;

/**
 * Represents a simulation study. Contains diverse counters for statistics and
 * program/simulator parameters. Starts the simulation.
 */
public class SimulationStudy {
    /*
	 * Problem 2.2.4 - configure program arguments here
	 * Here you can set the different parameters for your simulation
	 * Note: Units are real time units (seconds).
	 * They get converted to simulation time units in setSimulationParameters.
	 */
	protected long cInterArrivalTime = 10;
	protected long cServiceTime = 11;
	protected long cSimulationTime = 100000;

	/**
	 * Main method
	 */
	public static void main(String[] args) {
		/*
		 * create simulation object
		 */
		Simulator sim = new Simulator();
		
		//Mark
		RandVarTest.testRandVars();
		//Mark out
		
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
	 * Simulation time.
	 */
	public long simulationTime;


	/**
	 * inter arrival time of customers (in simulation time).
	 */
	public long interArrivalTime;

	/**
	 * service time of a customer (in simulation time).
	 */
	public long serviceTime;

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
	
	/*
	 * Problem 2.2 - naming your statistic objects
	 * Here you have to set some names (as Sting objects) for all your statistic objects
	 * They are later used to retrieve them from the dictionary
	 */
	// Example for discrete counter which measures customer waiting time:
	// public String dcWaitingTime = "discreteCounterWaitingTime";
	
	//Mark
	public String dcWaitingTime = "discreteCounterWaitingTime";
	public String dhWaitingTime = "discreteHistogramWaitingTime";
	public String dcServiceTime = "discreteCounterServiceTime";
	public String dhServiceTime = "discreteHistogramServiceTime";
	
	public String ccQueueOccupancy = "continuousCounterQueueOccupancy";
	public String chQueueOccupancy = "continuousHistogramQueueOccupancy";
	public String ccServerUtilization = "continuousCounterServerUtilization";
	public String chServerUtilization = "continuousHistogramServerUtilization";
	//Mark out
	

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
		interArrivalTime = simulator.realTimeToSimTime(cInterArrivalTime);
		serviceTime = simulator.realTimeToSimTime(cServiceTime);
		simulationTime = simulator.realTimeToSimTime(cSimulationTime);
	}

	/**
	 * Initializes statistic objects. Adds them into Hashmap.
	 */
	private void initStatistics() {
		maxQS = Long.MIN_VALUE;
		minQS = Long.MAX_VALUE;

		statisticObjects = new HashMap<>();

        /*
          Problem 2.2 - add statistic objects (counters) to the HashMap
          Here you have to create your counters and add them to the statisticObjects HashMap
          Use the name which you specified above as the key
         */
        // Example: statisticObjects.put(dcWaitingTime, new DiscreteCounter("waiting time/customer"));
		
		//Mark
		statisticObjects.put(dcWaitingTime, new DiscreteCounter("waiting time/customer"));
		statisticObjects.put(dhWaitingTime, new DiscreteHistogram("waitingTimePerCustomer", 10, 0, 10000));
		statisticObjects.put(dcServiceTime, new DiscreteCounter("service time/customer"));
		statisticObjects.put(dhServiceTime, new DiscreteHistogram("serviceTimePerCustomer", 10, 0, 50));
		statisticObjects.put(ccQueueOccupancy, new ContinuousCounter("queue occupancy", simulator));
		statisticObjects.put(chQueueOccupancy, new ContinuousHistogram("queueOccupancy", 10, 0, 50, simulator));
		statisticObjects.put(ccServerUtilization, new ContinuousCounter("server utilization", simulator));
		statisticObjects.put(chServerUtilization, new ContinuousHistogram("serverUtilization", 10, 0, 50, simulator));		
		//Mark out
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
            System.out.println("E[interArrivalTime] = " + simulator.simTimeToRealTime(interArrivalTime) + "\n"
                    + "E[serviceTime] = " + simulator.simTimeToRealTime(serviceTime) + "\n"
                    + "server utilization: "+ ((double) serviceTime / (double) interArrivalTime) + "\n");

            for (IStatisticObject so : statisticObjects.values()) {
                System.out.println(so.report());
            }

            System.out.println("minimum queue size: " + minQS + "\n" + "maximum queue size: " + maxQS);
        }
    }
}
