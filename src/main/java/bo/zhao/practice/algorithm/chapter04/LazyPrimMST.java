package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;
import bo.zhao.practice.algorithm.chapter01.Queue;
import bo.zhao.practice.algorithm.chapter02.priorityqueue.MinPQ;

/**
 * @author Bo.Zhao
 * @since 18/9/22
 */
public class LazyPrimMST {
    private boolean[] marked;

    private Queue<Edge> mst;

    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        pq = new MinPQ<>();
        marked = new boolean[graph.vCount()];
        mst = new LinkedQueue<>();
        visit(graph, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();

            int v = e.either();
            int w = e.other(v);
            // 跳过失效的点
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.enqueue(e);
            if (!marked[v]) {
                visit(graph, v);
            }
            if (!marked[w]) {
                visit(graph, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge edge : g.adj(v)) {
            if (!marked[edge.other(v)]) {
                pq.insert(edge);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return 0;
    }
}
