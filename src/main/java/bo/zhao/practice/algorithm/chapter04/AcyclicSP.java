package bo.zhao.practice.algorithm.chapter04;

public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph digraph, int s) {
        edgeTo = new DirectedEdge[digraph.vCount()];
        distTo = new double[digraph.vCount()];

        for (int v = 0; v < digraph.vCount(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological top = new Topological(digraph);

        for (Integer v : top.order()) {
            relax(digraph, v);
        }
    }

    private void relax(EdgeWeightedDigraph digraph, Integer v) {
        for (DirectedEdge edge : digraph.adj(v)) {
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.weight()) {
                distTo[w] = distTo[v] + edge.weight();
                edgeTo[w] = edge;
            }
        }
    }
}
