package bo.zhao.practice.algorithm.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bo.Zhao
 * @since 18/9/2
 */
public class TestSearch {
    public static void main(String[] args) {
        test(13, tinyGraph(), 0);
        test(13, tinyGraph(), 9);
    }

    private static void test(int vCount, List<int[]> in, int s) {
        Graph g = new Graph(vCount, in);

        Search search = new DepthFirstSearch(g, s);

        for (int i = 0; i < g.vCount(); i++) {
            if (search.marked(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        if (search.count() != g.vCount()) {
            System.out.print("NOT ");
        }
        System.out.println("connected");
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
