import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoadMapTester {

    private RoadMap roadMap;
    private Location locationA;
    private Location locationB;

    @BeforeEach
    void setUp() {
        roadMap = new RoadMap();
        locationA = new Location(1, "Location A");
        locationB = new Location(2, "Location B");
    }

    @Test
    void testAddConnection() {
        roadMap.addConnection(locationA, locationB);

        assertEquals(1, roadMap.getCityRoads().size(), "There should be one connection in the roadmap.");
        Connection connection = roadMap.getCityRoads().get(0);

        assertEquals(locationA, connection.getSource(), "The source location should match.");
        assertEquals(locationB, connection.getDestination(), "The destination location should match.");
    }

    @Test
    void testRandomizeTraffic() {
        roadMap.addConnection(locationA, locationB);
        roadMap.randomizeTraffic();

        Connection connection = roadMap.getCityRoads().get(0);
        int weight = connection.getWeight();

        assertTrue(weight >= 0 && weight < 50, "The weight should be between 0 and 49.");
    }

    @Test
    void testMultipleConnectionsRandomizeTraffic() {
        Location locationC = new Location(3, "Location C");
        roadMap.addConnection(locationA, locationB);
        roadMap.addConnection(locationB, locationC);
        roadMap.randomizeTraffic();

        for (Connection connection : roadMap.getCityRoads()) {
            int weight = connection.getWeight();
            assertTrue(weight >= 0 && weight < 50, "Each connection weight should be between 0 and 49.");
        }
    }
}
