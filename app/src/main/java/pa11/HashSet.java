package pa11;
import java.util.*;


public class HashSet {

    /**
     * Constructor for the set
     */
    private int capacity = 17;
    private ArrayList<String>[] array;
    private int size;

    public HashSet() {
        array = new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = new ArrayList<>();
        }

    }

    /**
     * Size of the set
     * @return the number of elements in the set
     */
    public int size() {
        return this.size;
    }

    /** 
     * Check if the set is empty
     * @return a boolean indicating whether the set is empty
     */
    public boolean isEmpty() {
        if (this.size==0) {
            return true;
        } 
        return false;
    }

    /**
     * Add an element to the set
     * @param s the element to add
     * @return the old value associated with the key, or null if no such entry exists
     */
    public void add(String s) {
        int hashcode = hash(s);
        if (!array[hashcode].contains(s)){
            array[hashcode].add(s);
            this.size ++;
        }
        
        //Check for duplicates using contains
        // System.out.println("Adding " + s);
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
     * Remove an element from the set
     * @param s the element to remove
     * @return the value associated with the key, or null if no such entry exists
     */
    public void remove(String s) {
        int hashcode = hash(s);
    
        
        if (array[hashcode] == null) {
            return;
        }
    
        Iterator<String> iterator = array[hashcode].iterator();
        while (iterator.hasNext()) {
            String search = iterator.next();
            if (search.equals(s)) {
                iterator.remove(); // Safe removal using iterator
                this.size--;
                break;
            }
        }
    }
    

    /** 
     * Check if the set contains an element
     * @param s the element to check for
     * @return a boolean indicating whether the set contains the element
     */
    public boolean contains(String s) {
        int hashcode = hash(s);
        for (String search: array[hashcode]){
            if (search.equals(s)){
                return true;
            }
        }

        return false;
    }

    /** 
     * Clears the set
     */
    public void clear() {
        for (int i = 0; i < capacity; i++){
            array[i].clear();
        }
        this.size = 0;
        System.out.print("Cleared");
    }

    /** 
     * Convert the set to an array
     * @return an array containing all the elements in the set
     */
    public String[] toArray() {
        String[] result = new String[this.size];
        int index = 0;
        if (!isEmpty()){
            for (int i = 0; i < capacity; i ++){
                for (String s: array[i]){
                    result[index++] = s;
                }
            }
            
        }
        return result;
    }

    /** 
     * Takes the intersection of the current set and the `other` set
     * @param other the set to intersect with
     * @return a new `HashSet` containing the intersection of the current set and the `other` set
     */
    public HashSet intersection(HashSet other) {
        HashSet result = new HashSet();
        String[] rightArray = other.toArray();
        String[] leftArray = this.toArray();

        HashSet otherSet = new HashSet();
        for (String element : rightArray) {
            otherSet.add(element);
        }

        for (String element : leftArray) {
            if (otherSet.contains(element)) {
                result.add(element);
            }
        }

        return result;
    }

    /** 
     * Convert the set to a string
     * @param other the set to union with
     * @return a new `HashSet` containing the union of the current set and the `other` set
     */
    public HashSet union(HashSet other) {
        HashSet result = new HashSet();
        String[] rightArray = other.toArray();
        String[] leftArray = this.toArray();
        for (String element : rightArray) {
            result.add(element);
        }

        for (String element : leftArray) {
            result.add(element);
        }

        return result;
    }

    /** 
     * Takes the difference of the current set and the `other` set 
     * @param other the set to take the difference with
     * @return a new `HashSet` containing the difference of the current set and the `other` set
     */
    public HashSet difference(HashSet other) {
        HashSet result = new HashSet();
        String[] rightArray = other.toArray();
        String[] leftArray = this.toArray();

        HashSet otherSet = new HashSet();
        for (String element : rightArray) {
            otherSet.add(element);
        }

        for (String element : leftArray) {
            if (!otherSet.contains(element)){
                result.add(element);
            }
        }

        return result;
    }

    /** 
     * Check if the current set is a subset of the `other` set
     * @param other the set to check for a subset
     * @return a boolean indicating whether the current set is a subset of the `other` set
     */
    public boolean subset(HashSet other) {
        String[] rightArray = other.toArray();
        String[] leftArray = this.toArray();

        HashSet otherSet = new HashSet();
        for (String element : rightArray) {
            otherSet.add(element);
        }

        if (otherSet.size()<=this.size()){
            for (String element: leftArray){
                if (!otherSet.contains(element)){
                    return false;
                }
            }
        }
        return true;
        
    }

    public static void main(String[] args) {
        HashSet set1 = new HashSet();
        set1.add("A");
        set1.add("B");
        set1.add("C");
    
        HashSet set2 = new HashSet();
        set2.add("B");
        set2.add("C");
        set2.add("D");
    
        System.out.println("Set 1: " + Arrays.toString(set1.toArray()));
        System.out.println("Set 2: " + Arrays.toString(set2.toArray()));
    
        HashSet union = set1.union(set2);
        System.out.println("Union: " + Arrays.toString(union.toArray()));
    
        HashSet intersection = set1.intersection(set2);
        System.out.println("Intersection: " + Arrays.toString(intersection.toArray()));
    
        HashSet difference = set1.difference(set2);
        System.out.println("Difference (Set1 - Set2): " + Arrays.toString(difference.toArray()));
    
        System.out.println("Set1 is subset of Set2: " + set1.subset(set2));
    }
    
        
}
