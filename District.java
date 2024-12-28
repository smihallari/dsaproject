public class District {

    private String name;
    private CustomSet<Location> locationsInDistrict;

    public District(String name) {
        this.name = name;
        locationsInDistrict = new CustomSet<>();
    }

    public String getDistrictName(){
        return this.name;
    }

    public void include(Location ... locations) {
        for (Location location : locations) {
            locationsInDistrict.add(location);
        }
    }
    public boolean partOfDistrict(Location l){
        return locationsInDistrict.contains(l);
    }


}