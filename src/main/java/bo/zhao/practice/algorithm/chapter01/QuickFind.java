package bo.zhao.practice.algorithm.chapter01;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/3/30
 */
public class QuickFind implements UF {
    private int[] id;
    /**
     * 连通的分量数
     */
    private int count;

    public QuickFind(int count) {
        id = new int[count];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
        this.count = count;
    }


    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        // 如果两个触点已经连通，直接返回
        if (i == j) {
            return;
        }

        // 把两个触点对应的分量合并为一个，实现触点连通
        for (int i1 = 0; i1 < id.length; i1++) {
            if (id[i1] == i) {
                id[i1] = j;
            }
        }
        count--;
    }

    @Override
    public int find(int p) {
        while (id[p] != p) {
            p = id[p];
        }
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }
}
