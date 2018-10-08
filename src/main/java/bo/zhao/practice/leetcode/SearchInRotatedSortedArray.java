package bo.zhao.practice.leetcode;

import java.util.Arrays;

public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }
        int i = searchRotatedIndex(nums);

        int index = Arrays.binarySearch(nums, 0, i, target);
        if (index >= 0) {
            return index;
        }
        index = Arrays.binarySearch(nums, i, nums.length, target);
        if (index >= 0) {
            return index;
        }
        return -1;
    }

    private int searchRotatedIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[left] < nums[mid]) {
                left = mid;
            } else if (nums[left] > nums[mid]) {
                right = mid;
            } else {
                break;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray array = new SearchInRotatedSortedArray();
        int search = array.search(new int[]{2, 1}, 2);
        System.out.println(search);
    }
}
