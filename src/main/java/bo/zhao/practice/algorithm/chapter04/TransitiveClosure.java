package bo.zhao.practice.algorithm.chapter04;

/**
 * 有向图中，顶点对的可达性
 * <p>
 * 无论对于稀疏还是稠密的图，它都是理想解决方案，
 * 但它不适用于在实际应用中可能遇到的大型有向图，
 * 因为构造函数所需要的空间和V^2成正比，
 * 所需要的时间和V(V+E)成正比：共有V的DirectedDFS对象，
 * 每个所需要的空间都与V成正比。
 * 本质上，TransitiveClosure通过计算G的传递闭包来支持
 * 常数时间的查询-传递闭包矩阵中的第v行就是TransitiveClosure类
 * 中第v个元素的mark[]数组。
 *
 * @author Bo.Zhao
 * @since 18/9/17
 */
public class TransitiveClosure {
    private DirectedDFS[] all;

    public TransitiveClosure(Digraph digraph) {
        this.all = new DirectedDFS[digraph.vCount()];

        for (int v = 0; v < digraph.vCount(); v++) {
            all[v] = new DirectedDFS(digraph, v);
        }
    }

    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }
}
