package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;

/**
 * @author Bo.Zhao
 * @since 18/9/12
 */
public class BreadthFirstDirectedPaths extends Paths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstDirectedPaths(Digraph digraph, int s) {
        this.s = s;
        marked = new boolean[digraph.vCount()];
        edgeTo = new int[digraph.vCount()];

        bfs(digraph, s);
    }

    private void bfs(Digraph digraph, int v) {
        marked[v] = true;

        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(v);

        while (!queue.isEmpty()) {
            Integer w = queue.dequeue();
            for (Integer x : digraph.adj(w)) {
                if (!marked[x]) {
                    edgeTo[x] = w;
                    queue.enqueue(x);
                }
            }
        }
    }


    @Override
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        return pathTo(s, edgeTo, v);
    }


    private void validateVertex(int v) {
        int vCount = marked.length;
        if (v < 0 || v >= vCount) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vCount - 1));
        }
    }
}
