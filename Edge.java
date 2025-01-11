abstract class Edge {
    Vertex source;
    Vertex destination;
    public int weight;

    public Edge(Vertex s, Vertex d) {
        this.source = s;
        this.destination = d;
        weight = 0;
    }


    public void setWeight(int w) {
        this.weight = w;
    }

    public int getWeight() {
        return this.weight;
    }

    public Vertex getSource() {
        return this.source;
    }

    public Vertex getDestination() {
        return this.destination;
    }

}