package bo.zhao.practice.algorithm.chapter04;

/**
 * @author Bo.Zhao
 * @since 18/9/2
 */
public class DepthFirstPaths extends Paths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;


    public DepthFirstPaths(Graph graph, int s) {
        edgeTo = new int[graph.vCount()];
        marked = new boolean[graph.vCount()];
        this.s = s;
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
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
