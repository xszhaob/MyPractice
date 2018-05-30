package bo.zhao.practice.algorithm.chapter03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhaobo on 2018/5/17.
 */
public class FrequencyCounter {
   private static final Set<Character> SET = new HashSet<>();

    static {
        SET.add(',');
        SET.add('\"');
        SET.add(';');
        SET.add('.');
        SET.add('?');
        SET.add(')');
        SET.add('(');
        SET.add('!');
    }
    public static void main(String[] args) throws Exception {
        test(8, new BST<>());
    }


    private static void test(int minLen, ST<String, Integer> st) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\zhaobo\\Desktop\\tale.txt"))) {
            String line = reader.readLine();

            while (line != null) {
                if (line.trim().length() == 0) {
                    line = reader.readLine();
                    continue;
                }
                String[] split = clear(line.trim()).split("\\s+");
                if (split.length == 0) {
                    line = reader.readLine();
                    continue;
                }
                for (String s : split) {
                    if (s.length() < minLen) {
                        continue;
                    }
                    if (!st.contains(s)) {
                        st.put(s, 1);
                    } else {
                        st.put(s, st.get(s) + 1);
                    }
                }

                line = reader.readLine();
            }
        }

        String max = "";
        st.put(max, 0);
        for (String s : st.keys()) {
            if (st.get(s) > st.get(max)) {
                max = s;
            }
        }

        System.out.println(max + " " + st.get(max));
    }

    private static String clear(String str) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (SET.contains(c)) {
                sb.append(" ");
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
