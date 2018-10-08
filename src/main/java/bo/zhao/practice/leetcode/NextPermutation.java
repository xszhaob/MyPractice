package bo.zhao.practice.leetcode;

import java.util.Arrays;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }

        int nextIndex = -1;
        int max = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > max) {
                max = nums[i];
            } else if (nums[i] < max) {
                nextIndex = i;
                break;
            }
        }

        if (nextIndex != -1) {
            Arrays.sort(nums, nextIndex + 1, nums.length);
            for (int i = nextIndex + 1; i < nums.length; i++) {
                if (nums[nextIndex] < nums[i]) {
                    int temp = nums[nextIndex];
                    nums[nextIndex] = nums[i];
                    nums[i] = temp;
                    break;
                }
            }
        } else {
            Arrays.sort(nums);
        }
    }


    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();

        int[] arr = {3,2,1};
        np.nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
    }
}
