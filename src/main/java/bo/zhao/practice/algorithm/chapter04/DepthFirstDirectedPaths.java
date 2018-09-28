package bo.zhao.practice.algorithm.chapter04;

/**
 * @author Bo.Zhao
 * @since 18/9/12
 */
public class DepthFirstDirectedPaths extends Paths {
    private int[] edgeTo;
    private boolean[] marked;
    private final int s;

    public DepthFirstDirectedPaths(Digraph digraph, int s) {
        this.s = s;
        edgeTo = new int[digraph.vCount()];
        marked = new boolean[digraph.vCount()];

        dfs(digraph, s);
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        for (Integer w : digraph.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
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
