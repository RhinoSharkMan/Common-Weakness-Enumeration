//Imports
import java.util.Scanner;

//CLASS: template_case
public class BigProject {





    /**
     * Main method
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int control = 0;
        System.out.println("WELCOME TO [X] DIRECTORY\n");
        while (control != -1) {
            control = 0;
            //Display options
            System.out.println("OPTION 01: ");
            System.out.println("OPTION 02: ");
            System.out.println("OPTION 03: ");
            System.out.println("OPTION 04: ");
            System.out.println("OPTION 05: ");
            System.out.print("\nEnter your choice (-1 to exit): ");
            //Read user input
            control = validateInput(control, scanner);
            //Handle user choice
            switch (control) {
                case -1:
                    System.out.println("\nThank you...exiting");
                    break; //exit the loop
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("ERROR: not a valid choice. Try again.");
            }//end case
        }//end loop
        scanner.close();
    }//end function



/*
 * ALL OTHER METHODS
 */

    /**
    * Validates the that the input from main is an int
    * @param input the initial integer value to validate.
    * @param scanner the Scanner object used to read user input.
    * @return the validated integer value from the user input.
    */
    public static int validateInput(int input, Scanner scanner)
    {
        if (scanner.hasNextInt() == true) {
            input = scanner.nextInt();
            } 
        else {
            scanner.next(); //clear the invalid input
            }
        return input;
    }


    /**
    * Buffer to avoid skipping directly to main loop
    * @param scanner the Scanner object used to read user input.
    */
    public static void returnToMain(Scanner scanner)
    {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        System.out.print("\nPress 'enter' to return to the main menu...");
        scanner.nextLine();
        System.out.println(""); // Print a blank line for spacing
    }




}//END: class
