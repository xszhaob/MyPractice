package bo.zhao.practice.algorithm;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/9
 */
public class Stopwatch {
    private final long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public long elapsedTime() {
        return System.currentTimeMillis() - start;
    }
}
