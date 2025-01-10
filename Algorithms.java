import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Algorithms {

    public static int getDistance(Location from, Location to, List<Connection> edges) {
        for (Connection edge : edges) {
            if (edge.getSource().equals(from) && edge.getDestination().equals(to)) {

                return edge.weight;
            }
        }return Integer.MAX_VALUE;
         
    }

    // Dijkstra's algorithm to find the shortest path between two vertices (returns distances and paths)
    public static Map<Location, List<Location>> dijkstra1(Location start, List<Location> vertices, List<Connection> edges) {

        int n = vertices.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start.id] = 0;
    
        Map<Location, List<Location>> paths = new HashMap<>();
        for (Location Location : vertices) {
            paths.put(Location, new ArrayList<>());
        }
        paths.get(start).add(start);
    
        PriorityQueue<Location> pq = new PriorityQueue<>(Comparator.comparingInt(v -> dist[v.id]));
    
        pq.add(start);
    
        while (!pq.isEmpty()) {
            Location u = pq.poll();
    
            for (Location v : vertices) {
                if (u.equals(v)) continue;
                int weight = getDistance(u, v, edges);
                if (weight == Integer.MAX_VALUE) continue;
    
                if (u.id < n && v.id < n && dist[u.id] != Integer.MAX_VALUE && dist[u.id] + weight < dist[v.id]) {
    
                    dist[v.id] = dist[u.id] + weight;
                    pq.add(v);
    
                    // Update the path
                    List<Location> path = new ArrayList<>(paths.get(u));
                    path.add(v);
                    paths.put(v, path);
                }
            }
        }
    
        return paths;
    }
    
    // Solve the TSP for the subset of vertices including helper vertices
    public static List<Location> solveTSP(Location start, List<Location> toVisit, List<Connection> edges, List<Location> allVertices) {
        
        long startTime = System.nanoTime();
        List<Location> bestRoute = new ArrayList<>();
        int bestCost = Integer.MAX_VALUE;
    
    
        Map<Location, Map<Location, List<Location>>> shortestPaths = new HashMap<>();
        for (Location v1 : allVertices) {
            shortestPaths.put(v1, new HashMap<>());
            Map<Location, List<Location>> paths = dijkstra1(v1, allVertices, edges);
    
            for (Location v2 : allVertices) {
                if (!v1.equals(v2)) {
                    shortestPaths.get(v1).put(v2, paths.get(v2));
                }
            }
        }
    
        // Generate permutations of vertices to visit
        List<List<Location>> permutations = generatePermutations(toVisit);
    
        // Evaluate each permutation
        for (List<Location> perm : permutations) {
            int cost = 0;
            Location current = start;
            boolean validPermutation = true;
            List<Location> currentRoute = new ArrayList<>();
            currentRoute.add(start); // Start with the starting Location
            for (Location next : perm) {
                List<Location> path = shortestPaths.get(start).get(next);
    
                if (path == null || path.isEmpty()) {
                    validPermutation = false;
                    break;
                }
    
                // Add intermediate vertices from the shortest path
                for (int i = 1; i < path.size(); i++) {
                    currentRoute.add(path.get(i));
                }
    
                // Add distance cost
                cost += getDistance(current, next, edges);
                current = next;
            }
    
            // Add return path to the start Location
            if (validPermutation) {
                List<Location> returnPath = shortestPaths.get(current).get(start);
                if (returnPath == null || returnPath.isEmpty()) {
                    validPermutation = false;
                } else {
                    // Add return path intermediate vertices
                    for (int i = 1; i < returnPath.size(); i++) {
                        currentRoute.add(returnPath.get(i));
                    }
    
                    // Add cost for the return path
                    cost += getDistance(current, start, edges);
                }
            }
    
            // Check if this route is better
            if (validPermutation && cost < bestCost) {
                bestCost = cost;
                bestRoute = new ArrayList<>(currentRoute);
            }
        }
        double elapsedTime = (System.nanoTime() - startTime) / 1_000_000_000.0;
        try (FileWriter writer = new FileWriter("operationData.txt",true)){
            writer.write("________________________________________________\n");
            writer.write("CASE FOR TSP\n");
            writer.write("Elapsed time for TSP: "+elapsedTime+" seconds\n");
            writer.write("Num of locations to visit: "+toVisit.size()+"\n");
            writer.write("________________________________________________\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return bestRoute;
    }
    
    
    
    
    
        // Generate all permutations of a list
        public static List<List<Location>> generatePermutations(List<Location> list) {
            List<List<Location>> result = new ArrayList<>();
            permute(list, 0, result);
            return result;
        }
    
        private static void permute(List<Location> list, int start, List<List<Location>> result) {
            if (start == list.size() - 1) {
                result.add(new ArrayList<>(list));
                return;
            }
            for (int i = start; i < list.size(); i++) {
                Collections.swap(list, i, start);
                permute(list, start + 1, result);
                Collections.swap(list, i, start);
            }
        }

        public static Map<Location, List<Location>> dijkstra(Location start, List<Location> targetVertices, List<Location> vertices, List<Connection> edges) {
            int n = vertices.size();
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE); // Initialize distances with infinity
            dist[start.id] = 0; // Distance to the start Location is zero
    
            Map<Location, List<Location>> paths = new HashMap<>(); // Store paths for target vertices
            for (Location Location : vertices) {
                paths.put(Location, new ArrayList<>());
            }
            paths.get(start).add(start); // Initialize the start Location path
    
            PriorityQueue<Location> pq = new PriorityQueue<>(Comparator.comparingInt(v -> dist[v.id]));
            pq.add(start); // Add the start Location to the priority queue
    
            while (!pq.isEmpty()) {
                Location u = pq.poll(); // Get the Location with the smallest distance
    
                boolean allTargetsReached = targetVertices.stream().allMatch(v -> !paths.get(v).isEmpty());
                if (allTargetsReached) {
                    break;
                }
    
                for (Location v : vertices) {
                    if (u.equals(v)) continue; // Skip self-loops
    
                    int weight = getDistance(u, v, edges); // Get the weight of the edge u -> v
                    if (weight == Integer.MAX_VALUE) continue; // Skip if no edge exists
    
                    // Relax the edge if a shorter path is found
                    if (dist[u.id] != Integer.MAX_VALUE && dist[u.id] + weight < dist[v.id]) {
                        dist[v.id] = dist[u.id] + weight; // Update distance
                        pq.add(v); // Add to the priority queue
    
                        // Update the path to the current Location
                        List<Location> path = new ArrayList<>(paths.get(u));
                        path.add(v);
                        paths.put(v, path);
                    }
                }
            }
    
            // Retain only paths to the target vertices
            paths.keySet().retainAll(targetVertices);
    
            return paths; // Return all paths for the target vertices
        }
    //  public static void main(String[] args) {

    //     Location v0 = new Location(0, "A");
    //     Location v1 = new Location(1, "B");
    //     Location v2 = new Location(2, "C");
    //     Location v3 = new Location(3, "D");
    //     Location v4 = new Location(4, "E");
    //     Location v5 = new Location(5, "F");
    //     Location v6 = new Location(6, "G");
    //     Location v7 = new Location(7, "H");
    //     Location v8 = new Location(8, "I");
    //     Location v9 = new Location(9, "J");
    //     Location v10 = new Location(10, "K");
    //     Location v11 = new Location(11, "L");
    //     Location v12 = new Location(12, "M");
    //     Location v13 = new Location(13, "N");
    //     Location v14 = new Location(14, "O");
    //     Location v15 = new Location(15, "P");
    //     Location v16 = new Location(16, "Q");
    //     Location v17 = new Location(17, "R");
    //     Location v18 = new Location(18, "S");
    //     Location v19 = new Location(19, "T");
    //     Location v20 = new Location(20, "U");
    //     Location v21 = new Location(21, "V");
    //     Location v22 = new Location(22, "W");
    //     Location v23 = new Location(23, "X");
    //     Location v24 = new Location(24, "Y");

    //     // List of all vertices
    //     List<Location> allVertices = Arrays.asList(
    //         v0, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, v23, v24
    //     );

    //     // Define edges (you can add more or adjust the weights as per your needs)
    //     List<Connection> edges = Arrays.asList(
    //         new Connection(v0, v1, 10), new Connection(v0, v2, 15), new Connection(v0, v3, 20), new Connection(v0, v4, 25),
    //         new Connection(v1, v5, 30), new Connection(v1, v6, 35), new Connection(v2, v7, 40), new Connection(v2, v8, 45),
    //         new Connection(v3, v9, 50), new Connection(v3, v10, 55), new Connection(v4, v11, 60), new Connection(v4, v12, 65),
    //         new Connection(v5, v13, 70), new Connection(v5, v14, 75), new Connection(v6, v15, 80), new Connection(v6, v16, 85),
    //         new Connection(v7, v17, 90), new Connection(v7, v18, 95), new Connection(v8, v19, 100), new Connection(v8, v20, 105),
    //         new Connection(v9, v21, 110), new Connection(v9, v22, 115), new Connection(v10, v23, 120), new Connection(v10, v24, 125),
    //         new Connection(v11, v0, 130), new Connection(v12, v1, 135), new Connection(v13, v2, 140), new Connection(v14, v3, 145),
    //         new Connection(v15, v4, 150), new Connection(v16, v5, 155), new Connection(v17, v6, 160), new Connection(v18, v7, 165),
    //         new Connection(v19, v8, 170), new Connection(v20, v9, 175), new Connection(v21, v10, 180), new Connection(v22, v11, 185),
    //         new Connection(v23, v12, 190), new Connection(v24, v13, 195)
    //     );

    //     // Subset of vertices to visit (including v0)
    //     List<Location> toVisit = Arrays.asList( v1,v3, v2, v6,v9,v7,v10, v4, v5);  // Example vertices to visit

    //     // Solve TSP for the specific list of locations
    //     List<Location> route = solveTSP(v0, toVisit, edges, allVertices);

    //     // Output the route
    //     System.out.println("Optimal route visiting specific locations:");
    //     for (int i = 1; i < route.size()-1; i++) {
    //         System.out.print(route.get(i).getLabel() + " -> ");
    //     }
    // }
    public static List<Location> dijkstraOnlyShortest(Location start, List<Location> targetVertices, List<Location> vertices, List<Connection> edges) {
        int n = vertices.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start.id] = 0;

        List<Location> shortestPath = new ArrayList<>();
        int minCost = Integer.MAX_VALUE;

        PriorityQueue<Location> pq = new PriorityQueue<>(Comparator.comparingInt(v -> dist[v.id]));
        pq.add(start);

        Map<Location, Location> previous = new HashMap<>();

        while (!pq.isEmpty()) {
            Location u = pq.poll();
            boolean allTargetsReached = targetVertices.stream().allMatch(v -> dist[v.id] < Integer.MAX_VALUE);
            if (allTargetsReached) {
                break;
            }

            for (Location v : vertices) {
                if (u.equals(v)) continue;

                int weight = getDistance(u, v, edges);
                if (weight == Integer.MAX_VALUE) continue;

                if (dist[u.id] != Integer.MAX_VALUE && dist[u.id] + weight < dist[v.id]) {
                    dist[v.id] = dist[u.id] + weight;
                    pq.remove(v);  // Ensure the old entry is removed
                    pq.add(v);     // Add the vertex with updated priority
                    previous.put(v, u);

                    if (targetVertices.contains(v) && dist[v.id] < minCost) {
                        minCost = dist[v.id];
                        shortestPath.clear();
                        Location current = v;
                        while (current != null) {
                            shortestPath.add(0, current);
                            current = previous.get(current);
                        }
                    }
                }
            }
        }

        return shortestPath;
    }
}
