package core;

public class Graph {
    List<Edge> edges;

    public Graph() {
        edges = new ArrayList<>();
    }

    public void buildGraph(List<Room> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = i + 1; j < rooms.size(); i++) {
                Room r1 = rooms.get(i);
                Room r2 = rooms.get(j);
                // 我们先实现中心到中心
                int dx = r1.centerX() - r2.centerX();
                int dy = r1.centerY() - r2.centerY();

                int weight = Math.abs(dx) + Math.abs(dy); // L1-norm

                edges.add(new Edge(i, j, weight));
            }
        }
    }

    public static List<Edge> MST(List<Room> rooms) {
        List<Edge> mst = new ArrayList<>();
        Collections.sort(mst);
        UnionFind uf = new UnionFind(rooms.size());

        List<Edge> edges = buildGraph(rooms);

        for (Edge e : edges) {
            if (!uf.isConnected(e.a, e.b)) {
                uf.union(e.a, e.b);
                mst.add(e);
            }

            if (mst.size() == rooms.size() - 1) break;
        }
        return mst;
    }
}
