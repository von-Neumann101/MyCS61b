package main;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    private final Map<Integer, Set<Integer>> graph; //值就是父节点，键就是其子节点的集合（下位词）

    Graph() {
        graph = new HashMap<>();
    }
    /**
     * 用于从hyponyms.txt中构建图
     *
     * @param file 文件地址
     */
    private void buildGraph(String file, Graph g) {
        In in = new In(file);
        
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] p = line.split(",");
            
            int parent = Integer.parseInt(p[0]);

            for (String s : p) {
                int child = Integer.parseInt(s);
                g.addEdge(parent, child);
            }
        }
    }

    private void addEdge(int parent, int child) {
        // 如果没有parent，加入到graph中
        if (!graph.containsKey(parent)) {
            graph.put(parent, new HashSet<>());
        }
        //维护child的出（使得child仍然是一个良定义的节点）
        if (!graph.containsKey(child)) {
            graph.put(child, new HashSet<>());
        }
        graph.get(parent).add(child);
    }

    void dfs(int label, Set<Integer> visited) {
        if (visited.contains(label)) return;
        visited.add(label);

        for (int i: graph.getOrDefault(label, Collections.emptySet())) {
            dfs(i, visited);
        }
    }
}
