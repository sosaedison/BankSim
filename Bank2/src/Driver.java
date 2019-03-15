
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This driver class contains a main that gets user input about the bank; the number of tellers
 * , whether the drive-thru is open and the file of customers. The main then runs the bank.
 *
 * @author Sosa Edison
 * @author Michael Gustin
 */
public class Driver {

    /**
     * Int that holds the number of Tellers we want for the simulation
     */
    static int amTellers;
    /**
     * Boolean that holds true for when the drive-thru is open and false for when
     * the drive-thru is closed.
     */
    static boolean driveThru;

    /**
     * Static block that helps clean up how we gather userinput for the creation of the bank.
     * As it takes in input, it binds the user until they enter an amount that's within the
     * bounds.
     */
    static {
        /* Setting the tellers number */
        System.out.println("Welcome to the bank simulation!");
        System.out.print("Please enter in the number of tellers you would like: ");
        Scanner reader = new Scanner(System.in);
        amTellers = reader.nextInt();

        while (amTellers < 3 || amTellers > 5) {
            System.out.println("Sorry, that number was outside the bounds");
            amTellers = reader.nextInt();
        }

        /* Setting up the driveThru */
        System.out.println("Would you like the drive-thru to be open?");
        System.out.println("Enter 1 for: YES");
        System.out.println("Enter 2 for: NO");

        int input = reader.nextInt();

        while (input < 1 || input > 2) {
            System.out.println("Sorry, that number was outside the bounds");
            amTellers = reader.nextInt();
        }

        if (input == 1) {
            driveThru = true;
        }else {
            driveThru = false;
        }
    }


    /**
     * Main method that handles how we create the bank and the file of
     * customers that the user wants to upload.
     * @param args
     */
    public static void main(String args[]) {
        // Create a file-selection dialog object

        /**
         * GETTING USER INPUT from static block
         */

        JFileChooser chooser = new JFileChooser();
        String line = "";
        EventParser parser;
        Bank bank = new Bank(amTellers, driveThru);

        try
        {
            // Display the dialog, and wait for return value.  If they cancel
            // out of the selection, throw an error -- no file to read
            if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
                throw new Error("Input file not selected");

            // Grab the selected File info
            File inFile = chooser.getSelectedFile();
            // Create a scanner, and attach it to the file.  Loop through
            // line at a time and print the contents to the screen.
            Scanner fileScanner = new Scanner(inFile);

            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine();
                //sending the line to the customer parser
                parser = new EventParser(line);
                bank.addCust(parser.createEvent());
            }

        } catch(
                FileNotFoundException e)

        {
            System.err.println("Data file not found.");
        } catch(
                Exception e)

        {
            System.err.println("A mysterious error occurred.");
            e.printStackTrace(System.err);
        }

        /* Run the bank */
        bank.run();
        System.out.println(bank);
    }


}
