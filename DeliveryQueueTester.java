import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeliveryQueueTester {

    private DeliveryQueue<Package> queue;
    private Package priorityPackage1;
    private Package priorityPackage2;
    private Package regularPackage1;
    private Package regularPackage2;

    @BeforeEach
    void setUp() {
        queue = new DeliveryQueue<>();
        priorityPackage1 = new Package(1, true,200, new Location(1,"Location D"), -1);  
        priorityPackage2 = new Package(3, true,300, new Location(2,"Location A"), -1);  
        regularPackage1 = new Package(2, false,400, new Location(3,"Location B"), -1);  
        regularPackage2 = new Package(4, false,500, new Location(4,"Location C"), -1);  
    }

    @Test
    void testEnqueuePriorityOrder() throws DeliveryQueueEmptyException {
        queue.enqueue(priorityPackage2);
        queue.enqueue(priorityPackage1);

        assertEquals(priorityPackage1, queue.peek(), "Priority package with smaller ID should be at the front.");
    }

    @Test
    void testEnqueueRegularAfterPriority() throws DeliveryQueueEmptyException {
        queue.enqueue(priorityPackage1);
        queue.enqueue(regularPackage1);

        assertEquals(priorityPackage1, queue.dequeue(), "Priority package should be dequeued first.");
        assertEquals(regularPackage1, queue.peek(), "Regular package should come after priority packages.");
    }

    @Test
    void testEnqueueMixedOrder() throws DeliveryQueueEmptyException {
        queue.enqueue(regularPackage2);
        queue.enqueue(priorityPackage2);
        queue.enqueue(regularPackage1);
        queue.enqueue(priorityPackage1);

        assertEquals(priorityPackage1, queue.dequeue(), "Priority package with smallest ID should be dequeued first.");
        assertEquals(priorityPackage2, queue.dequeue(), "Next priority package should be dequeued.");
        assertEquals(regularPackage2, queue.dequeue(), "Regular package with smallest ID should be dequeued next.");
        assertEquals(regularPackage1, queue.dequeue(), "Last regular package should be dequeued.");
    }

    @Test
    void testPeek() throws DeliveryQueueEmptyException {
        queue.enqueue(priorityPackage1);
        assertEquals(priorityPackage1, queue.peek(), "Peek should return the package at the front.");
        assertEquals(1, queue.size(), "Peek should not remove the package from the queue.");
    }

    @Test
    void testIsEmpty() throws DeliveryQueueEmptyException {
        assertTrue(queue.isEmpty(), "Queue should be empty initially.");
        queue.enqueue(priorityPackage1);
        assertFalse(queue.isEmpty(), "Queue should not be empty after adding a package.");
        queue.dequeue();
        assertTrue(queue.isEmpty(), "Queue should be empty after removing all packages.");
    }

    @Test
    void testSize() throws DeliveryQueueEmptyException {
        assertEquals(0, queue.size(), "Size should be 0 initially.");
        queue.enqueue(priorityPackage1);
        queue.enqueue(regularPackage1);
        assertEquals(2, queue.size(), "Size should reflect the number of packages in the queue.");
        queue.dequeue();
        assertEquals(1, queue.size(), "Size should decrease after a package is removed.");
    }
}