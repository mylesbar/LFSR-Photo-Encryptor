
/**
 * This XOR class provides the methods to calculate the binary form of two
 * numbers, calculate the bitwise exclusive OR of two numbers and return output
 * in either boolean, String or int data types. Also yields the method to
 * convert the binary form of a number back into decimal format.
 * 
 * 
 * @author Myles Barcelo
 * @version 7/28/2021
 * 
 */
import java.lang.StringBuilder;

public class XOR {

    /**
     * Conducts the bitwise exclusive OR operator on two boolean inputes
     * 
     * @param x true or false boolean
     * @param y true or false boolean
     * 
     * @return boolean output of the bitwise exclusive OR operation
     */
    public static boolean xor(boolean x, boolean y) {
        if ((x == true && y == false) || (x == false && y == true)) {
            return true;
        }
        return false; // replace this with the actual xor logic
    }

    /**
     * Conducts the bitwise exclusive OR on two strings assumed to be the binary
     * representation of two numbers.
     * 
     * @param x String representation of a number in binary form
     * @param y String represtation of a number in binary form
     * 
     * @return String representation of the new number after a bitwise exlusive OR
     *         operation.
     */

    public static String xor(String x, String y) {

        // debug
        // System.out.println("---------------------------------");
        // System.out.println("In XOR");
        // System.out.println("X: " + x);
        // System.out.println("Y: " + y);

        // assumes the two strings are composed only of 1s and 0
        String xIn = x;
        String yIn = y;

        int xLength = xIn.length();
        int yLength = yIn.length();

        if (y.length() < x.length()) {
            // System.out.println("---------------------------------");
            // System.out.println("lengthening y");

            while (xLength > yLength) {
                yIn = "0" + yIn;
                yLength++;
            }
        }

        if (x.length() < y.length()) {
            // System.out.println("---------------------------------");
            // System.out.println("lengthening x");

            while (xLength < yLength) {
                xIn = "0" + xIn;
                xLength++;
            }
        }

        // System.out.println("---------------------------------");
        // System.out.println("new X: " + xIn);
        // System.out.println("new Y: " + yIn);
        // System.out.println("---------------------------------");

        // Build Output String
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < xIn.length(); t++) {
            // System.out.println("Loop: " + t);
            if (xIn.charAt(t) != yIn.charAt(t)) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            // read the two integers, and compare #'s by indentifying array index.
        }

        // generate output from StringBuilder
        String outputString = sb.toString();
        // System.out.println("sb tostring: " + sb.toString());

        return outputString; // replace this with the actual xor logic for a string of 1s and 0s
    }

    /**
     * Conducts the bitwise exclusive OR on two integers after converting them into
     * binary String representation.
     * 
     * @param x integer input for the bitwise exclusive OR
     * @param y integer input for the bitwise exclusive OR
     * 
     * @return int New integer calculated by the bitwise exclusive OR operator.
     */

    public static int xor(int x, int y) {
        // assumes the integers are non-negative
        // don't forget to convert your integers to binary first
        String newS1 = decToBinary(x);
        String newS2 = decToBinary(y);
        String fixedString = xor(newS1, newS2);
        int fixedInt = binaryToDec(fixedString);

        return fixedInt; // replace this with the actoal xor logic for two integers
    }

    /**
     * Converts an integer into a binary String representation.
     * 
     * @param x integer input to be converted into a binary String
     * 
     * @return String represenation of a number in binary form.
     */
    public static String decToBinary(int x) {
        // assumes x >= 0
        if (x == 0) {
            return "0";
        }
        String binary = "";
        while (x > 0) {
            int rem = x % 2;
            binary = rem + binary;
            x = x / 2;
        }
        return binary;
    }

    /**
     * Converts a binary String into its integer form.
     * 
     * @param s String representation of an integer
     * 
     * @return int Integer representation of a number converted from binary form.
     */
    public static int binaryToDec(String s) {
        // assumes s is a string of 1s and 0s.
        int powTwo = 1;
        int decimal = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int x = Integer.parseInt(s.substring(i, i + 1));
            decimal = decimal + x * powTwo;
            powTwo = 2 * powTwo;
        }
        return decimal;
    }

    /**
     * Main method that tests the various XOR static functions
     * 
     * @param args - no command line arguments needed
     */
    public static void main(String[] args) {
        String s = decToBinary(873);
        System.out.println(s); // expected output: 1101101001
        int x = binaryToDec(s);
        System.out.println(x); // expected output: 873

        String s1 = decToBinary(20);
        System.out.println("s1: " + s1);// expected output: 10100
        int x1 = binaryToDec(s1);
        System.out.println("x1: " + x1); // expected output 20;

        String tester = xor(s, s1);
        System.out.println(tester + " tester binary");

        int cheat = 873 ^ 20;
        String cheatS = decToBinary(cheat);
        System.out.println(cheatS + " cheat binary"); // expected output 1101111101

        int testerInt = xor(x, x1);
        System.out.println(testerInt + " tester int"); // expected output 893
        System.out.println(cheat + " cheat int");

        System.out.println("Program Done");

    }

}