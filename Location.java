import java.util.Objects;

public class Location extends Vertex{
        public Location(String address, int id) {
        super(address, id);
    }

    public Location( int id,String address) {
        super(address, id);
    }

    public String getAddress() {
        return this.getLabel();
    }

    public int getNumber() {
        return this.getID();
    }
    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Location location = (Location) obj;
    return id == location.id;  // Compare based on unique id
}

@Override
public int hashCode() {
    return Objects.hash(id);  // Generate hash code based on id
}



}
