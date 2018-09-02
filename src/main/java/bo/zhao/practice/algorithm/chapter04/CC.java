package bo.zhao.practice.algorithm.chapter04;

/**
 * @author Bo.Zhao
 * @since 18/9/2
 */
public class CC {

    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph graph) {
        this.marked = new boolean[graph.vCount()];
        this.id = new int[graph.vCount()];
        for (int s = 0; s < graph.vCount(); s++) {
            if (!marked[s]) {
                dfs(graph, s);
                count++;
            }
        }
    }

    private void dfs(Graph graph, int s) {
        marked[s] = true;
        // 该顶点所在的图属于第几个连通变量
        id[s] = count;
        for (Integer v : graph.adj(s)) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
    }

    /**
     * 判断w和v是否连通
     */
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * 连通分量数
     */
    public int count() {
        return count;
    }

    /**
     * v所在的连通分量的标识符(0 ~ count() -1)
     */
    public int id(int v) {
        return id[v];
    }
}
