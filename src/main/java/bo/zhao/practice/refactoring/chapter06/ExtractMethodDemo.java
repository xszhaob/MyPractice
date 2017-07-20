package bo.zhao.practice.refactoring.chapter06;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 17/7/20
 */
public class ExtractMethodDemo {

    private String name = "aaa";
    private List<Integer> list = new ArrayList<Integer>();

    {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }

    @Test
    public void printOwing() {
        int outstanding = 0;

        // print banner
        System.out.println("*************************");
        System.out.println("******Customer Owes******");
        System.out.println("*************************");

        // calculate outstanding
        for (Integer integer : list) {
            outstanding += integer;
        }

        // print details
        System.out.println("name:" + name);
        System.out.println("amount:" + outstanding);
    }
}
