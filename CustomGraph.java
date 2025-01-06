import java.util.*;

public class CustomGraph {

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
    public Location getRandomLocation(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(graphVertices.size()-1)+1; 
        return graphVertices.get(randomIndex);
    }
    public List<Connection> getCityEdges(){
        return graphEdges;
    }

    public void addConnection(Location source, Location destination,int weight) {
        addLocation(source);
        addLocation(destination);
        graphEdges.add(new Connection(source, destination,weight));
    }
}