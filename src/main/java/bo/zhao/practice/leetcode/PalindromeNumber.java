package bo.zhao.practice.leetcode;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            return false;
        }
        if (x <= 9) {
            return true;
        }

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
