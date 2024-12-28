public class Street {

    private String streetName;
    private static CustomSet<Connection> streetParts;

    public Street(String name) {
        this.streetName = name;
    }
    public String getName(){
        return this.streetName;
    }

    public void include(Connection ... connections) {
        for (Connection c : connections) {
            streetParts.add(c);
        }
    }



}