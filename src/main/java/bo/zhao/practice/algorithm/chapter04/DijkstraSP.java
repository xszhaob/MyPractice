package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.LinkedStack;
import bo.zhao.practice.algorithm.chapter01.Stack;
import bo.zhao.practice.algorithm.chapter02.priorityqueue.IndexMinPQ;

/**
 * @author Bo.Zhao
 * @since 18/9/25
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph digraph, int s) {
        edgeTo = new DirectedEdge[digraph.vCount()];
        distTo = new double[digraph.vCount()];
        pq = new IndexMinPQ<>(digraph.vCount());

        for (int v = 0; v < digraph.vCount(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0D;
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(digraph, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph digraph, int v) {
        for (DirectedEdge edge : digraph.adj(v)) {
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.weight()) {
                distTo[w] = distTo[v] + edge.weight();
                edgeTo[w] = edge;
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new LinkedStack<>();
        for (DirectedEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.to()]) {
            path.push(edge);
        }
        return path;
    }


}
