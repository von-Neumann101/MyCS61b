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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge e = (Edge) o;

        return (a == e.a && b == e.b) ||
                (a == e.b && b == e.a);
    }

    @Override
    public int hashCode() {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        return 31 * min + max;
    }
}
