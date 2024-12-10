/*
 ***** THIS FILE IS FOR TESTING PURPOSES ONLY. *****
 * 
 * Put the final versions of your methods for problem 2 
 * in your ps1_partI file.
 * 
 * Do NOT submit them in this file.
 */

import java.util.*;    // for access to the Arrays class

public class Problem2Test {
    /*** Put your methods for problems 2a and 2b below ***/
    
    
    
 
    /*
     * This main method includes several tests for your methods.
     * 
     * It will not compile until you have added correct definitions
     * for both of the methods.
     * 
     * We encourage you to add additional tests as well.
     */ 

    public static void shiftRight (int[] arr) {
        /*Checking if the passed pointer is null and throw an exception if applicable*/
        if (arr == null) {
            throw new IllegalArgumentException("Null Pointer provided");
        }

        /*Checking if the array is of length 0 or 1 and do nothing if applicable*/
        if (arr.length == 0 || arr.length == 1) {
            return;
        }
        else {
            /*Starting by storing the value in the last cell of the table in a temporary variable*/
            int temp = arr[arr.length-1];
            /*Replacing value from the right side all the way to the left side using a for loop*/
            for (int i = arr.length-1 ; i > 0; i--) {
                arr[i] = arr [i-1];
            }
            /*Putting the last value to the first cell*/
            arr[0] = temp;
        }

    }

    public static int indexOf (int[] arr1, int[] arr2) {
        /*Checking if the length of the first array is larger than the second array, and return -1 if applicable*/
        if (arr1.length > arr2.length) {
            return -1;
        }
        else {
            /*ind is the index of the first occurence to be returned, or -1 if there is not occurence
             * i is the current starting index on the list arr2 where we would check for the presence
             * of the first list arr1
             */
            int ind = -1, i = 0;

            /*At the index i on the second list arr2, This WHILE loop assumes that the first list arr1 has not been found yet, and that 
             * arr2 is still large enough to contain all characters of arr1
            */
            while (ind == -1 && (arr1.length - 1 + i) <= (arr2.length - 1) ) {
                /*this WHILE loop assumes that the current index i IS the first occurence,
                 * and checks if characters match in both arrays
                 */
                ind = i;
                int j = 0;
                while (ind != -1 && j < arr1.length) {
                    /*Checking if at least one value in both lists differ, starting at index j*/
                    if (arr1[j] != arr2[i + j]) ind = -1;
                    j++; /*Updating index j*/
                }
                i++; /*updating index j */
            }
            /*If arr1 was found, the corresponding ind should have the value of that location
             * If arr1 was not found, the corresponding ind should have the original value -1 
             */
            return ind;    
        }
    }



    public static boolean search (Object item, Object[] arr) {
        /*Checking for null pointer parameter*/
        if (arr == null) {
            throw new IllegalArgumentException("Null Pointer provided");
        }

        /*Checking for empty array*/
        if (arr.length == 0) {
            return false;
        }

        for (int i = 0; i< arr.length; i++) {
            /*Two objects are equal if two conditions are met:
             * 1-They are of the same class
             * 2-They are equal
            */

            /*Verifying the first condition */
            if (item.getClass() == arr[i].getClass()){
                /*Verifying the second condition */
                if (arr[i].equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean search (Object item, Object[] arr, int start) {
        /*Checking for null pointer parameter*/
        if (arr == null) {
            throw new IllegalArgumentException("Null Pointer provided");
        }
        /*Checking for empty array*/
        else if (arr.length == 0) {
            return false;
        }
        /*If the passed array has one item, compare that item and return the result */
        else if (arr.length == 1) {
            return arr[0].equals(item);
        }
        /*If the passed array has more than one items */
        else {
            /*Base case 1: If we reach the last item, compare the item at that index and return the result
             * No need any additional recursive call at this point.
            */
            if (start == arr.length - 1) {
                return arr[arr.length - 1].equals(item);
            }
            else {
                /*Base case 2: If we are not yet at the last item and found the item, return true */
                if (arr[start].equals(item)) {
                    return true;
                }
                /*Recursive case: If we are not yet at the last item and didn't find the item, we check the next index */
                else {
                    return search (item, arr, start+1);
                }
            }
        }
    }

    public static void main (String[] args) {
        int[] values = {0, 2, 4, 6, 8, 10};
        System.out.println("values array before shifting:");
        System.out.println(Arrays.toString(values));
        shiftRight(values);
        System.out.println("values array after shifting:");
        System.out.println(Arrays.toString(values));
        System.out.println();

        int[] list1 = {1, 3, 6};
        //int[] list1 = {1, 3, 5, 8, 12, 1, 3, 17, 1, 3, 6, 9, 1, 3, 6, 7};
        int[] list2 = {1, 3, 5, 8, 12, 1, 3, 17, 1, 3, 6, 9, 1, 3, 6};
        int index = indexOf(list1, list2);
        System.out.println("indexOf(list1, list2) returns " + index);


        //Problem 4
        Object[] arrStringS ={"Hello", "World", 3, true};
        boolean bool = true;
        int number = 3;

        if(search(number, arrStringS, 0)){
            System.out.println("Object " + number +  " found");
        }
        else {
            System.out.println("Object " + number + " NOT found");
        };

        if(search(bool, arrStringS)){
            System.out.println("Object " + bool +  " found");
        }
        else {
            System.out.println("Object " + bool + " NOT found");
        };

        if(search(bool, arrStringS, 0)){
            System.out.println("Object " + bool +  " found");
        }
        else {
            System.out.println("Object " + bool + " NOT found");
        };
    }
}