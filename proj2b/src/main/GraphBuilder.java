package main;

import edu.princeton.cs.algs4.In;

public class GraphBuilder {
    private final String file;
    public GraphBuilder(String file) {
        this.file = file;
    }
    /**
     * 用于从hyponyms.txt中构建图
     */
    public Graph buildGraph() {
        In in = new In(file);
        Graph g = new Graph();

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] p = line.split(",");

            int parent = Integer.parseInt(p[0]);

            for (int i = 1; i < p.length; i++) {
                int child = Integer.parseInt(p[i].trim());
                g.addEdge(parent, child);
            }
        }
        return g;
    }
}
