public class RoadMap extends CustomGraph {
    public RoadMap() {
        super();
    }

    public void addLocation(Location location) {
        super.addVertex(location);
    }

    public void addConnection(Location from, Location to,int weight) {
        super.addEdge(from, to,weight);
    }
}
