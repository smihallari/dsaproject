class CustomList<T> {
    private Object[] elements; // Array to store elements
    private int size = 0;      // Current size of the list
    private int capacity = 10; // Initial capacity of the list

    public CustomList() {
        elements = new Object[capacity];
    }
    public CustomList(int spec) {
        capacity=spec;
        elements = new Object[spec];
    }

    // Add an element to the list
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        validateIndex(index);
        return (T) elements[index];
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        validateIndex(index);
        T removedElement = (T) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[--size] = null; // Nullify the last element
        return removedElement;
    }

    @SuppressWarnings("unchecked")
    public T remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) { 
                T removedElement = (T) elements[i];
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[size - 1] = null; 
                size--;
                return removedElement;
            }
        }
        return null; 
    }
  

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            Object[] newElements = new Object[capacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

}

