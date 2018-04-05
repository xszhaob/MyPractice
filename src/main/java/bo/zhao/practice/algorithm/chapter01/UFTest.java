package bo.zhao.practice.algorithm.chapter01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/3/30
 */
public class UFTest {

    private static final int TINY_COUNT = 10;

    private static final int MEDIUM_COUNT = 625;

    private static final int LARGE_COUNT = 1000000;

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
//        testQuickFind(new QuickFind(MEDIUM_COUNT));
//        System.out.println("cost " + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();
        testQuickFind(new WeightedQuickUnion(LARGE_COUNT));
        System.out.println("cost " + (System.currentTimeMillis() - start) + "ms");
    }

    private static void testQuickFind(UF uf) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\zhaobo\\Desktop\\largeUF.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(" ");
                int p = Integer.parseInt(split[0]);
                int q = Integer.parseInt(split[1]);
                if (uf.connected(p, q)) {
                    line = reader.readLine();
                    continue;
                }
                uf.union(p, q);
                line = reader.readLine();
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
