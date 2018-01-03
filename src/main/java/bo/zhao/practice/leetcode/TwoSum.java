package bo.zhao.practice.leetcode;

import java.util.Arrays;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 17/12/26
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] numArr = {1, 6, 9, 10};
        System.out.println(Arrays.toString(twoSum(numArr, 11)));
    }


    public static int[] twoSum(int[] numArr, int target) {
        for (int i = 0; i < numArr.length - 1; i++) {
            for (int j = i + 1; j < numArr.length; j++) {
                if (numArr[j] == target - numArr[i]) {
                    return new int[] {i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
