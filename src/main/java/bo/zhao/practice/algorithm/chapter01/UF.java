package bo.zhao.practice.algorithm.chapter01;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/3/30
 */
public interface UF {

    /**
     * 连通两个分量
     */
    void union(int p, int q);

    int find(int p);

    boolean connected(int p, int q);

    int count();
}
