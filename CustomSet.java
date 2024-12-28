public class CustomSet<T> {

    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public CustomSet() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public boolean add(T element) {
        if (contains(element)) {
            return false; // Element already exists
        }
        if (size == elements.length) {
            resize(); // Expand capacity if needed
        }
        elements[size++] = element;
        return true;
    }

    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                elements[i] = elements[size - 1]; // Replace with last element
                elements[size - 1] = null; // Nullify the last element
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

}
