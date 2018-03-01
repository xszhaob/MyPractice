package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.RandomBag;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/3/1
 */
public class Ex1_31_4 {
    public static void main(String[] args) {
        RandomBag<String> bag = new RandomBag<>();
        bag.add("123");
        bag.add("456");
        bag.add("890");
        bag.add("777");


        for (String s : bag) {
            System.out.println(s);
        }

        System.out.println("*******************");

        for (String s : bag) {
            System.out.println(s);
        }

        System.out.println("*******************");

        for (String s : bag) {
            System.out.println(s);
        }

        System.out.println("*******************");

        for (String s : bag) {
            System.out.println(s);
        }
    }
}
