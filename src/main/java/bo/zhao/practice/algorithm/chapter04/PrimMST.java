package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter02.priorityqueue.IndexMinPQ;

/**
 * @author Bo.Zhao
 * @since 18/9/24
 */
public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph graph) {
        this.edgeTo = new Edge[graph.vCount()];
        this.distTo = new double[graph.vCount()];
        this.marked = new boolean[graph.vCount()];
        for (int v = 0; v < graph.vCount(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(graph.vCount());
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            visit(graph, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge edge : graph.adj(v)) {
            int w = edge.other(v);
            if (marked[w]) {
                continue;
            }
            // w不在树中，但至少有一个边和树相连
            if (edge.weight() < distTo[w]) {
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }
}
