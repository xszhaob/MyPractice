package bo.zhao.practice.algorithm.chapter01;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/4
 */
public class QuickUnionPathCompression implements UF {
    private int count;
    private int[] id;


    public QuickUnionPathCompression(int count) {
        this.count = count;
        id = new int[count];

        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        id[pRoot] = qRoot;
        this.count--;
    }

    @Override
    public int find(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }

        while (root != p) {
            int tmp = id[p];
            id[p] = root;
            p = tmp;
        }
        return root;
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
