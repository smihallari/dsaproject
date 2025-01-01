public class District extends CustomGraph {

    private String name;
    private CustomList<Location> locationsInDistrict;
    private CustomList<Connection> roadsInD;
    public District(String name) {
        this.name = name;
        locationsInDistrict = new CustomList<>();
    }
    public CustomList<Location> getLocationsInD(){
        return  locationsInDistrict;
    }
    public CustomList<Connection> getRoadsInD(){
        return roadsInD;
    }
    public String getDistrictName(){
        return this.name;
    }

    public void includeLocations(Location ... locations) {
        for (Location location : locations) {
            locationsInDistrict.add(location);
        }
    }
    public void includeConnection(Connection ... connections) {
        for (Connection c : connections) {
            roadsInD.add(c);
        }
    }
    public boolean partOfDistrict(Location l){
        return locationsInDistrict.contains(l);
    }


}