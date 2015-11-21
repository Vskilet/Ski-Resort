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
    public void addLeaveEdges(Edges e){
        _leaveEdges.add(e);
    }
    public void addComeEdges(Edges e){
        _comeEdges.add(e);
    }

}
