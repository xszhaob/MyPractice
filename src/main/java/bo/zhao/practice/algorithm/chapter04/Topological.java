package bo.zhao.practice.algorithm.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bo.Zhao
 * @since 18/9/14
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph digraph) {
        DirectedCycle cycle = new DirectedCycle(digraph);
        if (cycle.hasCycle()) {
            return;
        }
        DepthFirstOrder dfs = new DepthFirstOrder(digraph);
        order = dfs.reversePost();
        System.out.println(dfs.pre());
        System.out.println(dfs.post());
        System.out.println(dfs.reversePost());
    }

    public Topological(EdgeWeightedDigraph digraph) {
        DirectedCycle cycle = new DirectedCycle(digraph);
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }


    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>(13);
        list.add(new int[]{0, 1});
        list.add(new int[]{0, 5});
        list.add(new int[]{0, 6});
        list.add(new int[]{2, 0});
        list.add(new int[]{2, 3});
        list.add(new int[]{3, 5});
        list.add(new int[]{5, 4});
        list.add(new int[]{6, 4});
        list.add(new int[]{6, 9});
        list.add(new int[]{7, 6});
        list.add(new int[]{8, 7});
        list.add(new int[]{9, 10});
        list.add(new int[]{9, 11});
        list.add(new int[]{9, 12});
        list.add(new int[]{11, 12});

        Digraph digraph = new Digraph(list, 13);
        Topological topological = new Topological(digraph);
        System.out.println(topological.order());
    }
}
