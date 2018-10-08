package bo.zhao.practice.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.bilibili.com/video/av9830088?from=search&seid=6487721469260499945
 */
public class QuanPaiLieByLeetcode {
    public void backtrack(int n,
                          List<Integer> numsList,
                          List<List<Integer>> output,
                          int first) {

        if (first == n) {
            output.add(new ArrayList<>(numsList));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(numsList, first, i);
            backtrack(n, numsList, output, first + 1);
            Collections.swap(numsList, first, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output = new LinkedList<>();

        ArrayList<Integer> numsList = new ArrayList<>();
        for (int num : nums)
            numsList.add(num);

        int n = nums.length;
        backtrack(n, numsList, output, 0);
        return output;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        QuanPaiLieByLeetcode leetcode = new QuanPaiLieByLeetcode();
        List<List<Integer>> permute = leetcode.permute(arr);
        for (List<Integer> integers : permute) {
            System.out.println(integers);
        }
    }
}
