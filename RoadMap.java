public class RoadMap extends CustomGraph {
    public RoadMap() {
        super();
    }

    // public void addLocation(Location location) {
    //     super.add(location);
    // }

    public void addConnection(Location from, Location to,int weight) {
        super.addConnection(from, to,weight);
    }
}
