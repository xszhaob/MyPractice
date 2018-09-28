package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.Bag;
import bo.zhao.practice.algorithm.chapter01.LinkedBag;

import java.util.List;

/**
 * @author Bo.Zhao
 * @since 18/9/25
 */
public class EdgeWeightedDigraph {
    private final int vCount;
    private int eCount;
    private Bag<DirectedEdge>[] adj;

    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(int vCount) {
        this.vCount = vCount;
        this.eCount = 0;
        this.adj = (LinkedBag[]) new LinkedBag[vCount];
        for (int v = 0; v < vCount; v++) {
            this.adj[v] = new LinkedBag<>();
        }
    }

    public EdgeWeightedDigraph(List<DirectedEdge> in, int vCount) {
        this(vCount);
        for (DirectedEdge edge : in) {
            addEdge(edge);
        }
    }

    public int vCount() {
        return vCount;
    }

    public int eCount() {
        return eCount;
    }

    public void addEdge(DirectedEdge edge) {
        this.adj[edge.from()].add(edge);
        this.eCount++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new LinkedBag<>();
        for (int v = 0; v < vCount; v++) {
            for (DirectedEdge edge : adj[v]) {
                bag.add(edge);
            }
        }
        return bag;
    }
}
