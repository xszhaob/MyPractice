package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;
import bo.zhao.practice.algorithm.chapter01.LinkedStack;
import bo.zhao.practice.algorithm.chapter01.Queue;
import bo.zhao.practice.algorithm.chapter01.Stack;

/**
 * @author Bo.Zhao
 * @since 18/9/14
 */
public class DepthFirstOrder {
    private boolean[] marked;
    /**
     * 所有顶点的前序排列
     */
    private Queue<Integer> pre;
    /**
     * 所有顶点的后序排列
     */
    private Queue<Integer> post;
    /**
     * 所有顶点的逆后续排列
     */
    private Stack<Integer> reversePost;


    public DepthFirstOrder(Digraph digraph) {
        this.pre = new LinkedQueue<>();
        this.post = new LinkedQueue<>();
        this.reversePost = new LinkedStack<>();
        this.marked = new boolean[digraph.vCount()];

        for (int v = 0; v < digraph.vCount(); v++) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        pre.enqueue(v);
        marked[v] = true;

        for (Integer w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }

        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
