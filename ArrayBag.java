/* 
 * ArrayBag.java
 * 
 * Computer Science E-22
 *
 * Modified by: <your name>, <your e-mail address>
 */

import java.util.*;

/**
 * An implementation of the Bag ADT using an array.
 */
public class ArrayBag implements Bag {
    /** 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /** 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /**
     * Constructor with no parameters - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        this.items = new Object[DEFAULT_MAX_SIZE];
        this.numItems = 0;
    }
    
    /** 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0");
        }
        this.items = new Object[maxSize];
        this.numItems = 0;
    }
    
    /**
     * numItems - accessor method that returns the number of items 
     * in this ArrayBag.
     */
    public int numItems() {
        return this.numItems;
    }
    
    /** 
     * add - adds the specified item to this ArrayBag. Returns true if there 
     * is room to add it, and false otherwise.
     * Throws an IllegalArgumentException if the item is null.
     */
    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        } else if (this.numItems == this.items.length) {
            return false;    // no more room!
        } else {
            this.items[this.numItems] = item;
            this.numItems++;
            return true;
        }
    }
    
    /** 
     * remove - removes one occurrence of the specified item (if any)
     * from this ArrayBag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in this ArrayBag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                // Shift the remaining items left by one.
                for (int j = i; j < this.numItems - 1; j++) {
                    this.items[j] = this.items[j + 1];
                }
                this.items[this.numItems - 1] = null;
                
                this.numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /**
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * containsAll - does this ArrayBag contain all of the items in
     * otherBag?  Returns false if otherBag is null or empty. 
     */
    public boolean containsAll(Bag otherBag) {
        if (otherBag == null || otherBag.numItems() == 0) {
            return false;
        }
        
        Object[] otherItems = otherBag.toArray();
        for (int i = 0; i < otherItems.length; i++) {
            if (! this.contains(otherItems[i])) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * grab - returns a reference to a randomly chosen item in this ArrayBag.
     */
    public Object grab() {
        if (this.numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * this.numItems);
        return this.items[whichOne];
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[this.numItems];
        
        for (int i = 0; i < this.numItems; i++) {
            copy[i] = this.items[i];
        }
        
        return copy;
    }
    
    /**
     * toString - converts this ArrayBag into a string that can be printed.
     * Overrides the version of this method inherited from the Object class.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < this.numItems; i++) {
            str = str + this.items[i];
            if (i != this.numItems - 1) {
                str += ", ";
            }
        }
        
        str = str + "}";
        return str;
    }

    /*PROBLEM SET CODE */

    public int capacity() {
        /*We return the field corresponding to the size of the array of objects */
        return this.items.length;
    }

    public boolean isFull() {
        /*The arraybag is full if the number of items is equal to the capacity */
        if (this.numItems == this.capacity()){
            return true;
        }
        else return false;
    }

    public void increaseCapacity (int amount) {
        if (amount == 0) {
            /*If the parameter is zero, the method should just return without making any changes to the called object */
            return;
        }
        else if (amount < 0) {
            /*If the parameter is negative, the method should throw an IllegalArgumentException.*/
            throw new IllegalArgumentException("The parameter should be positive");
        }
        else {
            /*Creating a new array of object with the increased capacity */
            Object[] newarray = new Object[amount + this.items.length];

            /*Filling the new array with all items from the original array */
            for (int i = 0; i < this.items.length - 1; i++) {
                newarray[i] = this.items[i];
            }

            /*Filling the remaining of the new array with null pointers*/
            for (int j = this.items.length; j < amount + this.items.length - 1; j++) {
                newarray[j] = null;
            }
            this.items = newarray;
            return;
        }

    }


    public boolean removeItems (Bag other) {
        if ( other == null) {
            /*If the parameter is null, the method should throw an IllegalArgumentException. */
            throw new IllegalArgumentException("The parameter should not be a null reference");
        }
        else if ( other.numItems() == 0 || this.numItems == 0){
            /*If the parameter is an empty Bag, or if the current bag is empty, the method should return false. */
            return false;
        }
        else {
            Object[] otherItems = other.toArray(); //We get a clear picture of what is inside the array

            /*Outer loop: Iterates through each element of the other bag */
            for (int i = 0; i < otherItems.length; i++) {
                /*We check for all occurences of the current element of the other bag and remove it if is found*/    
                for (int j = 0; j < this.items.length - 1; j++) {
                    if (this.items[j].equals(otherItems[i])) {
                        this.remove(this.items[j]);
                        this.numItems--;
                    }
                }
            }
            return true;
        }
    }


    public Bag unionWith(Bag other) {

        if (other == null || this.items == null) {
            throw new IllegalArgumentException("One of the reference is null");
        }
        else if (other.numItems() == 0 && this.numItems == 0) {
            /*If both bags are empty, return the empty array */
            Bag exportbag = new ArrayBag(0); 
            return exportbag;
        }
        else if (other.numItems() == 0) {
            /*If the other bag is empty, we copy one occurence of each element in our current bag */

            Object[] otherItems = other.toArray(); //We get a clear picture of what is inside the other bag's array

            /*Creating the new bag witht the capacity equal to that of the sum of the two capacities*/
            Bag exportbag = new ArrayBag(this.numItems + otherItems.length); 

            for (int i = 0; i < this.numItems; i++) {
                /*add any element from from our current bag, if it wasn't already there*/
                if( !exportbag.contains(this.items[i]) ) exportbag.add(this.items[i]); 
            }
            return exportbag;
        }
        else if (this.numItems == 0) {

            Object[] otherItems = other.toArray(); //We get a clear picture of what is inside the array

            /*Creating the new bag witht the capacity equal to that of the sum of the two capacities*/
            Bag exportbag = new ArrayBag(this.numItems + otherItems.length);

            /*add any element from from the other bag, if it wasn't already there*/
            for (int i = 0; i < otherItems.length; i++) {
                if( !exportbag.contains(otherItems[i]) ) exportbag.add(otherItems[i]);
            }

            return exportbag;
        }
        else {
            Object[] otherItems = other.toArray(); //We get a clear picture of what is inside the array

            /*Creating the new bag witht the capacity equal to that of the sum of the two capacities*/
            Bag exportbag = new ArrayBag(this.numItems + otherItems.length);

            for (int i = 0; i < this.numItems; i++) {
                /*add any element from from our current bag, if it wasn't already there*/
                if( !exportbag.contains(this.items[i]) ) exportbag.add(this.items[i]); 
            }
                /*add any element from from the other bag, if it wasn't already there*/
            for (int i = 0; i < otherItems.length; i++) {
                if( !exportbag.contains(otherItems[i]) ) exportbag.add(otherItems[i]);
            }

            return exportbag;
        }
    }
    


    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {

                // //PROBLEM SET TEST CODE
                // Bag b1 = new ArrayBag(5);
                // Bag b2 = new ArrayBag(5);
                // Bag b3 = new ArrayBag(5);

                // if (b1.add(1) && b1.add(1) && b1.add(0) && b2.add(2) && b2.add(2) && b2.add(29)) {
                //     b3 = b1.unionWith(b2);
                // } 
                // System.out.println(b1);
                // System.out.println(b2);
                // System.out.println(b3);
        
                // //END OF PROBLEM SET TEST CODE

        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("size of bag 1: ");
        int size = scan.nextInt();
        ArrayBag bag1 = new ArrayBag(size);

        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < size; i++) {
            System.out.print("item " + i + ": ");
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per
        // line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        if (bag1.contains(itemStr)) {
            bag1.remove(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();

        scan.close();
    }
}
