package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int numberOfSides;
    private int numberOfTrials;
    private PercolationFactory percolationFactory;
    private double[] results;


    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.numberOfSides = N;
        this.numberOfTrials=T;
        this.percolationFactory=pf;
        this.results = new double[T];
        runTrials();
    }   // perform T independent experiments on an N-by-N grid

    private void runTrials(){
        for(int i = 0;i<numberOfTrials;i++){
            Percolation p = percolationFactory.make(numberOfSides);
            double openSites = 0;
            while(!p.percolates()){
                int x = StdRandom.uniform(1, numberOfSides + 1);
                int y = StdRandom.uniform(1, numberOfSides + 1);
                if(!p.isOpen(x,y)){
                    p.open(x,y);
                    openSites++;
                }
            }
            results[i] = openSites/(numberOfSides*numberOfSides);
        }

    }
    public double mean(){
        return StdStats.mean(results);
    }                                           // sample mean of percolation threshold
    public double stddev(){
        return StdStats.stddev(results);
    }
                                            // sample standard deviation of percolation threshold
    public double confidenceLow(){
        return mean()-1.96*stddev()/Math.sqrt(numberOfTrials);
    }                              // low endpoint of 95% confidence interval
    public double confidenceHigh(){
        return mean()+1.96*stddev()/Math.sqrt(numberOfTrials);
    }                                 // high endpoint of 95% confidence interval

}
