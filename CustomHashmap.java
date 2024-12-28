public class CustomHashmap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Entry<K, V>[] table;
    private int capacity = 16;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public CustomHashmap() {
        table = new Entry[capacity];
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public CustomSet<Entry<K, V>> entrySet() {
        CustomSet<Entry<K, V>> entrySet = new CustomSet<>();
        for (Entry<K, V> bucket : table) {
            Entry<K, V> current = bucket;
            while (current != null) {
                entrySet.add(current);
                current = current.next;
            }
        }
        return entrySet;
    }

    private int getBucketIndex(K key) {
        int hash = key.hashCode();
        return Math.abs(hash % capacity);
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> newEntry = new Entry<>(key, value);

        if (table[bucketIndex] == null) {
            table[bucketIndex] = newEntry;
        } else {
            Entry<K, V> current = table[bucketIndex];
            Entry<K, V> previous = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // Update existing key
                    return;
                }
                previous = current;
                current = current.next;
            }

            previous.next = newEntry;
        }
        size++;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> current = table[bucketIndex];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null; // Key not found
    }

    public boolean remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> current = table[bucketIndex];
        Entry<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    table[bucketIndex] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

}
