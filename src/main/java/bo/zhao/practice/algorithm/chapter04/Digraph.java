package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.LinkedBag;
import bo.zhao.practice.algorithm.chapter01.LinkedStack;
import bo.zhao.practice.algorithm.chapter01.Stack;

import java.util.List;

/**
 * @author Bo.Zhao
 * @since 18/9/10
 */
public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * 顶点数
     */
    private int vCount;
    /**
     * 边的数量
     */
    private int eCount;

    /**
     * 边的情况
     */
    private LinkedBag<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Digraph(int vCount) {
        this.vCount = vCount;
        this.eCount = 0;
        this.adj = (LinkedBag<Integer>[]) new LinkedBag[vCount];
        for (int i = 0; i < vCount; i++) {
            adj[i] = new LinkedBag<>();
        }
    }

    /**
     * 从给定的输入构造一个有向图
     */
    public Digraph(List<int[]> in, int vCount) {
        this(vCount);
        for (int[] e : in) {
            addEdge(e[0], e[1]);
        }
    }


    public Digraph(Digraph digraph) {
        this(digraph.vCount);
        this.eCount = digraph.eCount();
        for (int v = 0; v < digraph.vCount; v++) {
            Stack<Integer> reverse = new LinkedStack<>();
            for (Integer w : digraph.adj(v)) {
                reverse.push(w);
            }
            for (Integer x : reverse) {
                adj[v].add(x);
            }
        }
    }

    /**
     * 如果图含有边v-w，方法返回true，否则返回false
     */
    public boolean hasEdge(int v, int w) {
        for (Integer x : adj[v]) {
            if (w == x) {
                return true;
            }
        }
        return false;
    }

    /**
     * 顶点总数
     */
    public int vCount() {
        return vCount;
    }

    /**
     * 边总数
     */
    public int eCount() {
        return eCount;
    }

    /**
     * 向有向图中添加一条边v-w
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        adj[v].add(w);
        eCount++;
    }

    /**
     * 由v指出的边所连接的所有顶点
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * 该图的反向图
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(vCount);
        for (int v = 0; v < vCount; v++) {
            for (Integer w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * 对象的字符串表示
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vCount)
                .append(" vertices, ")
                .append(eCount)
                .append(" edges ")
                .append(NEWLINE);
        for (int v = 0; v < vCount; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vCount) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vCount - 1));
        }
    }
}
