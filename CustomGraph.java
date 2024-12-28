public class CustomGraph {

    private CustomHashmap<Vertex, CustomList<Edge>> adjacencyList;

    public CustomGraph() {
        this.adjacencyList = new CustomHashmap<>();
    }

    public void addVertex(Vertex v) {
        if(adjacencyList.containsKey(v)) return;
        adjacencyList.put(v, new CustomList<>());
    }

    public void addEdge(Vertex source, Vertex destination,int weight) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(new Edge(source, destination,weight));
    }
}