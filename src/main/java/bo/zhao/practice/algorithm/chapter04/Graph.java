package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.LinkedBag;
import bo.zhao.practice.algorithm.chapter01.LinkedStack;

import java.util.List;

/**
 * @author Bo.Zhao
 * @since 18/9/2
 */
public class Graph {
    /**
     * 顶点数
     */
    private final int vCount;
    /**
     * 边数
     */
    private int eCount;
    /**
     * 邻接表
     */
    private LinkedBag<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int vCount) {
        this.vCount = vCount;
        this.eCount = 0;
        adj = (LinkedBag<Integer>[]) new LinkedBag[vCount];
        for (int i = 0; i < vCount; i++) {
            adj[i] = new LinkedBag<>();
        }
    }

    public Graph(int vCount, List<int[]> in) {
        this(vCount);
        for (int[] ints : in) {
            addEdge(ints[0], ints[1]);
        }
    }

    public Graph(Graph graph) {
        this(graph.vCount);
        this.eCount = graph.eCount;
        for (int v = 0; v < graph.vCount; v++) {
            LinkedStack<Integer> reverse = new LinkedStack<>();
            // 以栈的形式先拿出来，再写入当前的Graph
            for (Integer w : graph.adj(v)) {
                reverse.push(w);
            }
            for (Integer w : reverse) {
                this.adj(v).add(w);
            }
        }
    }

    /**
     * 顶点的数量
     */
    public int vCount() {
        return vCount;
    }

    /**
     * 边数
     */
    public int eCount() {
        return eCount;
    }

    /**
     * 向图中添加一条边v-w
     */
    private void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        eCount++;
    }

    /**
     * 和v相邻的所有顶点
     */
    public LinkedBag<Integer> adj(int v) {
        return adj[v];
    }

    public void show() {
        for (int v = 0; v < vCount; v++) {
            LinkedBag<Integer> bag = adj[v];
            System.out.print(v + ": ");
            for (Integer x : bag) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}