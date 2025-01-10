import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GarageTester {

    private Garage garage;

    @BeforeEach
    void setUp() {
        garage = new Garage();
    }

    @Test
    void testAddTruck() {
        int truckID = 1;
        int truckWeight = 3000;
        int maxCarryWeight = 10000;
        RoadMap city = new RoadMap();

        garage.addTruck(truckID, truckWeight, maxCarryWeight, city);

        Truck truck = garage.getTruck(truckID);
        assertNotNull(truck, "Truck should be added to the garage.");
        assertEquals(truckID, truck.getTruckID(), "Truck ID should match the added truck ID.");
        assertEquals(truckWeight, truck.getTruckWeight(), "Truck weight should match the added value.");
        assertEquals(maxCarryWeight, truck.getMaxWeight(), "Max carry weight should match the added value.");
    }

    @Test
    void testGetTruck() {
        int truckID = 2;
        int truckWeight = 3500;
        int maxCarryWeight = 12000;
        RoadMap city = new RoadMap();

        garage.addTruck(truckID, truckWeight, maxCarryWeight, city);

        Truck truck = garage.getTruck(truckID);
        assertNotNull(truck, "Truck should be retrieved from the garage.");
        assertEquals(truckID, truck.getTruckID(), "Retrieved truck ID should match.");

        Truck nonExistentTruck = garage.getTruck(999);
        assertNull(nonExistentTruck, "Non-existent truck should return null.");
    }

    @Test
    void testRemoveTruck() {
        int truckID = 3;
        int truckWeight = 4000;
        int maxCarryWeight = 15000;
        RoadMap city = new RoadMap();

        garage.addTruck(truckID, truckWeight, maxCarryWeight, city);

        assertNotNull(garage.getTruck(truckID), "Truck should exist in the garage before removal.");

        garage.removeTruck(truckID);

        assertNull(garage.getTruck(truckID), "Truck should no longer exist in the garage after removal.");
    }

    @Test
    void testDisplayAvailableGarage() {
        int truckID1 = 4;
        int truckWeight1 = 3000;
        int maxCarryWeight1 = 10000;
        RoadMap city = new RoadMap();

        int truckID2 = 5;
        int truckWeight2 = 3200;
        int maxCarryWeight2 = 11000;

        garage.addTruck(truckID1, truckWeight1, maxCarryWeight1, city);
        garage.addTruck(truckID2, truckWeight2, maxCarryWeight2, city);

        Truck truck = garage.getTruck(truckID1);
        truck.startDelivering();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        garage.displayAvailableGarage();

        String output = outContent.toString();
        assertTrue(output.contains("Truck with ID5 is available."), "Output should indicate truck ID 5 is available.");
        assertTrue(output.contains("Truck with ID4 is on a delivery."), "Output should indicate truck ID 4 is on a delivery.");

        System.setOut(System.out);
    }
}