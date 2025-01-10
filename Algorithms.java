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
            currentRoute.add(start); 
            for (Location next : perm) {
                List<Location> path = shortestPaths.get(start).get(next);
    
                if (path == null || path.isEmpty()) {
                    validPermutation = false;
                    break;
                }
    
                for (int i = 1; i < path.size(); i++) {
                    currentRoute.add(path.get(i));
                }
    
                cost += getDistance(current, next, edges);
                current = next;
            }
    
            if (validPermutation) {
                List<Location> returnPath = shortestPaths.get(current).get(start);
                if (returnPath == null || returnPath.isEmpty()) {
                    validPermutation = false;
                } else {
                    for (int i = 1; i < returnPath.size(); i++) {
                        currentRoute.add(returnPath.get(i));
                    }
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
    
                boolean allTargetsReached = targetVertices.stream().allMatch(v -> !paths.get(v).isEmpty());
                if (allTargetsReached) {
                    break;
                }
    
                for (Location v : vertices) {
                    if (u.equals(v)) continue; 
    
                    int weight = getDistance(u, v, edges); 
                    if (weight == Integer.MAX_VALUE) continue; 
    
                    if (dist[u.id] != Integer.MAX_VALUE && dist[u.id] + weight < dist[v.id]) {
                        dist[v.id] = dist[u.id] + weight; 
                        pq.add(v); 
                        List<Location> path = new ArrayList<>(paths.get(u));
                        path.add(v);
                        paths.put(v, path);
                    }
                }
            }
    
            paths.keySet().retainAll(targetVertices);
    
            return paths; 
        }
}
