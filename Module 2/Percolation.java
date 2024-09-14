import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean[][] matrix;
    private int size;
    private int top = 0;
    private int bottom;
    private int openSiteCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) { 
            throw new IllegalArgumentException("n must be larger than 0");
        }
        size = n;
        uf = new WeightedQuickUnionUF(size * size + 2);
        matrix = new boolean[size][size];
        bottom = size * size + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateValues(row, col);
        if (!isOpen(row, col)) { 
            matrix[row - 1][col - 1] = true;
            openSiteCount++;
            int newOpenIndex = getIndex(row, col);

            if (row == 1) {
                uf.union(newOpenIndex, top);
            }
            if (row == size) {
                uf.union(newOpenIndex, bottom);
            }

            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(newOpenIndex, getIndex(row - 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(newOpenIndex, getIndex(row, col - 1));
            }
            if (row < size && isOpen(row + 1, col)) {
                uf.union(newOpenIndex, getIndex(row + 1, col));
            }
            if (col < size && isOpen(row, col + 1)) {
                uf.union(newOpenIndex, getIndex(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateValues(row, col);
        return matrix[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateValues(row, col);
        return uf.find(top) == uf.find(getIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(top) == uf.find(bottom);
    }

    private int getIndex(int row, int col) {
        return size * (row - 1) + col;
    }

    private void validateValues(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException("row or col out of bounds");
        }
    }

}


