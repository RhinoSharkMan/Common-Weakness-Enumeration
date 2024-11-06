//GROUP PROJECT 2 - IT 355

//Imports
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

//CLASS: BIG PROJECT
public class BigProject {


    //Avoids CWE-500: Public Static Field Not Marked Final by making global variable a constant
    public static final int patientCapacity = 300; 

    //Big Project Vars
    private static ArrayList<Employee> employeeList = new ArrayList<>();
    private static ArrayList<Patient> patientList = new ArrayList<>();

    private static int numPatients = 0;

    /**
     * Main method
     */
    public static void main(String[] args){
        //setup variables
        Scanner scanner = new Scanner(System.in);
        int control = 0;
        Employee employee1 = new Employee("John Wick", "Janitor", 999, generateRandomSixDigitNumber());
        employeeList.add(employee1);
        System.out.println("\tWELCOME TO HOSPITAL 2.0 DIRECTORY\n");
        
      //loop through options     
      //Avoids CWE-484: Ommitted Break Staement in Switch by ensuring each case has a break statement.
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
                    option2(scanner, employeeList);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                //needs more work - need to validate int from scanner - need to validate index in range.
                case 10:
                    System.out.println("Provide index of Patient to copy:");
                    int patientIndex = scanner.nextInt(); 

                    Patient patientCopy = copyPatientInformation(patientList.get(patientIndex));

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
        System.out.println("OPTION 02: Admin Mode");
        System.out.println("OPTION 03: ");
        System.out.println("OPTION 04: ");
        System.out.println("OPTION 05: ");
        System.out.println("OPTION 06: ");
        System.out.println("OPTION 07: ");
        System.out.println("OPTION 08: ");
        System.out.println("OPTION 09: ");
        System.out.println("OPTION 10: Copy Patient Information");
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
        int pin = generateRandomSixDigitNumber();
        Employee employee = new Employee(name, position, id, pin);
        employeeList.add(employee);
        System.out.println("Employee added successfully!");
        returnToMain(scanner);
    }

    /**
    * TODO
    */
    public static void addPatient(Scanner scanner) {
        if (numPatients < patientCapacity){
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
            numPatients++;
            returnToMain(scanner);
        }
      else {
         System.out.println("Invalid Patient");
      }
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
    public static int generateRandomSixDigitNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
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

    /**
     * TODO
     * option2 - admin mode
    */
    public static void option2(Scanner scanner, ArrayList<Employee> employeeList)
    {
        try {
            Employee temp = null;
            System.out.println("\tAdmin Login");;
            System.out.print("Enter User ID: ");
            int userId = scanner.nextInt();
            for (Employee emp : employeeList) {
                if (emp.id == userId) {
                    temp= emp;
                    break;
                }
            }
            //Prompt for PIN
            if (temp != null) {
                System.out.print("Enter PIN: ");
                int pin = scanner.nextInt();
                // Check if the entered PIN matches the employee's PIN
                if (temp.comparePIN(temp, pin) == true) {
                    System.out.println("Login successful. Welcome, " + temp.name + "!");
                    System.out.println("it is a shame admin mode cannot do anything lol");
                } else {
                    System.out.println("ERROR: Incorrect PIN. Returning...");
                }
            } else {
                System.out.println("ERROR: User ID not found.");
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: request could not be granted");
        }
        returnToMain(scanner);
    }


    //creates a copy of a patient's information by copying their Patient object.
    //method is private to ensure patient's information cannot be publically accessed through cloning. 
    private static Patient copyPatientInformation(Patient original)
    {
       try {
           return (Patient) original.clone();
       } catch (CloneNotSupportedException e) {
        System.out.println("Patient invalid, unable to copy"); 
        return null; 
       }
    }

    //Andrew - need to add method covering valid use of type conversion. Need input validation for methods.



}//END: MAIN CLASS 





//CLASS: Employee
class Employee {
    public String name;
    public String position;
    public int id;
    private int pin;

    public Employee(String name, String position, int id, int pin) {
        this.name = name;
        this.position = position;
        this.id = id;
        this.pin = pin;
    }

    // Getter methods - Avoids CWE-767: Access to Critical Private Variable in Public Method
    //by ensuring crticial information can only be accessed through private methods. 
    private String getName() { return name; }
    private String getPosition() { return position; }
    private int getId() { return id; }
    private int getPin(){
        return pin;
    }

    public boolean comparePIN(Employee x, int test)
    {
        if(test == x.pin)
        {
            return true;
        }
        else
        {
            return false;
        }
    }



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

    //Returns a clone of the Patient object.
    //Avoids CWE-580: clone() method Without super.clone() by calling 
    //the necessary super.clone() method whenever cloning occurs. 
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone(); 
    }

    // Getter methods - Avoids CWE-767: Access to Critical Private Variable in Public Method
    //by ensuring crticial information can only be accessed through private methods. 
    private String getName() { return name; }
    private int getAge() { return age; }
    private int getId() { return id; }
} //END: Patient


