//GROUP PROJECT 2 - IT 355

//Imports
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//CLASS: BIG PROJECT
public class BigProject {


    //Avoids CWE-500: Public Static Field Not Marked Final by making global variable a constant
    public static final int patientCapacity = 300; 

    //Big Project Vars
    private static ArrayList<Employee> employeeList = new ArrayList<>();
    private static ArrayList<Patient> patientList = new ArrayList<>();
    private static ArrayList<Person> personList = new ArrayList<Person>();
    private static DaySystem daySystem = new DaySystem();
    private static int numPatients = 0;

    /**
     * Main method
     */
    public static void main(String[] args){
        //Start the day-cycling thread
        Thread dayThread = new Thread(daySystem);
        dayThread.start();
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
                    dayThread.interrupt();
                    break; //exit the loop
                case 1:
                    option1(scanner);
                    break;
                case 2:
                    option2(scanner, employeeList);
                    break;
                case 3:
                    option3(scanner);
                    break;
                case 4:
                    option4(scanner);
                    break;
                case 5:
                    option5(scanner);
                    break;
                case 6:
                    displayCurrentDayAndTime(scanner);
                    break;
                //ISSUE - Not correctly copying 
                case 7:
                    option7(scanner);
                break;
                case 8:
                    option8(scanner);
                break;
                case 9:
                    option9(scanner);
                break;
                case 10:
                    System.out.println("Provide index of Patient to copy:");
                    int patientIndex = scanner.nextInt(); 

                    if (patientIndex < patientList.size() && patientIndex >= 0)
                    {
                    Patient patientCopy = copyPatientInformation(patientList.get(patientIndex));
                    System.out.println("Patient's information sucessfully copied.");
                    System.out.println("Original Patient Name: " + patientList.get(patientIndex).getName());
                    System.out.println("Copied Patient Name: " + patientCopy.getName() + "\n");
                    }
                    else
                    {
                        System.out.println("Patient index out of range\n");
                    }
                    break; 
                case 11:
                    System.out.println("Calculating Employee to Patient Ratio: ");

                    if (patientList.size() > 0)
                    {
                    double ratio = findRatio(); 
                    System.out.println("There are approximately " + ratio + " employees per patient\n");
                    }

                    else 
                    {
                        System.out.println("Patient list empty - cannot compute average\n"); 
                    }
                    break;
                case 12:
                    employeePatientNameSame();
                    break;
                case 13:
                    divideSupply(scanner);
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
        System.out.println("\n\tMake your choice:");
        System.out.println("OPTION 01: Check In New Employee");
        System.out.println("OPTION 02: Admin Mode");
        System.out.println("OPTION 03: Export Prescription");
        System.out.println("OPTION 04: Add prescription");
        System.out.println("OPTION 05: Check In New Patient");
        System.out.println("OPTION 06: Today's Date");
        System.out.println("OPTION 07: Cognitive Test");
        System.out.println("OPTION 08: See all Employees");
        System.out.println("OPTION 09: See all Patients");
        System.out.println("OPTION 10: Copy Patient Information");
        System.out.println("OPTION 11: Employee to Patient Ratio");
        System.out.println("OPTION 12: Create Shared Name List");
        System.out.println("OPTION 13: Divide Supplies");
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
        int id = scanner.nextInt();
        if(isUniqueID(id)){
            int pin = generateRandomSixDigitNumber();
            Employee employee = new Employee(name, position, id, pin);
            employeeList.add(employee);
            System.out.println("Employee added successfully!");
        } else {
            System.out.println("\nID is not unique");
        }
        returnToMain(scanner);
    }

    /**
    * TODO
    */
    public static void addPatient(Scanner scanner) {
        if (numPatients < patientCapacity){
            System.out.print("Enter patient name: ");
            String name = scanner.next();
            System.out.print("Enter patient age: ");
            int age = scanner.nextInt();
            System.out.print("Enter patient ID: ");
            int id = scanner.nextInt();
            if(isUniqueID(id)) {
                Patient patient = new Patient(name, age, id);
                patientList.add(patient);
                System.out.println("Patient added successfully!");
                numPatients++;
            } else {
                System.out.println("\nID is not unique. Please try again.");
            }
            returnToMain(scanner);
        }
      else {
         System.out.println("Invalid Patient");
      }
    }

    /**
    * TODO
    */
    public static void exportPrescription(Scanner scanner) {
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        Patient patient = null;
        boolean found = false;
        for(int i = 0; i<patientList.size(); i++){
            if (id == patientList.get(i).getId()){  
                patient = patientList.get(i);
                found = true;
            }
        }
        if(found){
            if(patient.getMedList()!=null){
                patient.getMedList().exportMedList();
            }
            else{
                System.out.println("This patient has no prescription");
            }
            returnToMain(scanner);
        }
        else{
            System.out.println("Patient ID not found");
        } 
    }

