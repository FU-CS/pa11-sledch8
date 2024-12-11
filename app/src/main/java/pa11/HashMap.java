package pa11;

import java.util.ArrayList;

import org.checkerframework.checker.units.qual.K;

public class HashMap {

    /**
     *  Constructor for the map
     */

    private int capacity = 16;
    private int size = 0;
    private ArrayList<String>[] array;

    public HashMap() {
        array = new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = new ArrayList<>();
        }
    }
    
    /** 
     *  Size of the map
     *  @return the number of elements in the map
     */
    public int size() {
        return this.size;
    }

    /**
     *  Check if the map is empty
     *  @return a boolean indicating whether the map is empty
     */
    public boolean isEmpty() {
        if (this.size==0){
            return true;
        }
        return false;
    }

    /**
     *  Get the value associated with the key
     *  @param key the key to get the value for
     *  @return the value associated with the key, or null if no such entry exists
     */
    public String get(String key) {
        int hashcode = hash(key);
        for (String element: array[hashcode]){
            //turn each element into a list of strengths with a length of two, split by the colon
            String[] pair = element.split(":", 2);
            if (pair[0].equals(key)) { // If the key matches
                return pair[1];
            }            
        }
        return null;
    }

    private int hash(String s){
        int sum = 0;
        for (int i = 0; i < s.length(); i++){
           char c = s.charAt(i);
           int ascii = (int) c;
           sum = sum + ascii;
        }
        sum = sum % capacity;
        return sum;
    }

    /**
     *  Add an entry to the map
     *  @param key the key to add
     *  @param value the value to add
     *  @return the old value associated with the key, or null if no such entry exists
     */
    public String put(String key, String value) {
        int hashcode = hash(key);
        ArrayList<String> bucket = array[hashcode]; // Get the bucket
        for (int i = 0; i < bucket.size(); i++){
            String element = bucket.get(i);
            String[] pair = element.split(":", 2);
            if (pair[0].equals(key)) { // Key found
                String oldValue = pair[1];
                bucket.set(i, key + ":" + value); // Update the value
                return oldValue; // Return the old value
            }

        }

        bucket.add(key + ":" + value);
        size++;

        System.out.println("Put " + key + " " + value);
        return null;
    }

    /**
     *  Remove an entry from the map
     *  @param key the key to remove
     *  @return the value associated with the key, or null if no such entry exists
     */
    public String remove(String key) {

        int hashcode = hash(key);
        ArrayList<String> bucket = array[hashcode];

        for (String element: array[hashcode]){
            //turn each element into a list of strengths with a length of two, split by the colon
            String[] pair = element.split(":", 2);
            if (pair[0].equals(key)) { // If the key matches
                String removed = pair[1];
                bucket.remove(element);
                this.size --;

                return removed;
            }            
        }
        System.out.println("Remove " + key);
        return null;
    }

    /**
     *  Get all the keys in the map
     *  @return all the keys stored in the map
     */
    public String[] keySet() {

        ArrayList<String> keys = new ArrayList<>();
        for (ArrayList<String> bucket: array){
            for (String element: bucket){
                String[] pair = element.split(":", 2);
                keys.add(pair[0]);
            }
        }
        return keys.toArray(new String[0]);
    }

    /**
     *  Get all the values in the map
     *  @return all the values stored in the map
     */
    public String[] values() {
        ArrayList<String> values = new ArrayList<>();
        for (ArrayList<String> bucket: array){
            for (String element: bucket){
                String[] pair = element.split(":", 2);
                values.add(pair[1]);
            }
        }
        return values.toArray(new String[0]);
    }
}
