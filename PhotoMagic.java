
/**
 * The PhotoMagic class scrambles the pixels of an image using a pseudonumber generator 
 * based on a linear feedback shift register. 
 * 
 * @author Myles Barcelo
 * @version 7/28/2021
 * 
 */
import java.awt.Color;
import java.util.Scanner;

public class PhotoMagic {

    /**
     * Takes the RGB values of each pixel in an image and runs it through a bitwise
     * XOR function. The output value of the XOR function
     * 
     * @param Picture Picture object made from an input image file.
     * @param LSFR    LSFR object generated from an initial seed and tap bit for XOR
     *                function.
     * 
     * @return Picture Returns scrambled/unscrambled picture.
     */
    public static Picture transform(Picture inputPic, LSFR lsfr) {
        // initalize
        Picture newPic = new Picture(inputPic.width(), inputPic.height());
        newPic.setOriginUpperLeft();

        System.out.println("new Picture Dimensions:" + inputPic.width() + " " + inputPic.height());

        // getRGB

        for (int i = 0; i < newPic.width(); i++) {
            for (int j = 0; j < newPic.height() - 1; j++) {

                // getcolor
                int redComp = inputPic.get(i, j).getRed();
                int greenComp = inputPic.get(i, j).getGreen();
                int blueComp = inputPic.get(i, j).getBlue();

                // System.out.println(redComp);
                // System.out.println(greenComp);
                // System.out.println(blueComp);

                String redBin = XOR.decToBinary(redComp);
                String greenBin = XOR.decToBinary(greenComp);
                String blueBin = XOR.decToBinary(blueComp);

                // System.out.println(redBin);
                // System.out.println(greenBin);
                // System.out.println(blueBin);

                // Generate 8-bit number from LFSR
                // System.out.println("Generating red");
                lsfr.generate(5);
                // System.out.println(lsfr.toString());

                redBin = XOR.xor(redBin, lsfr.toString().substring(0, 8));

                // System.out.println("Generating blue");
                lsfr.generate(5);
                // System.out.println(lsfr.toString());
                greenBin = XOR.xor(greenBin, lsfr.toString().substring(0, 8));

                // System.out.println("Generating green");
                lsfr.generate(5);
                // System.out.println(lsfr.toString());
                blueBin = XOR.xor(blueBin, lsfr.toString().substring(0, 8));

                int red = XOR.binaryToDec(redBin);
                int green = XOR.binaryToDec(greenBin);
                int blue = XOR.binaryToDec(blueBin);

                // System.out.println("red: " + red);
                // System.out.println("green: " + green);
                // System.out.println("blue: " + blue);

                // XOR component with LFSR number
                Color newPixel = new Color(red, green, blue);

                // set pixel
                newPic.set(i, j, newPixel);

                System.out.println("Last i and j: " + i + "  " + j);

            }
        }
        // newPic.show();
        return newPic;
    }

    public static void main(String[] args) {

        /**
         * 
         * Reads in file name from user and asks if encrypting or decrypting. Saves to
         * folder's root directory & displays the resulting picture to the user.
         * 
         * Debug inputFile = pipe.png | seed = 0110 1000 0101 0001 0000 | tap = 3
         * 
         */

        Scanner kb = new Scanner(System.in);
        String inputFile = "";
        String outFile = "";
        String seed = "";
        int tap = 3;

        int status = 0;

        System.out.println("Encrypt or Decrypt?");
        String answer = kb.nextLine();

        if (answer.toLowerCase().equals("encrypt") || answer.equals("1")) {

            status = 1;
            System.out.print("Enter Filename w/ extension: ");
            String fileName = kb.nextLine();
            inputFile = fileName;
            seed = "01101000010100010000";
            tap = 3;

        } else if (answer.toLowerCase().equals("decrypt") || answer.equals("0")) {

            status = 0;
            System.out.print("Enter Filename w/ extension: ");
            String fileName = kb.nextLine();
            inputFile = fileName;
            seed = "01101000010100010000";
            tap = 3;

        }

        Picture input = new Picture(inputFile);
        LSFR inReg = new LSFR(seed, tap);
        Picture transformedPic = transform(input, inReg);

        if (status == 1) {
            outFile = "scrambled_" + inputFile;
        }
        if (status == 0) {
            outFile = "unscrambled_" + inputFile;
        }

        transformedPic.save(outFile);
        transformedPic.show();
        kb.close();
        System.out.println("Program Done");
    }

}
