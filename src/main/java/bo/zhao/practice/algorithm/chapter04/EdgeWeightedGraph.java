package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.Bag;
import bo.zhao.practice.algorithm.chapter01.LinkedBag;

import java.util.List;

/**
 * @author Bo.Zhao
 * @since 18/9/22
 */
public class EdgeWeightedGraph {
    private final int vCount;
    private int eCount;
    private Bag<Edge>[] adj;
    private double weight;

    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int vCount) {
        this.vCount = vCount;
        this.eCount = 0;
        this.adj = (Bag<Edge>[]) new LinkedBag[vCount];
        for (int i = 0; i < vCount; i++) {
            adj[i] = new LinkedBag<>();
        }
    }

    public EdgeWeightedGraph(List<Edge> egList, int vCount) {
        this(vCount);
        for (Edge edge : egList) {
            addEdge(edge);
        }
    }

    public int vCount() {
        return vCount;
    }

    public int eCount() {
        return eCount;
    }

    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        this.eCount++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new LinkedBag<>();
        for (int v = 0; v < eCount; v++) {
            for (Edge edge : adj[v]) {
                if (edge.other(v) > v) {
                    b.add(edge);
                }
            }
        }
        return b;
    }
}
