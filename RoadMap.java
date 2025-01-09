import java.util.Random;
public class RoadMap extends CustomGraph {

    public RoadMap() {
        super();

    }

    public void addConnection(Location from, Location to) {
        super.addConnection(from, to);
    }
    // method to have random weights for connections, to emulate real world conditions.
    public void randomizeTraffic(){
        Random rand= new Random();
        for(Connection c: graphEdges){
            int weight= rand.nextInt(50) ;
            c.setWeight(weight);
        }
    }
}
