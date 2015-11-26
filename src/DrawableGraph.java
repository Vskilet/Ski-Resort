import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 * Created by victor on 25/11/15.
 */
public class DrawableGraph extends JFrame implements ActionListener{
    private Graph _graph;
    private Stack<Vertex> _shortestPath;
    private DirectedSparseMultigraph<Vertex, Edges> _directedGraph;
    private DirectedSparseMultigraph<Vertex, Edges> _directedShortestPath;
    private JButton button;


    public DrawableGraph(Graph graph, Stack<Vertex> shortestPath) throws HeadlessException {
        super("Graph");
        this._graph = graph;
        this._shortestPath = shortestPath;
        this._directedGraph = new DirectedSparseMultigraph<Vertex, Edges>();
        this._directedShortestPath = new DirectedSparseMultigraph<Vertex, Edges>();
        this.button = new JButton("ERASE");
        button.addActionListener(this);

        this.setLayout(new GridLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100,100);
        this.setVisible(true);
    }

    public void drawGraph(){
        this.conversionGraph();
        this.conversionShortPath();
        this.getContentPane().removeAll();
        this.getContentPane().add(button);

        VisualizationImageServer vs = new VisualizationImageServer(new CircleLayout(this._directedGraph), new Dimension(500, 500));
        this.getContentPane().add(vs);
        this.pack();
    }

    public void conversionGraph() {
        for (Vertex v : _graph._vertex) {
            _directedGraph.addVertex(v);
        }
        for (Edges e : _graph._edges) {
            _directedGraph.addEdge(e, e.get_fromVertex(), e.get_toVertex());
        }
    }
    public void conversionShortPath() {
        for (Vertex v : _shortestPath){
            _directedShortestPath.addVertex(v);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Bravo Geoffrey");

        drawGraph();
    }
}
