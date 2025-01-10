public class Connection extends Edge {

    public Connection(Location from, Location to) {
        super(from, to);
    }

    public Connection(Location from, Location to,int weight) {
        super(from, to);
        setWeight(weight);
    }

    public void updateTraffic(int traffic) {
        setWeight(traffic);
    }

    
    public int getTraffic() {
        return (int) getWeight();
    }

    // get the source of the connection
    public Location startsFrom(){
        return (Location) this.source;
    }
    // get the destination of the connection
    public Location goesTo(){
        return (Location) this.destination;
    }

}
