import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    /*
    * 我们如是考虑，一开始我是打算使用二维int数组来表示状态
    * 但是这存在一个很大的问题——位置信息必须来源于row和col
    * 从题目角度说，没法使用并查集；从性能角度说，慢
    * 所以我们必须建立一个映射index(row, col)
    * */
    int openSites;
    WeightedQuickUnionUF grid;
    int N, god, water;
    public Percolation(int N) {
        if(N <= 0){
            throw new java.lang.IllegalArgumentException();
        }
        this.N = N;
        openSites = 0;
        god = N * N;
        water = god + 1;
        grid = new WeightedQuickUnionUF(N * N + 1);
    }

    public void open(int row, int col) {
        grid.union(index(row, col), god);
        waterDown(row, col);
        openSites += 1;
    }

    public boolean isOpen(int row, int col) {
        return grid.connected(index(row, col), god);
    }

    public boolean isFull(int row, int col) {
        return grid.connected(index(row, col), water);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return false;
    }

    private int index(int row, int col) {
        if(row < 0 || row > N - 1 || col < 0 || col > N - 1){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return row * N + col;
    }

    private void connectWater(int i) {
        grid.union(i, water);
    }

    private void waterDown(int row, int col) {
        if (!isOpen(row, col)) {
            return;
        }

        int i = index(row, col);
        boolean shouldFill = row == 0
                || (row > 0 && isFull(row - 1, col))
                || (row < N - 1 && isFull(row + 1, col))
                || (col > 0 && isFull(row, col - 1))
                || (col < N - 1 && isFull(row, col + 1));

        if (shouldFill) {
            connectWater(i);
        }
    }
}
