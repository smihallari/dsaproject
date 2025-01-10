import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;
class TruckTester {
    private Truck truck;
    private RoadMap city;
    private Location baseLocation;
    private Package package1;
    private Package package2;

    @BeforeEach
    void setUp() {
        baseLocation = new Location(0,"Base");
        city = new RoadMap();
        city.setBase(baseLocation);
        truck = new Truck(1, 1000, 5000, city);
        package1 = new Package(1, false,200, baseLocation, -1);
        package2 = new Package(2, true,300, new Location(1,"Location A"), -1);
    }

    @Test
    void testAddPackageWithinWeightLimit() {
        truck.addPackage(package1);
        assertEquals(1, truck.getCargoList().size());
        assertEquals(1200, truck.getTruckWeight(), 0.01);
    }

    @Test
    void testAddPackageExceedingWeightLimit() {
        Package heavyPackage = new Package(3, false,5000, new Location(4,"Location B"), -1);
        Exception exception = assertThrows(TruckOverWeightException.class, () -> truck.addPackage(heavyPackage));
        assertEquals("Package can not be added to the truck, it can not store such a heavy load.", exception.getMessage());
    }

    @Test
    void testDeliverPackages() {
        truck.addPackage(package1);
        truck.deliverPackages();
        assertEquals(0, truck.getCargoList().size());
        assertEquals(1000, truck.getTruckWeight(), 0.01);
    }


    @Test
    void testSetTruckRoute() {
        LinkedList<Location> route = new LinkedList<>();
        route.add(new Location(3,"Location A"));
        route.add(new Location(4,"Location B"));
        truck.setTruckRoute(route);
        assertEquals(2, truck.getTruckRoute().size());
    }

    @Test
    void testStartAndFinishDelivery() {
        assertFalse(truck.isonDelivery());
        truck.startDelivering();
        assertTrue(truck.isonDelivery());
        truck.finishedDelivering();
        assertFalse(truck.isonDelivery());
    }
}
    

