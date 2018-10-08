package bo.zhao.practice.leetcode;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }


    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray array = new RemoveDuplicatesFromSortedArray();
        int[] ints = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int i = array.removeDuplicates(ints);
        System.out.println(i);
        System.out.println(Arrays.toString(ints));

    }
}
