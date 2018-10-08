package bo.zhao.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        List<Integer> numsList = new ArrayList<>();
        for (int num : nums) {
            numsList.add(num);
        }

        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums.length, numsList, result, 0);
        return result;
    }


    public void backtrack(int n,
                          List<Integer> numsList,
                          List<List<Integer>> output,
                          int first) {

        if (first == n) {
            output.add(new ArrayList<>(numsList));
        }
        for (int i = first; i < n; i++) {
            if (i != first && numsList.get(i).equals(numsList.get(i - 1))) {
                continue;
            }
            Collections.swap(numsList, first, i);
            backtrack(n, numsList, output, first + 1);
            Collections.swap(numsList, first, i);
        }
    }


    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        List<List<Integer>> lists = permutations.permuteUnique(new int[]{0,1,0,0,9});
        for (List<Integer> integers : lists) {
            System.out.println(integers);
        }
    }
}
