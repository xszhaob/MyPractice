package bo.zhao.practice.algorithm.chapter04;

/**
 * 任意顶点对之间的最短路径
 */
public class DijkstraAllPairsSP {
    private DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph digraph) {
        all = new DijkstraSP[digraph.vCount()];
        for (int v = 0; v < digraph.vCount(); v++) {
            all[v] = new DijkstraSP(digraph, v);
        }
    }

    public Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }

    public double dist(int s, int t) {
        return all[s].distTo(t);
    }
}
