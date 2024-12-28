public class Location extends Vertex{
        public Location(String address, int id) {
        super(address, id);
    }

    public String getAddress() {
        return this.getLabel();
    }

    public int getNumber() {
        return this.getID();
    }

}
