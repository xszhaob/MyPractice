package bo.zhao.practice.algorithm.chapter01;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/3/30
 */
public class WeightedQuickUnion implements UF {
    /**
     * 父链接数组（由触点索引）
     */
    private int id[];
    /**
     * （由触点索引的）各个根节点所对应的分量的大小
     */
    private int sz[];
    /**
     * 连通分量的数量
     */
    private int count;

    public WeightedQuickUnion(int count) {
        id = new int[count];
        sz = new int[count];
        this.count = count;

        for (int i = 0; i < count; i++) {
            /*
            初始化中，
            所有节点的根节点即为自己，
            深度为1
             */
            id[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }
        // 把小树的根节点连接到大树的根节点
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] = sz[qRoot] + sz[pRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] = sz[pRoot] + sz[qRoot];
        }
        count--;
    }

    @Override
    public int find(int p) {
        while (p != id[p]) {
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
