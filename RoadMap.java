import java.util.List;
import java.util.Random;
public class RoadMap extends CustomGraph {

    public RoadMap() {
        super();

    }
    public RoadMap(List<Location> vertices, List<Connection> edges) {
        super(vertices,edges);
    }

    public void addConnection(Location from, Location to) {
        super.addConnection(from, to);
    }
    // method to have random weights for connections, to emulate real world conditions.
    public void randomizeTraffic(){
        Random rand= new Random();
        for(Connection c: graphEdges){
            int weight= rand.nextInt(100) ;
            c.setWeight(weight);
        }
    }
}
