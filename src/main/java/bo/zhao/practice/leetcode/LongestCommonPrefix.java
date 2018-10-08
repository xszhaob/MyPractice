package bo.zhao.practice.leetcode;


public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        String shortestStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < shortestStr.length()) {
                shortestStr = strs[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shortestStr.length(); i++) {
            char c = shortestStr.charAt(i);
            for (String str : strs) {
                if (c != str.charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[] {"flower","flow","flowht"}));
    }
}
