package bo.zhao.practice.algorithm.chapter04;

/**
 * @author Bo.Zhao
 * @since 18/9/2
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTowColorable = true;

    public TwoColor(Graph graph) {
        this.marked = new boolean[graph.vCount()];
        this.color = new boolean[graph.vCount()];
        for (int s = 0; s < graph.vCount(); s++) {
            dfs(graph, s);
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
            } else if (color[w] == color[v]) {
                isTowColorable = false;
            }
        }
    }

    public boolean isTowColorable() {
        return isTowColorable;
    }
}
