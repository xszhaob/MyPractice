package bo.zhao.practice.algorithm.chapter01.practice;

/**
 * Created by zhaobo on 2018/2/28.
 */
public class Ex1_3_32 {
    public static void main(String[] args) {
        Steque<String> steque = new Steque<>();

        steque.push("c");
        steque.push("b");
        steque.push("a");
        steque.enqueue("d");

        System.out.println(steque);
    }
}
