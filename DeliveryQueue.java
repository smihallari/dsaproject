public class DeliveryQueue<T extends Package> {
    private Node<T> head;
    private int size;


    public DeliveryQueue() {
        this.head = null;
        this.size = 0;
    }

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

    public T dequeue() {
        if (head == null) {
            throw new DeliveryQueueException("Delivery queue is empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    public T peek() {
        if (head == null) {
            throw new DeliveryQueueException("Delivery queue is empty");
        }
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
}