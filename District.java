import java.util.*;

public class District extends CustomGraph {

    private String name;
    private List<Location> locationsInDistrict;
    private List<Connection> roadsInD;
    public District(String name) {
        this.name = name;
        locationsInDistrict = new ArrayList<>();
        roadsInD = new ArrayList<>();
    }
    public List<Location> getLocationsInD(){
        return  locationsInDistrict;
    }
    public List<Connection> getRoadsInD(){
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