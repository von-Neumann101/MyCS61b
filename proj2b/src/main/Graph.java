package main;

import java.util.*;

public class Graph {
    private final Map<Integer, Set<Integer>> graph; //值就是父节点，键就是其子节点的集合（下位词）

    Graph() {
        graph = new HashMap<>();
    }

    void addEdge(int parent, int child) {
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

    private void dfs(int label, Set<Integer> visited) {
        if (visited.contains(label)) return;
        visited.add(label);

        for (int i: graph.getOrDefault(label, Collections.emptySet())) {
            dfs(i, visited);
        }
    }

    /**
     * 用于在规定节点的dfs，并的返回其路径
     * @param start dfs开始的节点（有可能有多个）
     * @return 返回路径
     */
    public Set<Integer> getDfSPathFromStart(Set<Integer> start) {
        Set<Integer> visited = new HashSet<>();
        for (int i: start) {
            dfs(i, visited);
        }
        return visited;
    }
}
