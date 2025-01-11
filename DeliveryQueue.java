public class DeliveryQueue<T extends Package> {
    private Node<T> head;
    private int size;


    public DeliveryQueue() {
        this.head = null;
        this.size = 0;
    }
    //Adds a package object to the queue in order of ascending ID value, with non-priority placed after priority 
    public void enqueue(T pkg) {
        Node<T> newNode = new Node<>(pkg);      
        if (head == null || pkg.isPriority() || (!head.data.isPriority() && pkg.getPackageID() < head.data.getPackageID())) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null && (!current.next.data.isPriority() || (current.next.data.isPriority() == pkg.isPriority() && current.next.data.getPackageID() <= pkg.getPackageID()))) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }
    //Removes and returns the package at the front of the queue.
    public T dequeue() throws DeliveryQueueEmptyException{
        if (head == null) {
            throw new DeliveryQueueEmptyException("Delivery queue is empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }
    //Return the package at the front of the queue without removing it
    public T peek() throws DeliveryQueueEmptyException{
        if (head == null) {
            throw new DeliveryQueueEmptyException("Delivery queue is empty");
        }
        return head.data;
    }
    //Checks if the queue is empty
    public boolean isEmpty() {
        return head == null;
    }
    //Return the size of the queue(the number of packages in the queue)
    public int size() {
        return size;
    }
}