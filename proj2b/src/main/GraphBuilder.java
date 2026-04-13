package main;

import edu.princeton.cs.algs4.In;

public class GraphBuilder {
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
}
