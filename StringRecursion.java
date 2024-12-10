import java.lang.String;

public class StringRecursion {

    public static void printLetters(String str) {
        if (str == null || str.length() == 0) {
            /*The method should not do any printing if the empty string ("") or the value null is passed in as the parameter; it should simply return */
            return;
        }
        else if (str.length() == 1) {
            /*BASE CASE: If the string has only one character, we print that character*/
            System.out.print(str.charAt(0) );
        }
        else {
            /*RECURSIVE CASE: We print the first character, the comma and space, and call the function again starting at the second character */
            char first_char = str.charAt(0);
            System.out.print (first_char + ", ");
            printLetters(str.substring(1));
        }
    }


    public static String replace(String str, char oldChar, char newChar) {
        /*f the first parameter is null, the method should return null.*/
        if (str == null) {
            return null;
        }
        /*If the first parameter is the empty string (""), the method should return the empty string. */
        else if (str == "") {
            /*BASE CASE: If we reach the end of the string */
            String emptyString = new String("");
            return emptyString;
        }
        else if (str.length() == 1) {
            char first_char = str.charAt(0);
            if (first_char == oldChar) first_char = newChar;
            String export = Character.toString(first_char);
            return export;
        }
        else {
            /*RECURSIVE CASE */
            char first_char = str.charAt(0);
            /*We check if the first character of the string is equal to the old character and remove it if applicable */
            if (first_char == oldChar) first_char = newChar;

            /*We concatenate the first character (modified or not) witht the result of the call of replace on the remaining substring */
            String export = new String (first_char + replace(str.substring(1), oldChar, newChar));
            return export;
        }
    }


    public static int indexOf(char ch, String str) {
        if (str == null || str.length() == 0) {
            /*BASE CASE: If we reach the end of the string without finding the character, or if the string is null */
            return -1;
        }
        else if (ch == str.charAt(0)) {
            /*BASE CASE: If we find the character, no need to keep track of how far from the beginning we've gone, so the step is zero */
            return 0;
        }
        else {
            /*RECURSIVE CASE */
            int rest = indexOf(ch, str.substring(1));
            /*If We can't find the character, we have to channel the result -1 all the way to the original call, no need to keep track of the number of steps */
            if (rest == -1) {
                return -1;
            }
            else {
                /* If we don't find the character, we add one step to the number of steps we will get on the remaining of the string*/
                return 1 + rest;
            }
        }
    }


    public static String trim (String str) {
        if ( str == null ) { /*If the parameter is null, the method should return null. */
            return null;
        }
        else if ( str.length() == 0) { /*If the parameter is the empty string, the method should return the empty string. */
            return str;
        }
        else if ( str.charAt(0) == ' ') { /*If the string has a leading space, we take it out by calling trim on the substring skipping that first space */
            return trim( str.substring(1) );
        }
        else if ( str.charAt( str.length()-1 ) == ' ') { /*If the string has a trailing space, we take it out by calling trim on the substring skipping that last space */
            return trim( str.substring(0, str.length()-1) );
        }
        else { /*If the non empty string has neither leading or tailing space, we return it as it is */
            return str;
        }
    }


    public static void main (String[] args) {
        String r1 = new String("Rabbit");
        System.out.println("");
        String r2 = new String("I like to recurse!");
        printLetters(r1);
        printLetters(r2);
        printLetters("");
        printLetters(null);

        System.out.println("");

        String r3 = replace("base case", 'e', 'y');
        String r4 = replace("base case", 'r', 'y');
        String r5 = replace(null, 'r', 'y');
        String r6 = replace("", 'r', 'y');
        System.out.println(r3);
        System.out.println(r4);
        System.out.println(r5);
        System.out.println(r6);

        System.out.println("");

        String r7 = new String("");
        String r8 = null;
        System.out.println(indexOf('b', r1));
        System.out.println(indexOf('P', r1));
        System.out.println(indexOf('P', r7));
        System.out.println(indexOf('P', r8));

        System.out.println("");

        
        System.out.print("\n    hello world \nafter trimming is\n" + trim(" hello world "));
        System.out.print("\nrecursion \nafter trimming is\n" + trim("recursion "));
        System.out.print("\nunchanged\nafter trimming is\n" + trim("unchanged"));
        System.out.print("\n(empty)\nafter trimming is\n" + trim(""));

        


    }
    
}
