package core;

public class Edge implements Comparable<Edge> {
    int a, b, weight;
    public Edge(int a, int b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}
