package bo.zhao.practice.algorithm.chapter04;

/**
 * @author Bo.Zhao
 * @since 18/9/2
 */
public abstract class Search {

    /**
     * 找到和起点s连通的所有顶点
     */
    public Search(Graph graph, int s) {

    }

    /**
     * v和s是否连通
     */
    public abstract boolean marked(int v);

    /**
     * 与s连通的顶点总数
     */
    public abstract int count();
}
