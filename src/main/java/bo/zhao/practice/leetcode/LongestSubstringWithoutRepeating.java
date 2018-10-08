package bo.zhao.practice.leetcode;

import java.util.Objects;

public class LongestSubstringWithoutRepeating {
    public int lengthOfLongestSubstring(String s) {
        if (Objects.isNull(s) || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int longest = 0;
        int notRepeatOffset = 0;
        char[] arr = new char[s.length()];
        arr[0] = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[i] = c;
            int count = 1;
            int j = notRepeatOffset;
            for (; j < i; j++) {
                if (arr[j] == c) {
                    notRepeatOffset = j + 1;
                    break;
                } else {
                    count++;
                }
            }
            longest = Math.max(longest, count);
        }

        return longest;
    }
}
