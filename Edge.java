class Edge {
    Vertex source;
    Vertex destination;
    int weight;

    public Edge(Vertex s, Vertex d, int w) {
        this.source = s;
        this.destination = d;
        this.weight = w;
    }

    public Edge(Vertex s, Vertex d) {
        this.source = s;
        this.destination = d;
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