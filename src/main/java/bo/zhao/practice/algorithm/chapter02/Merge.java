package bo.zhao.practice.algorithm.chapter02;

import bo.zhao.practice.algorithm.Stopwatch;

import java.util.Arrays;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/9
 */
public class Merge extends BaseMergeSort {

    @Override
    public long sort(Comparable[] a) {
        Stopwatch stopwatch = new Stopwatch();

        AUX = new Comparable[a.length];
        sort(a, 0, a.length - 1);

        return stopwatch.elapsedTime();
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }


    public static void main(String[] args) {
        Merge merge = new Merge();
        Comparable[] a = new Comparable[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        merge.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
