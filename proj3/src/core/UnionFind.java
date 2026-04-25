package core;

public class UnionFind {
    private final int[] parent;
    private final int[] size;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * 寻找x的根节点
     * @param x 一个节点
     * @return x的根节点
     */
    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]); // 顺手处理
        }
        return parent[x];
    }

    public boolean isConnected(int a, int b) { //??????
        return find(a) == find(b);
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootB == rootA) return;

        if (size[rootA] < size[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }

}
