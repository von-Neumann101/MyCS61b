import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    boolean[][] grid;
    int openSites;

    public Percolation(int N) {
        if(N <= 0){
            throw new java.lang.IllegalArgumentException();
        }
        grid = new boolean[N][N];
        openSites = 0;
    }

    public void open(int row, int col) {
        grid[row][col] = true;
    }

    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return 0;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
