package bo.zhao.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int first = nums[i];
            if (first > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 1; j++) {
                int second = nums[j];
                if (first + second > 0 && second > 0) {
                    break;
                }
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int third = 0 - first - second;
                int index = Arrays.binarySearch(nums, j + 1, nums.length, third);
                if (index > j) {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(first);
                    list.add(second);
                    list.add(third);
                    result.add(list);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(threeSum(new int[] {-1,0,1,2,-1,-4}));
    }
}
