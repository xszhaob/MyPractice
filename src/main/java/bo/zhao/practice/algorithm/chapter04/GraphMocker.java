package bo.zhao.practice.algorithm.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bo.Zhao
 * @since 18/9/12
 */
public class GraphMocker {
    public static final int TINY_DG = 13;

    public static List<int[]> mockTinyDG() {
        List<int[]> result = new ArrayList<>();
        result.add(new int[]{4, 2});
        result.add(new int[]{2, 3});
        result.add(new int[]{3, 2});
        result.add(new int[]{6, 0});
        result.add(new int[]{0, 1});
        result.add(new int[]{2, 0});
        result.add(new int[]{11, 12});
        result.add(new int[]{12, 9});
        result.add(new int[]{9, 10});
        result.add(new int[]{9, 11});
        result.add(new int[]{8, 9});
        result.add(new int[]{10, 12});
        result.add(new int[]{11, 4});
        result.add(new int[]{4, 3});
        result.add(new int[]{3, 5});
        result.add(new int[]{7, 8});
        result.add(new int[]{8, 7});
        result.add(new int[]{5, 4});
        result.add(new int[]{0, 5});
        result.add(new int[]{6, 4});
        result.add(new int[]{6, 9});
        result.add(new int[]{7, 6});
        return result;
    }
}
