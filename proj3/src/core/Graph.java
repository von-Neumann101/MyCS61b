package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    public static List<Edge> buildGraph(List<Room> rooms) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                Room r1 = rooms.get(i);
                Room r2 = rooms.get(j);
                // 我们先实现中心到中心
                int dx = r1.centerX() - r2.centerX();
                int dy = r1.centerY() - r2.centerY();

                int weight = Math.abs(dx) + Math.abs(dy); // L1-norm

                edges.add(new Edge(i, j, weight));
            }
        }
        return edges;
    }

    /**
     * 根据Room生成对应MST
     * @param rooms 所有房间的列表
     * @return 返回一组边，包含一个mst的全部信息
     */
    public static List<Edge> MST(List<Room> rooms) {
        List<Edge> mst = new ArrayList<>();
        UnionFind uf = new UnionFind(rooms.size());
        List<Edge> edges = buildGraph(rooms);
        Collections.sort(edges);
        
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
