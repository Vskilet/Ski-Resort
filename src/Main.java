import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.samples.SimpleGraphDraw;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        System.out.println("Bienvenu");
        Graph graph = new Graph();
        graph.readFile("dataski.txt");
        //System.out.println(graph);
        Resort lvl = new Resort();
        /*lvl.add(RoadType.R);
        lvl.add(RoadType.N);
        lvl.add(RoadType.V);
        lvl.add(RoadType.B);
        lvl.add(RoadType.SURF);
        lvl.add(RoadType.KL);


        Stack<Vertex> st = graph.shortestPath(graph._vertex.get(0), graph._vertex.get(5), lvl);
        System.out.println("ShortestPath");
        for (int i=0; i < st.size(); i++){
            Vertex v = st.get(i);
            System.out.println(v);
        }
        ArrayList<Vertex> test = graph.dfs(graph._vertex.get(7), lvl);
        System.out.println(test.size());
        System.out.println("DFS");
        for (int i=0; i < test.size(); i++){
            System.out.println(test.get(i));
        }*/
        Stack<Vertex> st = graph.shortestPath(graph._vertex.get(0), graph._vertex.get(5), lvl);
        DrawableGraph frame = new DrawableGraph(graph, st);
        frame.drawGraph();

    }
}
