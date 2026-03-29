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
    int N, water, end;
    boolean[][] OPEN;

    public Percolation(int N) {
        if(N <= 0) throw new java.lang.IllegalArgumentException();
        this.N = N;
        OPEN = new boolean[N][N];
        openSites = 0;
        water = N * N;
        end = water + 1;
        grid = new WeightedQuickUnionUF(end);
    }

    public void open(int row, int col) {
        /*注意到，open本质上是要我们建立联通
        * 每次open我们就查找能否建立联通
        * 最终我们的判断就是底部的点是否和顶部的点联通*/
        if (isOpen(row, col)) return;
        OPEN[row][col] = true;
        openSites += 1;

        int i = index(row, col);

        if(row == 0)
            grid.union(i, water);

        if(row == N - 1)
            grid.union(i, end);

        if (row > 0 && isOpen(row - 1, col))
            grid.union(i, index(row - 1, col));
        if (row < N - 1 && isOpen(row + 1, col))
            grid.union(i, index(row + 1, col));
        if (col > 0 && isOpen(row,col - 1))
            grid.union(i, index(row,col - 1));
        if (col < N - 1 && isOpen(row,col + 1))
            grid.union(i, index(row, col + 1));
    }

    public boolean isOpen(int row, int col) {
        return grid.connected(index(row, col), god);
    }

    public boolean isFull(int row, int col) {
        if(!isOpen(row, col)){
            return false;
        }
        return grid.connected(index(row, col), water);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return depth == N - 1;
    }

    private int index(int row, int col) {
        if(row < 0 || row > N - 1 || col < 0 || col > N - 1){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return row * N + col;
    }
}
