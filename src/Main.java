import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        System.out.println("Bienvenu");
        Graph graph = new Graph();
        graph.readFile("dataski.txt");
        //System.out.println(graph);
        Resort lvl = new Resort();
        lvl.add(RoadType.R);
        //lvl.add(RoadType.N);
        lvl.add(RoadType.V);
        lvl.add(RoadType.B);
        //lvl.add(RoadType.SURF);
        //lvl.add(RoadType.KL);
        Stack<Vertex> st = graph.shortestPath(graph._vertex.get(0), graph._vertex.get(5), lvl);
        for (int i=0; i < st.size(); i++){
            Vertex v = st.get(i);
            System.out.println(v);
        }


    }
}
