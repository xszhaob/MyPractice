package bo.zhao.practice.algorithm.chapter04;

/**
 * @author Bo.Zhao
 * @since 18/9/17
 */
public class kosarajuSCC {
    /**
     * 已经访问的顶点
     */
    private boolean[] marked;

    /**
     * 强连通分量的标识符
     */
    private int[] id;

    /**
     * 强连通分量的数量
     */
    private int count;

    public kosarajuSCC(Digraph digraph) {
        this.marked = new boolean[digraph.vCount()];
        this.id = new int[digraph.vCount()];
        DepthFirstOrder order = new DepthFirstOrder(digraph);
        for (Integer s : order.reversePost()) {
            if (!marked[s]) {
                count++;
                dfs(digraph, s);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }
}
