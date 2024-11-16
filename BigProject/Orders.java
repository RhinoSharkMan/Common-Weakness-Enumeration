package BigProject;
import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Orders {

    private static Orders instance; // No volatile; ensure single-threaded init
    private static final ReentrantLock mutex = new ReentrantLock();
    
    // Menu for orders
    public void ordermenu(){
        System.out.println("OPTION 1: Order status ");
        System.out.println("OPTION 2: Find order ");
        System.out.println("OPTION 3: Add order ");
        System.out.println("OPTION 4: Create multiple orders");
        System.out.println("OPTION -1: EXIT ");
    }



    // Function to dem. CWE-192
    public void processOrder(short order) {
        if(checkDatabase(order)){
            System.out.println("Order: " + order + " has been processed.\n");
        }else{
            System.out.println("Order: " + order + " has not been processed yet.\n");
        }
    }



    // Function that finds if an order exists
    // Returns ordernum and if it was found or not
    public void findOrder(short order){
        if(checkDatabase(order)){
            System.out.println("Order: " + order + " was found.\n");
            return;
        }else{
            System.out.println("Order: " + order + " was not found.\n");
        }
    }



    // Function that validates short inputs
    // Returns true or false based on whether or not the value is a short
    public boolean validateShortInput(int order){
        // CWE-192 Integer Coercion Error and Error provention
        if ((order < Short.MIN_VALUE || order > Short.MAX_VALUE) || order == 0) {
            System.out.println("Not a valid order ID: " + order);
            return false;
        }else{
            return true;
        }
    }



    // Adds an order to the "database"
    // Throws IOException because it writes to a file
    public void addOrder(short order) throws IOException {
        String filePath = "BigProject\\orders.dat";
        if(checkDatabase(order)){
            System.out.println("Order: " + order + " already exists.");
            return;
        }
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write("\n" + String.valueOf(order));
            System.out.println("Order " + order + " was created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // CWE-572 Call to Thread run() instead of start()
    // Create multiple orders at once using threads
    public void createMultOrders(int numbOrdersToBeCreated, Scanner scan) throws IOException {
        for (int i = 0; i < numbOrdersToBeCreated; i++) {
            // Used to slow down display, otherwise print statments output before threads finish
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Enter order number: ");
            int userInput = inputVal(0, scan); // Validate input

            if (validateShortInput(userInput)) {
                short order = (short) userInput;

                // Create a new thread for each order
                Thread orderThread = new Thread(new OrderThread(order));
                orderThread.start(); // Use of start() instead of run()
            }
        }

        // Print statments would output before threads finished, uses sleep to wait until threads finish
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All orders have been added to the database.\n");
    }



    // Function that checks if an integer value will over flow or wraparound
    private static int inputVal(int input, Scanner scan){
        if (scan.hasNextInt() == true) {
            input = scan.nextInt();
            } 
        else {
            scan.next(); //clear the invalid input
            }
        return input;
    }


    // CWE-609
    public static Orders getInstance() {
        synchronized (Orders.class) {
            if (instance == null) { // Only initialize once
                instance = new Orders();
            }
        }
        return instance;
    }


    // Thread class for creating multiple orders
    public class OrderThread implements Runnable {
        private final short order;

        public OrderThread(short order) {
            this.order = order;
        }

        @Override
        public void run() {
            mutex.lock(); // CWE-366
            try {
                addOrder(order);
                System.out.println("Order: " + order + " successfully added by " + Thread.currentThread().getName());
            } catch (IOException e) {
                System.err.println("Error adding order: " + order);
                e.printStackTrace();
            } finally {
                mutex.unlock(); // CWE-366
            }
        }
    }


    
    // Call to a "database" to check and see if order has been processed
    private static boolean checkDatabase (short order){
        String filePath = "BigProject\\orders.dat";
        try (Scanner scan = new Scanner(new File(filePath))){
            while (scan.hasNext()) {
                if(scan.hasNextShort()){
                    short curr = scan.nextShort();
                    if(curr == order){return true;}
                }else{
                    scan.next();
                }
            }
            scan.close();
        }catch(FileNotFoundException e){
            System.err.println("File not found: " + filePath);
            
        }
        return false;

    }
}
