import java.util.Objects;

public class Vertex {
    
    String label;
    int id;

    public Vertex(String l, int v) {
        setLabel(l);
        setID(v);
    }

    public Vertex(String l) {
        setLabel(l);
        id=-1;
    }

    public Vertex(int v) {
        setID (v);
        label= "null";
    }

    private void setLabel(String l) {
        this.label = l;
    }

    public String getLabel() {
        return this.label;
    }

    private void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public boolean equals(Vertex v) {
        return (Objects.equals(this.label, v.getLabel()) && this.id == v.getID());
    }

    public String toString() {
        return this.label + " " + this.id;
    }

}
