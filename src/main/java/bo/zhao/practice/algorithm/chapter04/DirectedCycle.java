package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.LinkedStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bo.Zhao
 * @since 18/9/13
 */
public class DirectedCycle {
    private boolean marked[];
    private int[] edgeTo;
    private LinkedStack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph digraph) {
        onStack = new boolean[digraph.vCount()];
        marked = new boolean[digraph.vCount()];
        edgeTo = new int[digraph.vCount()];
        for (int v = 0; v < digraph.vCount(); v++) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    public DirectedCycle(EdgeWeightedDigraph digraph) {
        onStack = new boolean[digraph.vCount()];
        marked = new boolean[digraph.vCount()];
        edgeTo = new int[digraph.vCount()];
        for (int v = 0; v < digraph.vCount(); v++) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        onStack[v] = true;
        marked[v] = true;

        for (Integer w : digraph.adj(v)) {
            if (hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
            } else if (onStack[w]) {
                cycle = new LinkedStack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    private void dfs(EdgeWeightedDigraph digraph, int v) {
        onStack[v] = true;
        marked[v] = true;

        for (DirectedEdge edge : digraph.adj(v)) {
            int w = edge.to();
            if (hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
            } else if (onStack[w]) {
                cycle = new LinkedStack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        List<int[]> in = new ArrayList<>();
        in.add(new int[]{0, 1});
        in.add(new int[]{0, 5});
        in.add(new int[]{2, 0});
        in.add(new int[]{2, 3});
        in.add(new int[]{3, 2});
        in.add(new int[]{3, 5});
        in.add(new int[]{4, 2});
        in.add(new int[]{4, 3});
        in.add(new int[]{5, 4});
        in.add(new int[]{6, 0});
        in.add(new int[]{6, 4});
        in.add(new int[]{6, 9});
        in.add(new int[]{7, 6});
        in.add(new int[]{7, 8});
        in.add(new int[]{8, 7});
        in.add(new int[]{8, 9});
        in.add(new int[]{9, 10});
        in.add(new int[]{9, 11});
        in.add(new int[]{10, 12});
        in.add(new int[]{11, 12});
        in.add(new int[]{11, 4});
        in.add(new int[]{12, 9});

        Digraph digraph = new Digraph(in, 13);
        DirectedCycle cycle = new DirectedCycle(digraph);
        if (cycle.hasCycle()) {
            System.out.println("has cycle");
            for (Integer v : cycle.cycle()) {
                System.out.print(v + " ");
            }
        }
    }
}
