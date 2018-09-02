package bo.zhao.practice.algorithm.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bo.Zhao
 * @since 18/9/2
 */
public class TestPaths {
    public static void main(String[] args) {
        Graph graph = new Graph(6, tinyGraph());
        int s = 0;
        test(new DepthFirstPaths(graph, s), s, graph);
        test(new BreadthFirstPaths(graph, s), s, graph);
    }


    private static void test(Paths paths, int s, Graph graph) {
        for (int i = 0; i < graph.vCount(); i++) {
            System.out.print(s + " to " + i + ": ");
            if (paths.hasPathTo(i)) {
                for (Integer x : paths.pathTo(i)) {
                    if (x == s) {
                        System.out.print(x);
                    } else {
                        System.out.print("-" + x);
                    }
                }
            }
            System.out.println();
        }
    }

    private static List<int[]> tinyGraph() {
        List<int[]> graph = new ArrayList<>(8);
        graph.add(new int[]{0, 5});
        graph.add(new int[]{2, 4});
        graph.add(new int[]{2, 3});
        graph.add(new int[]{1, 2});
        graph.add(new int[]{0, 1});
        graph.add(new int[]{3, 4});
        graph.add(new int[]{3, 5});
        graph.add(new int[]{0, 2});
        return graph;
    }
}
