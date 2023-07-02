package Dictionary;
import java.util.ArrayList;
import java.util.Hashtable;
import List.ListInterface;
import List.MyLinkedList;

public class MyHashtable implements DictionaryInterface {

    protected int tableSize;
    protected int size;
    // The LinkedList is of type Entry
    protected MyLinkedList[] table;

    public MyHashtable(int tableSize) {
        table = new MyLinkedList[tableSize];
        this.tableSize = tableSize;
    }

    public int biggestBucket() {
        int biggestBucket = 0;
        for (int i = 0; i < table.length; i++) {
            // Loop through the table looking for non-null locations.
            if (table[i] != null) {
                // If you find a non-null location, compare the bucket size against the largest
                // bucket size found so far. If the current bucket size is bigger, set biggestBucket
                // to this new size.
                MyLinkedList bucket = table[i];
                if (biggestBucket < bucket.size())
                    biggestBucket = bucket.size();
            }
        }
        return biggestBucket; // Return the size of the biggest bucket found.
    }

    // Returns the average bucket length. Gives a sense of how many collisions are happening overall.
    public float averageBucket() {
        float bucketCount = 0; // Number of buckets (non-null table locations)
        float bucketSizeSum = 0; // Sum of the size of all buckets
        for (int i = 0; i < table.length; i++) {
            // Loop through the table
            if (table[i] != null) {
                // For a non-null location, increment the bucketCount and add to the bucketSizeSum
                MyLinkedList bucket = table[i];
                bucketSizeSum += bucket.size();
                bucketCount++;
            }
        }

        // Divide bucketSizeSum by the number of buckets to get an average bucket length.
        return bucketSizeSum / bucketCount;
    }

    public String toString() {
        String s = "";
        for (int tableIndex = 0; tableIndex < tableSize; tableIndex++) {
            if (table[tableIndex] != null) {
                MyLinkedList bucket = table[tableIndex];
                for (int listIndex = 0; listIndex < bucket.size(); listIndex++) {
                    Entry e = (Entry) bucket.get(listIndex);
                    s = s + "key: " + e.key + ", value: " + e.value + "\n";
                }
            }
        }
        return s;
    }

    protected class Entry {
        String key;
        Object value;

        Entry(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    // Implement these methods
    public boolean isEmpty() {
        return size == 0;
    } // returns true if the Dictionary is empty, false otherwise.

    public int size() {
        return size;
    } //Returns the number of key/value pairs stored in the dictionary.

    // Adds a value stored under the given key. If the key has already been stored in the Dictionary,
    // replaces the value associated with the key and returns the old value. If the key isn't in the dictionary
    // returns null.
    public Object put(String key, Object value) {
        // 1. Compute an array index given the key
        int hash = key.hashCode() % tableSize;
        if (table[hash] == null) {
            table[hash] = new MyLinkedList();
            table[hash].add(new Entry(key, value));
            size++;
            return null;
        } else {
            for (int i = 0; i < table[hash].size(); i++) {
                Entry entry = (Entry) table[hash].get(i);
                if (entry.key.equals(key)) {
                    Object oldValue = entry.value;
                    entry.value = value;
                    return oldValue;
                }
            }
            table[hash].add(new Entry(key, value));
            size++;
            return null;
        }
    }


    public Object get(String key) {
        int hash = key.hashCode() % tableSize;
        if (table[hash] == null) {
            return null;
        } else {
            for (int i = 0; i < table[hash].size(); i++) {
                Entry entry = (Entry) table[hash].get(i);
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
            return null;
        }
    }

    public void remove(String key) {
        int hash = key.hashCode() % tableSize; // Calculate the hash code for the key to determine the bucket location
        if (table[hash] != null) { // Check if the bucket is not null
            MyLinkedList bucket = table[hash]; // Assign the bucket to a local variable
            for (int i = 0; i < bucket.size(); i++) { // Iterate through the entries in the bucket
                Entry entry = (Entry) bucket.get(i); // Retrieve the entry at index i
                if (entry.key.equals(key)) { // Check if the entry key matches the given key
                    bucket.remove(i); // Remove the entry from the bucket
                    size--; // Decrement the size counter
                    return; // Exit the method
                }
            }
        }
    }


    public void clear() {
        for (int i = 0; i < tableSize; i++) { // Iterate through the hashtable array
            if (table[i] != null) { // Check if the bucket is not null
                ((DictionaryInterface) table[i]).clear(); // Clear the bucket by calling the clear() method on it
            }
        }
        size = 0; // Set the size counter to 0 to indicate an empty dictionary
    }// Empties the dictionary.

    public String[] getKeys() {
        ArrayList<String> keysList = new ArrayList<>(); // Create an ArrayList to store the keys temporarily

        for (int i = 0; i < tableSize; i++) { // Iterate through the hashtable array
            if (table[i] != null) { // Check if the bucket is not null
                MyLinkedList bucket = table[i]; // Assign the bucket to a local variable
                for (int j = 0; j < bucket.size(); j++) { // Iterate through the entries in the bucket
                    Entry entry = (Entry) bucket.get(j); // Retrieve the entry at index j
                    keysList.add(entry.key); // Add the key to the keysList
                }
            }
        }

        String[] keysArray = new String[keysList.size()]; // Create a String array with a size equal to the number of keys
        keysList.toArray(keysArray); // Convert the keysList to an array and store it in keysArray
        return keysArray; // Return the array of keys
    }

}
        
