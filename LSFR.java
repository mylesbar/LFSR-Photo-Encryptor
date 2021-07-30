
/**
 * Generates a Linear Feedback Shift Register to be used for the decryption of
 * an image. Contains the methods to calculate the pseudo-random numbers using
 * an initial seed ad a given tap bit.
 * 
 * @author Myles Barcelo
 * @version 7/28/2021
 */
public class LSFR {
    // instance variables - replace the example below with your own
    private int bit;
    private String lsfr;

    private boolean debug = false;

    // private String[]lsfrArray=new String[11];
    /**
     * Constructor for objects of class LSFR
     * 
     * @param t   Binary String representation of the seed.
     * @param int z Integer value of the tap bit.
     */
    public LSFR(String t, int z) {

        lsfr = t;
        bit = z;

        // lsfrArray = new String[t.length()];
        // lsfrArray=lsfr.split ( "(?!^)" );
    }

    /**
     * Outputs a String representation of the LSFR.
     * 
     * @param N/A
     * @return String
     */
    public String toString() {
        return lsfr;
    }

    /**
     * Calculates a "step" in the LSFR by taking the bitwise exclusive OR of the
     * first index of the seed and the index of the seed that matches the numeric
     * value of the tap bit. Stores the output of the bitwise exclusive OR as the
     * last index of the seed and shifts each remaining value of the step by 1
     * index.
     * 
     * @param N/A
     * @return int Integer value of newly shifted seed
     */
    public int step() {
        // System.out.println("-------------------------------------------------");
        if (debug) {
            System.out.println("original length:" + lsfr.length());
            System.out.println("original String: " + lsfr + " " + XOR.binaryToDec(lsfr));
        }
        StringBuilder sb = new StringBuilder();

        String end = XOR.xor(Character.toString(lsfr.charAt(0)), Character.toString(lsfr.charAt(bit)));

        for (int i = 1; i <= lsfr.length(); i++) {
            sb.append(lsfr.charAt(i - 1));
        }
        sb.append(end);
        sb.deleteCharAt(0);
        lsfr = sb.toString();

        if (debug) {
            System.out.println("Stepped Line:    " + sb.toString() + " " + XOR.binaryToDec(sb.toString()));

            System.out.println("End length: " + lsfr.length());
            System.out.println("-------------------------------------------------");
            System.out.println(Integer.parseInt(end));
        }

        sb.setLength(0);
        return Integer.parseInt(end);
    }

    /**
     * Conducts multiple shifts using the step() method at a given using the input
     * parameter as a quota for the amount of steps.
     * 
     * @param x Integer value of the quota of steps needed,
     * 
     * @return int Integer value of shifted seed
     */
    public int generate(int x) {
        StringBuilder genNum = new StringBuilder();

        for (int i = 0; i < x; i++) {
            // System.out.println("-------------------------------------------------");
            if (debug) {
                System.out.println("step # " + i + ":");
            }
            genNum.append(step());
        }

        int returnNum = XOR.binaryToDec(genNum.toString());
        genNum.setLength(0);
        return returnNum;

    }

    /**
     * Main method that tests the various LSFR functions.
     * 
     * @param args - no command line arguments needed
     * 
     * @return void
     */
    public static void main(String[] args) {
        LSFR lsfr = new LSFR("01101000010", 2);
        System.out.println("----------------");
        System.out.println("original seed: " + lsfr);
        System.out.println("----------------");

        // for (int i = 0; i < 10; i++) {
        // int bit = lsfr.step();
        // System.out.println(lsfr + " " + bit);
        // }

        for (int i = 0; i < 10; i++) {
            int r = lsfr.generate(5);
            System.out.println(lsfr + " " + r);
        }

    }
}
