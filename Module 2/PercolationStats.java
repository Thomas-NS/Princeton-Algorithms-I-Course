import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int trials;
    private Percolation grid;
    private double[] results;
    private double confidenceNo = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0) { 
            throw new IllegalArgumentException("n and t must be larger than 0");
        }
        trials = t;
        results = new double[trials];

        for (int i = 0; i < trials; i++) {
            grid = new Percolation(n);

            while (!grid.percolates()) {
                int randomRow = StdRandom.uniformInt(1, n + 1);
                int randomCol = StdRandom.uniformInt(1, n + 1);

                if (!grid.isOpen(randomRow, randomCol)) {
                    grid.open(randomRow, randomCol);
                }
            }
            results[i] = (double) grid.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {  
        return mean() - ((confidenceNo * stddev() / Math.sqrt(trials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((confidenceNo * stddev() / Math.sqrt(trials)));
    }

   // test client
   public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);

        System.out.println("mean                    = " + stats.mean()); 
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
   }

} 