    private static boolean isUniqueID(int id){
        for(Employee e: employeeList){
            if(e.getId() == id)
                return false;
        }
        for(Patient p: patientList){
            if(p.getId() == id)
                return false;
        }
        return true;
    }

     /**
    * TODO
    */
    public static void addPrescription(Scanner scanner) {
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter medication: ");
        String medication = scanner.next();
        Patient patient = null;
        boolean found = false;
        for(int i = 0; i<patientList.size(); i++){
            if (id == patientList.get(i).getId()){  
                patient = patientList.get(i);
                found = true;
            }
        }
        if(found){
            if(patient.getMedList()!=null){
                patient.getMedList().addMedication(medication);
                System.out.println("Medication successfully added to prescription");
            }
            else{
                ArrayList<String> ml = new ArrayList<>();
                patient.createPrescription(ml);
                patient.getMedList().addMedication(medication);
                System.out.println("Medication successfully added to prescription");
            }
            returnToMain(scanner);
        }
        else{
            System.out.println("Patient ID not found");
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
        System.out.print("\n\tPress 'enter' to return to the main menu...");
        scanner.nextLine();
        System.out.println(""); // Print a blank line for spacing
    }

    /**
     * TODO
     * generates a random 6 digit number
    */
    public static int generateRandomSixDigitNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    /** Preventing uncaught exceptions using try-catch CWE-248 
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

    public static void option3(Scanner scanner)
    {
        try {
            exportPrescription(scanner);
        } catch (Exception e) {
            System.out.println("ERROR: Patient ID not found");
        }
    }

    public static void option4(Scanner scanner)
    {
        try {
            addPrescription(scanner);
        } catch (Exception e) {
            System.out.println("ERROR: Unable to add prescription");
        }
    }

    public static void option5(Scanner scanner)
    {
        try {
            addPatient(scanner);
        } catch (Exception e) {
            System.out.println("ERROR: could not add patient. Please add valid input");
        }
    }

    /**
     * TODO
     * option7 - cog. test
     * 
    */
    public static void option7(Scanner scanner) {
        System.out.println("Employee List:");
        if (employeeList.isEmpty()) {
            System.out.println("\tNo employees available.");
        } else {
            for (Employee employee : employeeList) {
                System.out.println("\t" + employee.toString());  
            }
        }
        returnToMain(scanner);
    }
    
    /**
     * TODO
     * option8 - displays the list of employees
     * 
    */
    public static void option8(Scanner scanner) {
        System.out.println("Employee List:");
        if (employeeList.isEmpty()) {
            System.out.println("\tNo employees available.");
        } else {
            for (Employee employee : employeeList) {
                System.out.println("\t" + employee.toString());  
            }
        }
        returnToMain(scanner);
    }

    /**
     * TODO
     * option9 - ddisplays the list of patients
     * 
    */
    public static void option9(Scanner scanner) {
        System.out.println("Patient List:");
        if (patientList.isEmpty()) {
            System.out.println("\tNo patients available.");
        } else {
            for (Patient patient : patientList) {
                System.out.println("\t" + patient.toString());  
            }
        }
        returnToMain(scanner);
    }

    /**
     * divideSupply - Divides the total number of employees and patients by the user-provided
     * number of supplies, ensuring that division by zero is handled gracefully.
     * option13()
     * @param scanner a Scanner object to read user input
     */
    public static void divideSupply(Scanner scanner) {
        int totalEmployees = employeeList.size();
        int totalPatients = patientList.size();
        int totalCount = totalEmployees + totalPatients;
        System.out.println("\nTotal Number of People in System: " + totalCount);
        System.out.print("Enter the number of supplies to divide among employees and patients: ");
        try {
            int supplies = scanner.nextInt();
            int result = supplies/totalCount;
            System.out.println("\tEach Person recieves" + result + " of the product.");

        //catch exceptions
        } catch (InputMismatchException e) { 
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next();  //clear the invalid input
        }
        catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero. Please enter a non-zero number.");
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

    /**
     * Creates a list of all the employees with the same first name as a patient
     * 
     * Uses CWE-486 to safely compare employee and patient classes
     */
    public static ArrayList<String> employeePatientNameSame(){
        ArrayList<String> sharedNames = new ArrayList<>();
        combineList();
        boolean atLeastOne = false;
        for(Person p1: personList){
            for(Person p2: personList){
                if(p2.getClass() != p1.getClass() && p2.getName().equals(p1.getName()) && !sharedNames.contains(p1.getName())){
                    sharedNames.add(p1.getName());
                    atLeastOne = true;
                }
            }
        }
        if (atLeastOne){
            System.out.println("\nThere are shared names!");
        } else {
            System.out.println("\nThere are no shared names between employees and patients");
        }
        return sharedNames;
    }

    public static void combineList(){
        personList = new ArrayList<>();
        for(Employee e: employeeList){
            personList.add(e);
        }
        for(Patient p: patientList){
            personList.add(p);
        }
    }


    /**
     * TODO
     * option5 - display current day and time
     * 
    */
    public static void displayCurrentDayAndTime(Scanner scanner) 
    {
        String currentDay = daySystem.getCurrentDay();
        int daysPassed = daySystem.getTotalDaysPassed();
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("\nCurrent day: " + currentDay + ", Time: " + currentTime);
        System.out.println("Total Days Passed: " + daysPassed);
        returnToMain(scanner);
    }

    /*
     * Finds the ratio of employees to patients.
     * Converts from int to double - avoids CWE-681:Incorrect Conversion
     * between Numeric Types as no information is lost in this conversion.
     * @return double result from dividing size of employeeList by size of patientList 
     */
    private static double findRatio()
    {
        int numEmployees = employeeList.size(); 
        int numPatients = patientList.size(); 

        
        return (double) numEmployees / numPatients; 
    }





}//END: MAIN CLASS 

//CLASS: Person
abstract class Person{
    protected String name;
    protected int id;

    public Person(String name, int id){
        this.name = name;
        this.id = id;
    }
    public int getId() { return id; }
    public String getName() { return name; }

}

//CLASS: Employee
class Employee extends Person{
    public String name;
    public String position;
    public int id;
    private int pin;

    /*
     * constructor
     */
    public Employee(String name, String position, int id, int pin) {
        super(name, id);
        this.name = name; //prevents shadowing
        this.id = id; //prevents shadowing
        this.position = position;
        this.pin = pin;
    }

    // Getter methods - Avoids CWE-767: Access to Critical Private Variable in Public Method
    //by ensuring crticial information can only be accessed through private methods. 
    private String getPosition() { return position; }
    private int getPin(){
        return pin;
    }

    /*
     * comparePIN()
     */
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

    /*
     * toString
     */
    @Override
    public String toString() {
        return "Employee ID: " + id +", Name: " +name +", Position: " + position;
}


} //END: Employee

//CLASS: Patient
class Patient extends Person implements Cloneable {
    private String name;
    private int age;
    private int id;
    private Prescription med;

    public Patient(String name, int age, int id) {
        super(name, id);
        this.name = name; //prevents shadowing
        this.id = id; //prevents shadowing
        this.age = age;
        this.med = null;
    }

    public void createPrescription(ArrayList<String> medList){
        Prescription ml = new Prescription(medList);
        med = ml;
    }

    /*
     * toString
     */
    @Override
    public String toString() {
        return "Patient ID: " + id +", Name: " + name +", Age: " + age;
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
    private int getAge() { return age; }
    public Prescription getMedList() { return med; }



} //END: Patient

//CLASS: Prescription
class Prescription {
    // marked private so that internal state of medList is only modified as intended - CWE-607
    private static final ArrayList<String> medList = new ArrayList<>();

    public Prescription(ArrayList<String> medList){
        
    }

    public void addMedication(String med){
        medList.add(med);
       
    }

    public void exportMedList(){
        String content = "";
        ArrayList<String> ml = getMedList();

        for(int i = 0; i < ml.size(); i++){
            content = content + "\n" + ml.get(i);
        }
        try (FileWriter writer = new FileWriter("Prescription.txt")){
            writer.write(content);
            System.out.println("Prescription exported successfully");
        }catch (IOException e){
            System.out.println("Error Occured while exporting med list");
        }
    }
    /**
    * @return clone of medList
    */
    public ArrayList<String> getMedList(){
        //returns a defensive copy of medList CWE-375
        return new ArrayList<>(medList);
    }
}//END: Prescription

//CLASS: DaySystem
class DaySystem implements Runnable {
    private final String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private int dayIndex = 0;
    private static int totalDaysPassed = 0; //shared resource
    
    @Override
    public void run() {
        while (Thread.currentThread().isInterrupted() == false) {
            advanceDay();
            try {
                Thread.sleep(20000); //delay for 20 seconds before moving to the next day
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); //exit gracefully
            }
        }
    }

    public synchronized String getCurrentDay() {
        return days[dayIndex];
    }

    public static synchronized int getTotalDaysPassed() {
        return totalDaysPassed; //Safely retrieve total days passed
    }

    private synchronized void advanceDay() {
        dayIndex = (dayIndex + 1) % days.length;
        totalDaysPassed++; //Safely increment total days passed
    }

    public static synchronized void resetTotalDaysPassed() {
        totalDaysPassed = 0; //Safely reset total days passed
    }


} //END: DaySystem
