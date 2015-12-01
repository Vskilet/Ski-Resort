import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.layout.CachingLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by victor on 25/11/15.
 */
public class DrawableGraph extends JFrame implements ActionListener {
    private Resort _lvl = new Resort();
    private Graph _graph;
    private Stack<Vertex> _shortestPath;
    private DirectedSparseMultigraph<Vertex, Edges> _directedGraph;
    private DirectedSparseMultigraph<Vertex, Edges> _directedShortestPath;
    private ArrayList<Vertex> _dfs;

    private JPanel p_dfs;
    private JPanel p_short;
    private JPanel p_screen;

    private JCheckBox chB;
    private JCheckBox chV;
    private JCheckBox chR;
    private JCheckBox chN;
    private JCheckBox chSURF;
    private JCheckBox chKL;

    private JButton button;
    private JButton b_dfs;
    private JComboBox box1;
    private JComboBox box2;
    private JComboBox box_dfs;
    private JLabel label1;
    private VisualizationImageServer vs;
    private VisualizationImageServer vs2;
    //private BasicVisualizationServer<Integer,String> vv;


    public DrawableGraph(Graph graph, Stack<Vertex> shortestPath) throws HeadlessException {
        super("Graph");
        this._lvl = new Resort();
        this._graph = graph;
        this._shortestPath = shortestPath;
        this._directedGraph = new DirectedSparseMultigraph<Vertex, Edges>();
        this._directedShortestPath = new DirectedSparseMultigraph<Vertex, Edges>();
        this._dfs = new ArrayList<Vertex>();

        this.p_dfs = new JPanel();
        this.p_short = new JPanel();

        this.p_screen = new JPanel();

        this.button = new JButton("Shortest Path");
        button.addActionListener(this);
        this.b_dfs = new JButton("DFS");
        b_dfs.addActionListener(this);

        this.chB = new JCheckBox("B");
        chB.addActionListener(this);
        this.chV = new JCheckBox("V");
        chV.addActionListener(this);
        this.chR = new JCheckBox("R");
        chR.addActionListener(this);
        this.chN = new JCheckBox("N");
        chN.addActionListener(this);
        this.chSURF = new JCheckBox("SURF");
        chSURF.addActionListener(this);
        this.chKL = new JCheckBox("KL");
        chKL.addActionListener(this);

        Object[] elements = new Object[]{"villaroger", "2", "3", "4", "aiguille-rouge", "6", "arc2000", "pre-saint-esprit", "9", "10", "11", "12", "grand-col", "14", "arpette", "16", "17", "18", "deux-tetes", "20", "21", "22", "col-des-frettes", "24", "25", "col-de-la-chal", "27", "28", "29", "arc1600", "31", "col-du-grand-renard", "33", "34", "35", "arc1800", "37"};
        this.box1 = new JComboBox(elements);
        this.box2 = new JComboBox(elements);
        this.box_dfs = new JComboBox(elements);
        this.label1 = new JLabel("Label");
        this.vs = new VisualizationImageServer(new FRLayout(this._directedGraph), new Dimension(450, 450));
        this.vs2 = new VisualizationImageServer(new FRLayout(this._directedShortestPath), new Dimension(600, 600));

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.setVisible(true);
    }

