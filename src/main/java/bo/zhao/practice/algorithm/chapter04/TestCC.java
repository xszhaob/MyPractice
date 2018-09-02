package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.LinkedBag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bo.Zhao
 * @since 18/9/2
 */
public class TestCC {
    public static void main(String[] args) {
        int vCount = 13;
        List<int[]> in = tinyGraph();
        Graph graph = new Graph(vCount, in);
        graph.show();
        test(graph);
    }

    @SuppressWarnings("unchecked")
    private static void test(Graph graph) {
        CC cc = new CC(graph);

        int m = cc.count();
        System.out.println(m + " components");

        LinkedBag<Integer>[] components = (LinkedBag<Integer>[]) new LinkedBag[m];
        for (int i = 0; i < m; i++) {
            components[i] = new LinkedBag<>();
        }

        for (int v = 0; v < graph.vCount(); v++) {
            components[cc.id(v)].add(v);
        }

        for (LinkedBag<Integer> component : components) {
            for (Integer w : component) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }

    private static List<int[]> tinyGraph() {
        List<int[]> data = new ArrayList<>();
        data.add(new int[]{0, 5});
        data.add(new int[]{4, 3});
        data.add(new int[]{0, 1});
        data.add(new int[]{9, 12});
        data.add(new int[]{6, 4});
        data.add(new int[]{5, 4});
        data.add(new int[]{0, 2});
        data.add(new int[]{11, 12});
        data.add(new int[]{9, 10});
        data.add(new int[]{0, 6});
        data.add(new int[]{7, 8});
        data.add(new int[]{9, 11});
        data.add(new int[]{5, 3});
        return data;
    }
}
