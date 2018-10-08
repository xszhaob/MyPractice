package bo.zhao.practice.algorithm.chapter04;

import bo.zhao.practice.algorithm.chapter03.ST;
import bo.zhao.practice.algorithm.chapter03._3_4.SeparateChainingHashST;

import java.util.List;

public class SymbolGraph {

    private ST<String, Integer> st;
    private String[] keys;
    private Graph g;

    public SymbolGraph(List<String[]> in) {
        st = new SeparateChainingHashST<>();

        for (String[] str : in) {
            for (String s : str) {
                if (!st.contains(s)) {
                    st.put(s, st.size());
                }
            }
        }

        keys = new String[st.size()];

        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        g = new Graph(st.size());

        for (String[] strings : in) {
            String name = strings[0];
            Integer v = st.get(name);
            for (int i = 1; i < strings.length; i++) {
                g.addEdge(v, st.get(strings[i]));
            }
        }
    }

    /**
     * 判断key是否是一个顶点
     */
    public boolean contains(String key) {
        return st.contains(key);
    }

    /**
     * key的索引
     */
    public int index(String key) {
        return st.get(key);
    }

    /**
     * 索引v的顶点名
     */
    public String name(int v) {
        return keys[v];
    }

    /**
     * 隐藏的Graph对象
     */
    Graph graph() {
        return g;
    }
}
