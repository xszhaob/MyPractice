package bo.zhao.practice.algorithm.chapter04;

/**
 * @author Bo.Zhao
 * @since 18/9/2
 */
public class DepthFirstSearch extends Search {

    private boolean[] marked;

    private int count;

    public DepthFirstSearch(Graph graph, int s) {
        super(graph, s);
        marked = new boolean[graph.vCount()];
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        count++;
        Iterable<Integer> adj = graph.adj(v);
        for (Integer w : adj) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    @Override
    public int count() {
        return count;
    }
}
