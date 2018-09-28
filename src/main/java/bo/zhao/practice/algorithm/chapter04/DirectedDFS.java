package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.LinkedBag;

/**
 * 有向图的可达性
 *
 * @author Bo.Zhao
 * @since 18/9/12
 */
public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph digraph, int s) {
        marked = new boolean[digraph.vCount()];

        dfs(digraph, s);
    }

    public DirectedDFS(Digraph digraph, Iterable<Integer> sources) {
        marked = new boolean[digraph.vCount()];

        for (Integer s : sources) {
            if (!marked[s]) {
                dfs(digraph, s);
            }
        }
    }

    private void dfs(Digraph digraph, int s) {
        marked[s] = true;

        for (Integer v : digraph.adj(s)) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    private void validateVertex(int v) {
        int vCount = marked.length;
        if (v < 0 || v >= vCount) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vCount - 1));
        }
    }


    public static void main(String[] args) {
        Digraph digraph = new Digraph(GraphMocker.mockTinyDG(), GraphMocker.TINY_DG);
        LinkedBag<Integer> ite = new LinkedBag<>();
        ite.add(1);
        ite.add(2);
        ite.add(6);
        DirectedDFS dfs = new DirectedDFS(digraph, ite);

        for (int v = 0; v < digraph.vCount(); v++) {
            if (dfs.marked(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();
    }
}
