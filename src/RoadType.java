/**
 * Created by victor on 13/11/15.
 */
public enum RoadType {
    TK, TS, TSD, TPH, TC, BUS, V, B, R, N, KL, SURF;

    public static RoadType parse(String type) {
        if (type.equals("TK")) {
            return TK;
        } else if (type.equals("TS")) {
            return TS;
        } else if (type.equals("TSD")) {
            return TSD;
        } else if (type.equals("TPH")) {
            return TPH;
        } else if (type.equals("TC")) {
            return TC;
        } else if (type.equals("BUS")) {
            return BUS;
        } else if (type.equals("V")) {
            return V;
        } else if (type.equals("B")) {
            return B;
        } else if (type.equals("R")) {
            return R;
        } else if (type.equals("N")) {
            return N;
        } else if (type.equals("KL")) {
            return KL;
        } else if (type.equals("SURF")) {
            return SURF;
        } else {
            System.err.println("Error while converting string to RoadType : invalid input");
            return null;
        }
    }
}