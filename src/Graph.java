import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

/**
 * Created by victor on 13/11/15.
 */
public class Graph {
    public String _name;
    public List<Edges> _edges;
    public List<Vertex> _vertex;

    public Graph(){};

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


}
