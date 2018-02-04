package bo.zhao.practice.algorithm;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/26
 */
public class BinarySearch implements Search {

    @Override
    public int search(int key, int[] arr) {
        int a = 0;
        int n = arr.length - 1;

        while (a <= n) {
            int mid = a + (n - a) / 2;
            if (key < arr[mid]) {
                n = mid - 1;
            } else if (key > arr[mid]) {
                a = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
