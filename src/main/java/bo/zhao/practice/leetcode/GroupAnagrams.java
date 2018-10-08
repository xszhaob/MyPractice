package bo.zhao.practice.leetcode;

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>(0);
        }

        Map<String, List<String>> map = new HashMap<>();
        String[] sortStr = new String[strs.length];
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            sortStr[i] = new String(chars);
        }

        for (int i = 0; i < sortStr.length; i++) {
            List<String> strings = map.get(sortStr[i]);
            if (strings == null) {
                strings = new ArrayList<>();
            }
            strings.add(strs[i]);
            map.put(sortStr[i], strings);
        }

        return new ArrayList<>(map.values());
    }
}
