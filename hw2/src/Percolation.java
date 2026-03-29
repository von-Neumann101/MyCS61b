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
    int N, god;
    public Percolation(int N) {
        if(N <= 0){
            throw new java.lang.IllegalArgumentException();
        }
        this.N = N;
        openSites = 0;
        god = N * N;
        grid = new WeightedQuickUnionUF(N * N + 1);
    }

    public void open(int row, int col) {
        grid.connected(index(row, col), god);
        openSites += 1;
    }

    public boolean isOpen(int row, int col) {
        return
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return openSites - 1;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return false;
    }

    private int index(int row, int col){
        return row * N + col;
    }

}
