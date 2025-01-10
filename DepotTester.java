import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

class DepotTester {

    private Depot depot;
    private RoadMap city;
    private District district;
    private DeliveryQueue<Package> packageQueue;

    @BeforeEach
    void setUp() {
        city = new RoadMap(); 
        packageQueue = new DeliveryQueue<>();
        city.setBase(new Location(0, "Depot"));
        district = new District("DistrictA"); 
        Location locationA = new Location(1, "Location A");
        Location locationB = new Location(2, "Location B");
        Location locationC = new Location(3, "Location C");
        city.addConnection(locationA, locationB);
        city.addConnection(locationB, locationC);
        district.includeLocations(locationA,locationB);
        depot = new Depot(city);
    }

    @Test
    void testSetCityBase() {
        Location newBase = new Location(4, "New Depot Base");
        depot.setCityBase(newBase);

        assertEquals(newBase, city.getBase(), "The base location of the city should be updated correctly.");
    }

    @Test
    void testFillPackages() {
        int packageCountToAdd = 10;

        depot.fillPackages(packageCountToAdd);

        assertEquals( packageCountToAdd, depot.getPackageQueue().size(),
                "The package queue should contain the correct number of packages after filling.");
    }

    @Test
    void testAddTruckToGarage() {
        int truckId = 1001;

        depot.addTruckToGarage(truckId);

        Truck addedTruck = depot.getGarage().getTruck(truckId);
        assertNotNull(addedTruck, "Truck should be added to the garage.");
        assertEquals(truckId, addedTruck.getTruckID(), "The truck ID should match the one added.");
    }

    @Test
    void testFillTruckWithPackages() {
        int truckId = 1001;
        depot.addTruckToGarage(truckId);

        depot.fillPackages(100);
        Truck truck = depot.getGarage().getTruck(truckId);
        depot.fillTruckWithPackages(truckId, district);

        assertTrue(!truck.getCargoList().isEmpty(), "Truck should have packages loaded.");
    }

}