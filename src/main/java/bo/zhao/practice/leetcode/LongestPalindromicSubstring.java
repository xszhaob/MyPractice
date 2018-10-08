package bo.zhao.practice.leetcode;

import java.util.Objects;

public class LongestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        if (Objects.isNull(s)) {
            return null;
        }
        if (s.length() == 0) {
            return s;
        }
        int start = 0;
        int end = 0;
        for (int i = 1; i < s.length(); i++) {
            int len1 = length(s, i, i);
            int len2 = length(s, i, i + 1);
            int max = Math.max(len1, len2);
            if (max > end - start) {
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int length(String s, int left, int right) {
        int i = left;
        int j = right;
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }

    public static void main(String[] args) {
        System.out.println("***************");
    }
}
