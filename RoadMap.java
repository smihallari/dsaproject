import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class RoadMap extends CustomGraph {
    private List<Location> gasStations;
    public RoadMap() {
        super();
        gasStations = new ArrayList<>();
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

    public void addGasStations(Location ...locations){
        for(Location l : locations){
        gasStations.add(l);
        }
    }
    public List<Location> getGasStations(){
        return gasStations;
    }
}
