import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 Full Name: Minh Tri Le
 Student ID: A00249054

 Program Description: This program provides useful tools for developers as following:
 1. encrypt - A Caesar cipher method that accepts a String (the String to encrypt),
 a shift value, and returns the new String.
 2. arrayAvg - A method that accepts an array and produces the average of all values (array should
 be numeric type)
 3. arrayContains - A method that accepts an array and a search value, and determines if
 the array contains the value (true/false) (can use any data type you want)
 4. reverse - A method that reverses an array (gives a new array)
 (can use any data type you want)

 To use this program:
 1. Please enter some text as instructed
 2. Then enter a shift value to be used by Caesar cipher encryption method, the results of
 encrypting and decrypting will be displayed afterwards.
 3. Then an auto-generated array of random values will be shown up with its average of all values,
 then please enter a number to search for in the array. The result determine if the array
 contains the number will be shown (true/false). Finally, the reverse version of the array will
 also be printed out.
 **/

public class ArrayTools {
    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {
        // ========== Encrypt and Decrypt string using Caesar cipher method ==========
        String stringToEncrypt; // User input string to encrypt
        String shiftInput;  // User input of 'shiftValue' to be used by Caesar cipher method
        int shiftValue = 0; // Parsed from 'shiftInput' for computation

        System.out.print("Enter a string to encrypt. ");
        stringToEncrypt = myScanner.nextLine();

        // To determine if input 'shiftValue' is an integer,
        // so no more input attempt is required
        Boolean isValidShiftValue = false;
        do {
            System.out.print("Enter a value to encrypt with. ");
            shiftInput = myScanner.nextLine();
            if (shiftInput.equals("quit")) {  // Allow users to quit program at anytime
                return;
            }
            try {
                // Convert 'shiftInput' string to 'shiftValue' integer for computation
                shiftValue = Integer.parseInt(shiftInput);
                isValidShiftValue = true;   // Successful conversion -> exit do-while loop
            } catch(Exception e){
                System.out.println("The input is not an integer value. Please try again or type 'quit' to exit.");
            }
        } while (!isValidShiftValue);  // Allow users to try enter value again if there's any mistaken or wrong input value

        // Start encrypting string by 'shiftValue'
        String encryptedString = encrypt(stringToEncrypt, shiftValue);
        System.out.println("The encrypted string is " + encryptedString);

        // Start decrypting the encrypted string above by 'shiftValue'
        String decryptedString = decrypt(encryptedString, shiftValue);
        System.out.printf("Decrypting %s with -%s...\n", encryptedString, shiftValue);
        System.out.println(decryptedString);

        System.out.println();

        // ========== Generate an array of random integers ==========
        int[] array = generateRandomValuesForArray(10, 100);

        System.out.print("Testing methods with ");
        printArray(array);  // Print all elements of array

        // ========== Calcualte average of all elements of the array ==========
        double avg = calculateArrayAverage(array);
        // Print out the average of all values in the array
        System.out.println("The average is " + String.format("%.1f", avg));

        System.out.println();

        // ========== Search for an element in the array ==========
        String valueToSearch;   // User input integer value to search for in the array
        Integer value = null;   // Integer converted value from user input one for computation

        // To determine if input 'value' is an integer,
        // so no more input attempt is required
        Boolean isValidValue = false;
        do {
            System.out.print("Enter a value to search for. ");
            valueToSearch = myScanner.nextLine();
            if (valueToSearch.equals("quit")) {  // Allow users to quit program at anytime
                return;
            }
            try {
                // Convert 'valueToSearch' string to 'value' integer for computation
                value = Integer.parseInt(valueToSearch);
                isValidValue = true;   // Successful conversion -> exit do-while loop
            } catch(Exception e){
                System.out.println("The input is not an integer value. Please try again or type 'quit' to exit.");
            }
        } while (!isValidValue);  // Allow users to try enter value again if there's any mistaken or wrong input value

        // Check if the input value is exist in the array or not
        if (isValueExistsInArray(value, array)) {
            System.out.printf("The array contains %s\n", value);
        } else {
            System.out.printf("The array does not contain %s\n", value);
        }

        System.out.println();

        // ========== Print out the reversed version of the array ==========
        System.out.print("The array reversed is ");
        printArray(reverseArray(array));
    }

    // Encrypt a string by Caesar cipher method
    static String encrypt(String str, int shift) {
        String result = "";
        // for optimization, so it doesn't need to invoke a function to access
        // length property of string on every loop
        int len = str.length();
        for(int i = 0; i < len; ++i) {
            result += (char)(str.charAt(i) + shift);
        }
        return result;
    }

    // Decrypt a string encrypted by Caesar cipher method
    static String decrypt(String str, int shift) {
        return encrypt(str, -shift);
    }

    // Print all elements of an array
    static void printArray(int[] array) {
        System.out.print("[ ");
        for (int i = 0; i < array.length - 1; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.printf("%d ]\n", array[array.length - 1]);
    }

    // Reverse an array and return a new array which left the original array intact
    static int[] reverseArray(int[] array) {
        int[] newArray = Arrays.copyOf(array, array.length);
        int midPos = newArray.length / 2;
        int lastPos = newArray.length - 1;
        int leftPointer = 0, rightPointer = lastPos;
        int tempInt;

        while (rightPointer > midPos || leftPointer < midPos) {
            tempInt = newArray[leftPointer];
            newArray[leftPointer] = newArray[rightPointer];
            newArray[rightPointer] = tempInt;

            ++leftPointer;
            rightPointer = lastPos - leftPointer;
        }

        return newArray;
    }

    // Generate an array of random integers with specified length and upper bound
    static int[] generateRandomValuesForArray(int lengthOfArray, int randomUpperBound) {
        int[] arrayOfRandomInts = new int[lengthOfArray];
        Random rand = new Random();

        // generate random values from 0 to (lengthOfArray - 1)
        for(int i = 0; i < lengthOfArray; ++i){
            arrayOfRandomInts[i] = rand.nextInt(randomUpperBound);
        }

        return arrayOfRandomInts;
    }

    // Calculate average of all elements of an integer array
    static double calculateArrayAverage(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; ++i) {
            sum += array[i];
        }
        return (double) sum / array.length;
    }

    // Check if an integer is exist in an integer array or not
    static Boolean isValueExistsInArray(int value, int[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (value == array[i]) {
                return true;
            }
        }
        return false;
    }
}
