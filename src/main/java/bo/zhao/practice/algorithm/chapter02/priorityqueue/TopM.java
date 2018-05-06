package bo.zhao.practice.algorithm.chapter02.priorityqueue;

import java.util.Arrays;
import java.util.Random;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/30
 */
public class TopM {
    public static void main(String[] args) {
        Random random = new Random();
        MaxPQ<Integer> intPQ = new MaxPQ<>(11);
        for (int i = 0; i < 10; i++) {
            intPQ.insert(random.nextInt(100));
        }
        System.out.println(Arrays.toString(intPQ.getA()));
        while (!intPQ.isEmpty()) {
            System.out.println(intPQ.delMax());
        }

    }
}
