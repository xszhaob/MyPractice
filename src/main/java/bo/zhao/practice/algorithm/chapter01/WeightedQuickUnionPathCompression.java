package bo.zhao.practice.algorithm.chapter01;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/4
 */
public class WeightedQuickUnionPathCompression implements UF {
    private int count;
    private int[] parent;
    private int[] size;

    public WeightedQuickUnionPathCompression(int count) {
        this.count = count;
        parent = new int[count];
        size = new int[count];

        for (int i = 0; i < count; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        if (size[pRoot] < size[qRoot]) {
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
        count--;
    }

    @Override
    public int find(int p) {
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
        }
        while (p != root) {
            int tmp = parent[p];
            parent[p] = root;
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
