//GROUP PROJECT 2 - IT 355

//Imports
import java.util.Scanner;
import java.util.ArrayList;


//CLASS: BIG PROJECT
public class BigProject {

    //Big Project Vars
    private static ArrayList<Employee> employeeList = new ArrayList<>();
    private static ArrayList<Patient> patientList = new ArrayList<>();


    /**
     * Main method
     */
    public static void main(String[] args){
        //setup variables
        Scanner scanner = new Scanner(System.in);
        int control = 0;
        Employee employee1 = new Employee("John Wick", "Janitor", 999);
        employeeList.add(employee1);
        System.out.println("\tWELCOME TO HOSPITAL 2.0 DIRECTORY\n");
        //loop through options
        while (control != -1) {
            control = 0;
            //Display options
            printOptions(); 
            System.out.print("\nEnter your choice (-1 to exit): ");
            //Read user input
            control = validateIntInput(control, scanner);
            //Handle user choice
            switch (control) {
                case -1:
                    System.out.println("\nThank you...exiting");
                    break; //exit the loop
                case 1:
                    option1(scanner);
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
    * prints the dispaly options within our main program
    */
    public static void printOptions()
    {
        System.out.println("OPTION 01: Check In New User");
        System.out.println("OPTION 02: ");
        System.out.println("OPTION 03: ");
        System.out.println("OPTION 04: ");
        System.out.println("OPTION 05: ");
        System.out.println("OPTION 06: ");
        System.out.println("OPTION 07: ");
        System.out.println("OPTION 08: ");
        System.out.println("OPTION 09: ");
        System.out.println("OPTION 10: ");
        System.out.println("OPTION 11: ");
        System.out.println("OPTION 12: ");
        System.out.println("OPTION 13: ");
        return;
    }

    /**
    * TODO 
    */
    public static void addEmployee(Scanner scanner) {
        System.out.print("Enter employee name: ");
        String name = scanner.next();
        System.out.print("Enter employee position: ");
        String position = scanner.next();
        System.out.print("Enter employee ID: ");
        //TODO - ensure ID is unique
        int id = scanner.nextInt();
        Employee employee = new Employee(name, position, id);
        employeeList.add(employee);
        System.out.println("Employee added successfully!");
        returnToMain(scanner);
    }

    /**
    * TODO
    */
    public static void addPatient(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        System.out.print("Enter patient ID: ");
        //TODO - ensure ID is unique
        int id = scanner.nextInt();
        Patient patient = new Patient(name, age, id);
        patientList.add(patient);
        System.out.println("Patient added successfully!");
        returnToMain(scanner);
    }

    /**
    * Validates the that the input from main is an int
    * @param input the initial integer value to validate.
    * @param scanner the Scanner object used to read user input.
    * @return the validated integer value from the user input.
    */
    public static int validateIntInput(int input, Scanner scanner)
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

    /**
     * TODO
     * option1 - check in new user
    */
    public static void option1(Scanner scanner)
    {
        try {
            addEmployee(scanner);
        } catch (Exception e) {
            System.out.println("ERROR: could not add patient. Please add valid input");
        }
    }




}//END: MAIN CLASS 





//CLASS: Employee
class Employee {
    private String name;
    private String position;
    private int id;

    public Employee(String name, String position, int id) {
        this.name = name;
        this.position = position;
        this.id = id;
    }

    // Getter methods
    public String getName() { return name; }
    public String getPosition() { return position; }
    public int getId() { return id; }
} //END: Employee





//CLASS: Patient
class Patient {
    private String name;
    private int age;
    private int id;

    public Patient(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // Getter methods
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getId() { return id; }
} //END: Patient