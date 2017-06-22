package bo.zhao.practice.nio;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * author:xszhaobo
 * <p/>
 * date:2016/12/12
 * <p/>
 * package_name:bo.zhao.practice.nio
 * <p/>
 * project: MyPractice
 */
public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String,Charset> charSets = Charset.availableCharsets();
        for (String next : charSets.keySet()) {
            System.out.print(next);
            Iterator<String> iterator1 = charSets.get(next).aliases().iterator();
            if (iterator1.hasNext()) {
                System.out.print(": ");
            }
            while (iterator1.hasNext()) {
                System.out.print(iterator1.next());
                if (iterator1.hasNext()) {
                    System.out.print(",");
                }
            }
            System.out.println("");
        }
    }
}
