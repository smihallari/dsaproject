import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

//To test the abstract class CustomGraph, we used the class which extends it, RoadMap
//While we also could have left CustomGraph as a non-abstract class, this seemed like a more appropriate solution.





import static org.junit.jupiter.api.Assertions.*;

class CustomGraphTester {
    
    private CustomGraph graph;
    private Location locationA;
    private Location locationB;
    private Location locationC;
    
    @BeforeEach
    void setUp() {
        graph = new RoadMap();
        locationA = new Location(1,"A");
        locationB = new Location(2,"B");
        locationC = new Location(3,"C");
    }

    @Test
    void testSetBaseAndGetBase() {
        graph.setBase(locationA);
        assertEquals(locationA, graph.getBase(), "The base location should match the set location.");
    }

    @Test
    void testAddLocation() {
        graph.addConnection(locationA, locationB);
        assertTrue(graph.getCityLocations().contains(locationA), "Location A should be added to the graph.");
        assertTrue(graph.getCityLocations().contains(locationB), "Location B should be added to the graph.");
    }

    @Test
    void testGetCityLocations() {
        graph.addConnection(locationA, locationB);
        List<Location> locations = graph.getCityLocations();
        assertEquals(2, locations.size(), "The graph should contain two locations.");
    }

    @Test
    void testGetCityRoads() {
        graph.addConnection(locationA, locationB);
        graph.addConnection(locationB, locationC);

        List<Connection> roads = graph.getCityRoads();
        assertEquals(2, roads.size(), "The graph should contain two connections.");
    }

    @Test
    void testAddConnection() {
        graph.addConnection(locationA, locationB);
        assertEquals(1, graph.getCityRoads().size(), "The graph should contain one connection.");

        Connection connection = graph.getCityRoads().get(0);
        assertEquals(locationA, connection.getSource(), "The connection's source should be Location A.");
        assertEquals(locationB, connection.getDestination(), "The connection's destination should be Location B.");
    }

    @Test
    void testGetRandomLocation() {
        graph.addConnection(locationA, locationB);
        graph.addConnection(locationB, locationC);

        Location randomLocation = graph.getRandomLocation();
        assertNotNull(randomLocation, "The random location should not be null.");
        assertTrue(graph.getCityLocations().contains(randomLocation), "The random location should be from the graph's locations.");
    }

    @Test
    void testConstructorWithParameters() {
        List<Location> vertices = new ArrayList<>();
        vertices.add(locationA);
        vertices.add(locationB);

        List<Connection> edges = new ArrayList<>();
        edges.add(new Connection(locationA, locationB));

        RoadMap customGraph = new RoadMap(vertices, edges);

        assertEquals(2, customGraph.getCityLocations().size(), "The graph should have two locations.");
        assertEquals(1, customGraph.getCityRoads().size(), "The graph should have one connection.");
    }

    @Test
    void testNoDuplicateLocations() {
        graph.addConnection(locationA, locationB);
        graph.addConnection(locationA, locationC);

        List<Location> locations = graph.getCityLocations();
        assertEquals(3, locations.size(), "The graph should not contain duplicate locations.");
    }
}