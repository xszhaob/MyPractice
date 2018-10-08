package bo.zhao.practice.leetcode;

import java.util.Arrays;

public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return new int[]{0, 0};
            } else {
                return new int[]{-1, -1};
            }
        }

        int left = findLeft(nums, target);
        int right = findRight(nums, target);
        return new int[]{left, right};
    }

    private int findLeft(int[] nums, int target) {
        int minIndex = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                minIndex = mid;
                right = mid - 1;
            }
        }
        return minIndex;
    }

    private int findRight(int[] nums, int target) {
        int maxIndex = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                maxIndex = mid;
                left = mid + 1;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        FindFirstAndLastPositionOfElementInSortedArray array = new FindFirstAndLastPositionOfElementInSortedArray();
        int[] arr = {5,7,7,8,10};
        System.out.println(Arrays.toString(array.searchRange(arr, -8)));
    }
}
