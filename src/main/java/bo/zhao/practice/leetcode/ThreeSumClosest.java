package bo.zhao.practice.leetcode;

import java.util.Arrays;

public class ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Illegal Argument");
        }

        Arrays.sort(nums);

        int closest = Integer.MAX_VALUE;
        int left, right;
        int sum;
        int temp = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                sum = nums[i] + nums[left] + nums[right];
                if (target == sum) {
                    return target;
                }
                int abs = Math.abs(target - sum);
                if (abs < temp) {
                    temp = abs;
                    closest = sum;
                }
                if (sum > target) {
                    right--;
                    while (right > 0 && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else {
                    left++;
                    while (left < nums.length && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
            }
        }
        return closest;
    }
}
