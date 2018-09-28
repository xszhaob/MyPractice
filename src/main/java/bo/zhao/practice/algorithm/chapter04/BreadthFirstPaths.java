package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;

/**
 * @author Bo.Zhao
 * @since 18/9/2
 */
public class BreadthFirstPaths extends Paths {
    private static final int INFINITY = Integer.MAX_VALUE;
    // 到达该顶点的已知路径上的最后一个顶点
    private int[] edgeTo;
    // 到达该顶点的最短路径长度
    private int[] distTo;
    // 到达该顶点的最短路径是否已知
    private boolean[] marked;
    private final int s;

    public BreadthFirstPaths(Graph graph, int s) {
        this.marked = new boolean[graph.vCount()];
        this.edgeTo = new int[graph.vCount()];
        this.s = s;
        bfs(graph, s);
    }

    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    private void bfs(Graph graph, int s) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        for (int i = 0; i < graph.vCount(); i++) {
            distTo[i] = INFINITY;
        }
        marked[s] = true;
        distTo[s] = 0;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int x : graph.adj(v)) {
                if (!marked[x]) {
                    edgeTo[x] = v;
                    // 上一步的路径+1
                    distTo[x] = distTo[v] + 1;
                    marked[x] = true;
                    queue.enqueue(x);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        return pathTo(s, edgeTo, v);
    }

    private void validateVertex(int v) {
        int vCount = marked.length;
        if (v < 0 || v >= vCount) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vCount - 1));
        }
    }
}
