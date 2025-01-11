import java.util.*;

public abstract class CustomGraph {
// Customgraph can be an abstract class, but to test its 
    public  List<Location> graphVertices;
    public  List<Connection> graphEdges;
    public Location base;
    public CustomGraph() {
        graphVertices = new ArrayList<>();
        graphEdges = new ArrayList<>();
    }
    public CustomGraph(List<Location> vertices, List<Connection> edges) {
        graphVertices = vertices;
        graphEdges = edges;
    }
    public void setBase(Location l){
        base = l;
    }
    public Location getBase(){
        return base;
    }
    private void addLocation(Location l) {
        if(graphVertices.contains(l)) return;
        graphVertices.add(l);
    }   
    public List<Location> getCityLocations(){
        return graphVertices;
    }
    // get a random location from the locations in the city, used to emulate real world conditions 
    public Location getRandomLocation(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(graphVertices.size()-1)+1;

        return graphVertices.get(randomIndex);
    }
    public List<Connection> getCityRoads(){
        return graphEdges;
    }
    // method to add a connection to the graph
    public void addConnection(Location source, Location destination) {
        addLocation(source);
        addLocation(destination);
        graphEdges.add(new Connection(source, destination));
    }
}