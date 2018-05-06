package bo.zhao.practice.algorithm.chapter02.quicksort;

import bo.zhao.practice.algorithm.Stopwatch;
import bo.zhao.practice.algorithm.chapter02.Sort;

/**
 * 文件描述：三向切分的快速排序。
 * 它从左到右遍历数组一次，维护一个指针lt使得a[lo...lt-1]中的元素都小于v,
 * 一个指针gt使得a[gt+1....hi]中间的元素都大于v，一个指针i使得[lt...i-1]中的
 * 元素都等于v，a[i...gt]中的元素都还未确定。一开始i和lo相等，
 * 我们使用compareTo对a[i]进行三向比较来直接处理以下情况：
 * 1）a[i]小于v，将a[lt]和a[i]交换，同时i加1，lt加1；
 * 2）a[i]大于v，将a[i]和a[gt]交换，同时gt减1；
 * 3）a[i]等于v，将i加1。
 * 这些操作都保持数据元素不变且缩小gt-i的值。另外，除非和切分元素相等，其他元素都会被交换。
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/22
 */
public class Quick3way extends Sort {
    @Override
    public long sort(Comparable[] a) {
        Stopwatch stopwatch = new Stopwatch();
        sort(a, 0, a.length - 1);
        return stopwatch.elapsedTime();
    }


    private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int lt = lo;
        int i = lo + 1;
        int gt = hi;

        // 用a[lo]作为中间值
        Comparable v = a[lo];
        // 只要i没有到比v大的值的下标，就一直比较
        while (i <= gt) {
            // 比较a[i]和中间值v
            int com = a[i].compareTo(v);
            if (com < 0) {
                /*
                如果a[i]小于中间值。
                把i和lt下标对应的值交换，
                同时i往前进一位，lt也往前进一位。
                此时，lt之前的都小于v。
                 */
                exch(a, i++, lt++);
            } else if (com > 0) {
                /*
                a[i]大于中间值。
                把i和gt下标对应的值交换，
                同时gt下标往前进一位，此时，gt下标之后的数据一定都大于v。
                i的下标不能进一位，因为此时（交换后）i的值还不确定
                是小于中间值还是大于中间值还是等于中间值。
                 */
                exch(a, i, gt--);
            } else {
                // 如果a[i]等于中间值
                i++;
            }
        }
        // lo到lt-1的值均小于lt~gt的值
        sort(a, lo, lt - 1);
        // gt+1到hi的值均大于lt~gt的值
        sort(a, gt + 1, hi);

    }
}
