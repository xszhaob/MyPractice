package bo.zhao.practice.algorithm.chapter04;

import java.util.ArrayList;
import java.util.List;

public class DegreesOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(mockData());


        String source = "JFK";
        if (!sg.contains(source)) {
            System.out.println("JFK not in database.");
            return;
        }

        Graph graph = sg.graph();
        int index = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, index);

        String las = "LAS";
        if (sg.contains(las)) {
            int t = sg.index(las);
            if (!bfs.hasPathTo(t)) {
                System.out.println("Not connected");
            }
            for (Integer integer : bfs.pathTo(t)) {
                System.out.println("    " + sg.name(integer));
            }
        }
    }


    private static List<String[]> mockData() {
        List<String[]> result = new ArrayList<>();
        result.add(new String[]{"JFK", "MCO"});
        result.add(new String[]{"ORD", "DEN"});
        result.add(new String[]{"ORD", "HOU"});
        result.add(new String[]{"DFW", "PHX"});
        result.add(new String[]{"JFK", "ATL"});
        result.add(new String[]{"ORD", "DFW"});
        result.add(new String[]{"ORD", "PHX"});
        result.add(new String[]{"ATL", "HOU"});
        result.add(new String[]{"DEN", "PHX"});
        result.add(new String[]{"PHX", "LAX"});
        result.add(new String[]{"JFK", "ORD"});
        result.add(new String[]{"DEN", "LAS"});
        result.add(new String[]{"DFW", "HOU"});
        result.add(new String[]{"ORD", "ATL"});
        result.add(new String[]{"LAS", "LAX"});
        result.add(new String[]{"ATL", "MCO"});
        result.add(new String[]{"HOU", "MCO"});
        result.add(new String[]{"LAS", "PHX"});
        return result;
    }
}
