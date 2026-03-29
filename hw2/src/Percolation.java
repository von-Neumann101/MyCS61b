import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    /*
    * 我们如是考虑，一开始我是打算使用二维int数组来表示状态
    * 但是这存在一个很大的问题——位置信息必须来源于row和col
    * 从题目角度说，没法使用并查集；从性能角度说，慢
    * 所以我们必须建立一个映射index(row, col)
    * */
    int openSites;
    WeightedQuickUnionUF world, water_world;
    int N, water, end;
    boolean[][] OPEN;

    public Percolation(int N) {
        if(N <= 0) throw new java.lang.IllegalArgumentException();
        this.N = N;
        OPEN = new boolean[N][N];
        openSites = 0;
        water = N * N;
        end = water + 1;
        world = new WeightedQuickUnionUF(end);
        /*原来Open的实现是把所有的底部开元素都加入end里面
        * 这就导致如果联通，本来被堵死的地方都会连接到water
        * 导致对底部的isFull判断错误*/
        water_world = new WeightedQuickUnionUF(end);
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
            world.union(i, water);

        if(row == N - 1)
            world.union(i, end);

        if (row > 0 && isOpen(row - 1, col))
            world.union(i, index(row - 1, col));
        if (row < N - 1 && isOpen(row + 1, col))
            world.union(i, index(row + 1, col));
        if (col > 0 && isOpen(row,col - 1))
            world.union(i, index(row,col - 1));
        if (col < N - 1 && isOpen(row,col + 1))
            world.union(i, index(row, col + 1));
    }

    public boolean isOpen(int row, int col) {
        index(row, col);
        return OPEN[row][col];
    }

    public boolean isFull(int row, int col) {
        if(!isOpen(row, col)){
            return false;
        }
        return world.connected(index(row, col), water);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        /*底部有没有连到水*/
        return world.connected(water, end);
    }

    private int index(int row, int col) {
        if(row < 0 || row > N - 1 || col < 0 || col > N - 1){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return row * N + col;
    }
}
