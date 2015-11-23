import java.util.List;

/**
 * Created by victor on 13/11/15.
 */
public class Edges {
    private int _num;
    private String _name;
    private RoadType _type;
    private float _time;
    private Vertex _fromVertex;
    private Vertex _toVertex;

    public Edges(int num, String name, RoadType type, Vertex from, Vertex to){
        _num = num;
        _name = name;
        _type = type;
        _fromVertex = from;
        _toVertex = to;
        _fromVertex.addLeaveEdges(this);
        _toVertex.addComeEdges(this);

        computeTime();

    }

    private void computeTime(){
        int diff = _fromVertex.get_altitude() - _toVertex.get_altitude();
        if (diff < 0){
            diff = -diff;
        }
        switch (this._type){
            case TK : _time = 1 + 1f * diff * 4/100; break;
            case TS : _time = 1 + 1f * diff * 4/100; break;
            case TSD : _time = 1 + 1f * diff * 3/100; break;
            case TPH : _time = 4 + 1f * diff * 2/100; break;
            case TC : _time = 2 + 1f * diff * 3/100; break;
            case V : _time = 1f * diff * 5/100; break;
            case B : _time = 1f * diff * 4/100; break;
            case R : _time = 1f * diff * 3/100; break;
            case N : _time = 1f * diff * 2/100; break;
            case KL : _time = 1f * diff * (10/60)/100; break;
            case SURF : _time = 1f * diff * 10/100; break;
            case BUS :{
                if (this._name.contains("2000")){
                    _time = 40;
                }else{
                    _time = 30;
                }
            }
        }
    }
}
