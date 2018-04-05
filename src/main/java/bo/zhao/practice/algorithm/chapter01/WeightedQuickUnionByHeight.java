package bo.zhao.practice.algorithm.chapter01;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/4
 */
public class WeightedQuickUnionByHeight implements UF {
    private int count;
    private int[] height;
    private int[] parent;

    public WeightedQuickUnionByHeight(int count) {
        this.count = count;
        parent = new int[count];
        height = new int[count];

        for (int i = 0; i < count; i++) {
            parent[i] = i;
            height[i] = 0;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        if (height[pRoot] < height[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (height[pRoot] > height[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            height[pRoot]++;
        }
        count--;
        System.out.println("height[pRoot]=" + height[pRoot]);
        System.out.println("height[qRoot]=" + height[qRoot]);
    }

    @Override
    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
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
