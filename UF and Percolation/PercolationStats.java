/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private int numTrials;
    private double[] results;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n and trials must be a positive integer");

        numTrials = trials;
        results = new double[numTrials];

        for (int i = 0; i < numTrials; i++) {
            Percolation system = new Percolation(n);
            while (!system.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                system.open(row, col);
            }
            double result = (double) system.numberOfOpenSites() / (n * n);
            results[i] = result;
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(numTrials));
    }

    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(numTrials));
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("You must pass two arguments, size and trials.");
            return;
        }
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats experiments = new PercolationStats(size, trials);
        System.out.println("Mean = " + experiments.mean());
        System.out.println("stddev = " + experiments.stddev());
        System.out.println("95% confidence interval = [" + experiments.confidenceLo() + ", "
                                   + experiments.confidenceHi() + "]");
    }
}
