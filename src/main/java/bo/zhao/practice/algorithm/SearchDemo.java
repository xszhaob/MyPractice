package bo.zhao.practice.algorithm;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/26
 */
public class SearchDemo {

    public static void main(String[] args) {
        int[] arr = getArr(1000000);

        int[] filterArr = getArrReverse(100000);

        process(arr, filterArr, new BinarySearch());

        process(arr, filterArr, new ViolenceSearch());
    }



    private static void process(int[] arr, int[] filterArr, Search search) {
        long start = System.currentTimeMillis();
        for (int i : arr) {
            if (search.search(i, filterArr) < 0) {
                System.out.print("");
            }
        }
        System.out.println("");
        System.out.println("cost " + (System.currentTimeMillis() - start) + " ms");
    }


    private static int[] getArr(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static int[] getArrReverse(int length) {
        int[] arr = new int[length];
        for (int i = length - 1, count = 0; i >= 0; i--, count++) {
            arr[count] = i;
        }
        return arr;
    }
}
