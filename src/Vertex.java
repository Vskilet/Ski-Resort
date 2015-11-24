import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 13/11/15.
 */
public class Vertex {
    private int _num;
    private String _name;
    private int _altitude;

    private List<Edges> _leaveEdges;
    private List<Edges> _comeEdges;

    private boolean _mark;
    private float _distance;
    private Vertex _preVertex;

    public Vertex(int num, String name, int altitude){
        _num = num;
        _name = name;
        _altitude = altitude;

        _leaveEdges = new ArrayList<Edges>();
        _comeEdges = new ArrayList<Edges>();
    }

    public int get_altitude(){
        return _altitude;
    }
    public int get_num(){
        return _num;
    }
    public String get_name(){
        return _name;
    }
    public float get_distance() { return _distance; }
    public boolean get_mark() { return _mark; }
    public Vertex get_preVertex() { return _preVertex; }
    public List<Edges> get_leaveEdges() { return _leaveEdges; }

    public void set_preVertex(Vertex v) { _preVertex = v; }
    public void set_mark(boolean b) { _mark = b ; }
    public void set_distance(float d) { _distance = d; }
    public void addLeaveEdges(Edges e){ _leaveEdges.add(e);}
    public void addComeEdges(Edges e){
        _comeEdges.add(e);
    }

    @Override
    public String toString(){
        return "Vertex" + " " + _num + " " + _name + " " + _altitude;
    }
    public boolean greaterThan(Vertex v){
        if (_distance > v._distance){
            return true;
        }
        else {
            return false;
        }

    }

}
