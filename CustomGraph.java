public class CustomGraph {

    public static CustomSet<Location> graphVertices;
    public static CustomSet<Connection> graphEdges;
    public CustomGraph() {
        graphVertices = new CustomSet<>();
        graphEdges = new CustomSet<>();
    }
    public CustomGraph(CustomSet<Location> graphVertices, CustomSet<Connection> graphEdges) {
        CustomGraph.graphVertices = graphVertices;
        CustomGraph.graphEdges = graphEdges;
    }
    private void addLocation(Location l) {
        if(graphVertices.contains(l)) return;
        graphVertices.add(l);
    }   
    public CustomSet<Location> getCityLocations(){
        return graphVertices;
    }
    public CustomSet<Connection> getCityEdges(){
        return graphEdges;
    }

    public void addConnection(Location source, Location destination,int weight) {
        addLocation(source);
        addLocation(destination);
        graphEdges.add(new Connection(source, destination,weight));
    }
}