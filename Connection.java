public class Connection extends Edge {

    public Connection(Location from, Location to) {
        super(from, to);
    }

    public void updateTraffic(int traffic) {
        setWeight(traffic);
    }

    public int getTraffic() {
        return (int) getWeight();
    }

    public Location startsFrom(){
        return (Location) this.source;
    }

    public Location goesTo(){
        return (Location) this.destination;
    }

}
