package bo.zhao.practice.jp.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternInAction {

    public static void main(String[] args) {
        /*// \$\s*\{?\s*([\._0-9a-zA-Z]+)\s*\}?
        // \$\s*\{?\s*([a-zA-Z      ]+)\s*}?
        Pattern pattern = Pattern.compile("\\$\\s*\\{?\\s*([a-zA-Z]+)\\s*}?");
        String str = "$ { abc }$ {sdfsdf}";
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(sb, Matcher.quoteReplacement("113"));
            System.out.println(group);
            System.out.println(sb);
        }*/
        test();
    }

    private static void test() {
        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one cat two cats in the yard");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "dog");
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
