import java.util.ArrayList;

/**
 * Created by victor on 24/11/15.
 */
public class Resort extends ArrayList<RoadType>{

    public Resort(){
        this.add(RoadType.TS);
        this.add(RoadType.TC);
        this.add(RoadType.TK);
        this.add(RoadType.TPH);
        this.add(RoadType.TSD);
    }
    public boolean isValid(Edges e){
        if (this.contains(e.get_type())){
            return true;
        } else if (e.get_type() == RoadType.TK) {
            return true;
        } else if (e.get_type() == RoadType.TS) {
            return true;
        } else if (e.get_type() == RoadType.TSD) {
            return true;
        } else if (e.get_type() == RoadType.TPH) {
            return true;
        } else if (e.get_type() == RoadType.TC) {
            return true;
        } else if (e.get_type() == RoadType.BUS) {
            return true;
        } else {
            return false;
        }
    }
}
