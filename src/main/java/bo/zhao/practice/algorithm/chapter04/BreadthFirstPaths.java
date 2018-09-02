package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;

/**
 * @author Bo.Zhao
 * @since 18/9/2
 */
public class BreadthFirstPaths extends Paths {

    private int[] edgeTo;
    private boolean[] marked;
    private final int s;

    public BreadthFirstPaths(Graph graph, int s) {
        super(graph, s);
        this.marked = new boolean[graph.vCount()];
        this.edgeTo = new int[graph.vCount()];
        this.s = s;
        bfs(graph, s);
    }


    private void bfs(Graph graph, int s) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int x : graph.adj(v)) {
                if (!marked[x]) {
                    edgeTo[x] = v;
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
}
