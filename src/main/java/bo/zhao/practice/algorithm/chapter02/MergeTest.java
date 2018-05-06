package bo.zhao.practice.algorithm.chapter02;

import org.junit.Test;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/15
 */
public class MergeTest {

    @Test
    public void test() {
        for (int i = 1; i <= 512; i++) {
            Merge merge = new Merge();
            sort(i, merge);
            System.out.println(
                            "size = " + i
                            + " count = " + merge.count.get() +
                            ", 6N(log(N)) = " + (6 * i * log(i, 2)));
        }

        for (int i = 1; i <= 512; i++) {
            MergeBU merge = new MergeBU();
            sort(i, merge);
            System.out.println(
                    "size = " + i
                            + " count = " + merge.count.get() +
                            ", 6N(log(N)) = " + (6 * i * log(i, 2)));
        }
    }


    private void sort(int size, Sort sort) {
        Comparable[] a = new Comparable[size];
        for (int i = 0; i < size; i++) {
            a[i] = size - i;
        }
        sort.sort(a);
    }

    public static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }
}
