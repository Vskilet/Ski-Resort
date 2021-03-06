import edu.uci.ics.jung.graph.DirectedSparseGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by victor on 13/11/15.
 */
public class Graph {
    private String _name;
    public List<Edges> _edges;
    public List<Vertex> _vertex;

    public Graph(){
        _edges = new ArrayList<Edges>();
        _vertex = new ArrayList<Vertex>();
    };

    public void readFile(String fileName){
        int nb;
        String[] vertexLine = new String[3];
        String[] edgeLine = new String[5];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            nb = Integer.parseInt(reader.readLine());
            for (int i = 0; i < nb; i++) {
                vertexLine = reader.readLine().split("\t");
                _vertex.add(new Vertex(Integer.parseInt(vertexLine[0]), vertexLine[1], Integer.parseInt(vertexLine[2])));
            }
            nb = Integer.parseInt(reader.readLine());
            for (int i = 0; i < nb; i++) {
                edgeLine = reader.readLine().split("\t");
                _edges.add(new Edges(Integer.parseInt(edgeLine[0]), edgeLine[1], RoadType.parse(edgeLine[2]), _vertex.get(Integer.parseInt(edgeLine[3])-1), _vertex.get(Integer.parseInt(edgeLine[4])-1)));
            }

            reader.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public String toString() {
        String out = new String(_vertex.size() + " vertices :\n");
        for (Vertex v : _vertex)
            out += "  - " + v.toString() + "\n";
        out += _edges.size() + " edges :\n";
        for (Edges e : _edges)
            out += "  - " + e.toString() + "\n";
        return out;
    }
    public void resetMark(){
       for (Vertex v : _vertex){
           v.set_mark(false);
           v.set_preVertex(null);
           v.set_distance(-1f);
       }
    }

    public Stack<Vertex> shortestPath(Vertex v_start, Vertex v_end, Resort r_choice){
        Vertex current;
        Stack<Vertex> shoPath = new Stack<Vertex>();
        List<Vertex> notSee = new LinkedList<Vertex>();
        resetMark();
        v_start.set_distance(0);
        notSee.add(v_start);

        while (!notSee.isEmpty()) {
            current = notSee.get(0);
            //Choose the min element
            for (Vertex v : notSee) {
                if (current.greaterThan(v)) {
                    current = v;
                }
            }
            current.set_mark(true);
            for (Edges e : current.get_leaveEdges()){
                if (!e.get_toVertex().get_mark() && r_choice.isValid(e)){
                    if (e.get_toVertex().get_distance() == -1f || e.get_toVertex().get_distance() > e.get_fromVertex().get_distance() + e.get_time()){
                        e.get_toVertex().set_distance(e.get_fromVertex().get_distance() + e.get_time());
                        e.get_toVertex().set_preVertex(e.get_fromVertex());
                    }
                    if (notSee.contains(e.get_toVertex())){
                        notSee.remove(e.get_toVertex());
                    }
                    notSee.add(e.get_toVertex());
                }
            }
            notSee.remove(current);
        }
        current = v_end;
        while (current.get_preVertex() != null){
            shoPath.add(current);
            current = current.get_preVertex();
        }
        shoPath.add(v_start);
        return shoPath;

    }

    public Stack<Vertex> shortestPath(String start, String end, Resort r_choice){
        Vertex v_start = null;
        Vertex v_end = null;
        for (Vertex v : _vertex){
            if (v.get_name().equals(start)){
                v_start  = v;
            }
            if (v.get_name().equals(end)){
                v_end = v;
            }
        }
        return shortestPath(v_start, v_end, r_choice);
    }
    public ArrayList<Vertex> dfs(Vertex v_start, Resort r_choice){
        r_choice.add(RoadType.TS);
        r_choice.add(RoadType.TC);
        r_choice.add(RoadType.TK);
        r_choice.add(RoadType.TPH);
        r_choice.add(RoadType.TSD);

        ArrayList<Vertex> listOut = new ArrayList<Vertex>();
        Stack<Vertex> listVertex = new Stack<Vertex>();
        listVertex.add(v_start);
        while (!listVertex.isEmpty()){
            v_start = listVertex.pop();
            if (!listOut.contains(v_start)){
                listOut.add(v_start);
                for (Edges e : v_start.get_leaveEdges()){
                    if (r_choice.contains(e.get_type())){
                        listVertex.add(e.get_toVertex());
                   }
                }
            }
        }
        return listOut;
    }
    public ArrayList<Vertex> dfs(String start, Resort r_choice){
        Vertex v_start = null;
        for (Vertex v : _vertex){
            if (v.get_name().equals(start)){
                v_start  = v;
            }
        }
        return dfs(v_start, r_choice);
    }


}
