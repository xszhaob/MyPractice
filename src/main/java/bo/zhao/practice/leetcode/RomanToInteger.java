package bo.zhao.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>(13);
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            String roman = String.valueOf(c);
            if (i < s.length() - 1) {
                char nextChar = s.charAt(i + 1);
                if (map.containsKey(c + "" + nextChar)) {
                    roman = c + "" + nextChar;
                    i++;
                }
            }
            result += map.get(roman);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
}