    public void drawGraph() {
        this.conversionGraph();
        this.conversionShortPath();
        this.getContentPane().removeAll();

        //this.getContentPane().add(vv);
        p_short.add(chB);
        p_short.add(chV);
        p_short.add(chR);
        p_short.add(chN);
        p_short.add(chSURF);

        p_short.add(box1);
        p_short.add(box2);
        p_short.add(button);

        this.getContentPane().add(p_short, BorderLayout.NORTH);

        p_dfs.add(box_dfs);
        p_dfs.add(b_dfs);
        this.getContentPane().add(p_dfs, BorderLayout.SOUTH);

        p_screen.add(label1);
        this.getContentPane().add(p_screen, BorderLayout.EAST);

        /*this.getContentPane().add(chB);
        this.getContentPane().add(chV);
        this.getContentPane().add(chR);
        this.getContentPane().add(chN);
        this.getContentPane().add(chSURF);

        this.getContentPane().add(box1);
        this.getContentPane().add(box2);
        this.getContentPane().add(button);

        this.getContentPane().add(box_dfs);
        this.getContentPane().add(b_dfs);

        this.getContentPane().add(label1);*/

        //VisualizationImageServer vs = new VisualizationImageServer(new FRLayout(this._directedGraph), new Dimension(500, 500));
        //this.getContentPane().add(visualizationServer);
        this.getContentPane().add(vs, BorderLayout.CENTER);
        //this.getContentPane().add(vs2, BorderLayout.WEST);
        //this.getContentPane().add(vs2);

        //this.vs.repaint();
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
        for (Vertex v : _shortestPath) {
            _directedShortestPath.addVertex(v);
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
        boolean selected = abstractButton.getModel().isSelected();

        if (actionEvent.getActionCommand().equals("Shortest Path")) {
            _shortestPath = _graph.shortestPath(this.box1.getModel().getSelectedItem().toString(), this.box2.getModel().getSelectedItem().toString(), _lvl);
            this.conversionShortPath();
            this.printShortestPath();
            drawGraph();
        } else if (actionEvent.getActionCommand().equals("DFS")) {
            this._dfs = _graph.dfs(this.box_dfs.getModel().getSelectedItem().toString(), _lvl);
            printDFS();
        } else if (selected) {
            if (actionEvent.getSource() == chB) {
                _lvl.add(RoadType.B);
                System.out.println("chB");
            } else if (actionEvent.getSource() == chV) {
                _lvl.add(RoadType.V);
                System.out.println("chV");
            } else if (actionEvent.getSource() == chR) {
                _lvl.add(RoadType.R);
                System.out.println("chR");
            } else if (actionEvent.getSource() == chN) {
                _lvl.add(RoadType.N);
                System.out.println("chN");
            } else if (actionEvent.getSource() == chSURF) {
                _lvl.add(RoadType.SURF);
                System.out.println("chSURF");
            } else if (actionEvent.getSource() == chKL) {
                _lvl.add(RoadType.KL);
                System.out.println("chKL");
            } else {
                System.out.println("erreur");
            }
        } else if (!selected) {
            if (actionEvent.getSource() == chB) {
                _lvl.remove(RoadType.B);
                System.out.println("uncheck B");
            } else if (actionEvent.getSource() == chV) {
                _lvl.remove(RoadType.V);
                System.out.println("uncheck V");

            } else if (actionEvent.getSource() == chR) {
                _lvl.remove(RoadType.R);
            } else if (actionEvent.getSource() == chN) {
                _lvl.remove(RoadType.N);
            } else if (actionEvent.getSource() == chSURF) {
                _lvl.remove(RoadType.SURF);
            } else if (actionEvent.getSource() == chKL) {
                _lvl.remove(RoadType.KL);
            } else {
                System.out.println("else");
            }
        }
    }

    /*public void colorPath() {
        final ArrayList<String> al = handleIntegerPath(_shortestPath); //simple fonction pour caster en string
        Transformer<String, Paint> edgeStrokeTransformer2 = new Transformer<String, Paint>() {
            public Paint transform(String sa) {
                return (al.contains(sa)?Color.BLUE:Color.BLACK);
            }
        };
        BasicVisualizationServer visualizationServer = null;
        visualizationServer.getRenderContext().setEdgeDrawPaintTransformer(edgeStrokeTransformer2);
        visualizationServer.repaint();
    }
    public ArrayList<String> handleIntegerPath(Stack<Vertex> arrayList){
        ArrayList<String> al = new ArrayList<String>();
        for (Vertex v : arrayList){
            al.add(v.get_name().toString());
        }
        return  al;
    }*/
    public void printShortestPath() {
        String out = "<html><body>ShortestPath<br/>";
        System.out.println("ShortestPath");
        for (Vertex v : _shortestPath) {
            System.out.println(v);
            out += v.toString() + "<br/>";
        }
        out += "</body></html>";
        this.label1.setText(out);
    }

    public void printDFS() {
        String out = "<html><body>DFS<br/>";
        System.out.println("DFS");
        for (Vertex v : _dfs) {
            System.out.println(v);
            out += v.toString() + "<br/>";
        }
        out += "</body></html>";
        this.label1.setText(out);
    }
}