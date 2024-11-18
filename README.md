# Common-Weakness-Enumeration
Project in IT 355

----CWE's in Use:----

# # # CHASE CONTENT # # # 
# CWE-362: Concurrent Execution using Shared Resource with Improper Synchronization ('Race Condition')
-this CWE is demonstated through the synchronization that exists with the shared resource int totalDaysPassed. 
Modifications to this variable are protected via the synchronzied keyword

# CWE-20: Improper Input Validation
-this CWE is demonstated through the method public static int validateIntInput(int input, Scanner scanner)
which is a method that validates that the input gathered from the user is of type int

# CWE-334: Small Space of Random Values
-this CWE is demonstated upon PIN generation for the Employee objects. 
Done within the method generateRandomSixDigitNumber()

# CWE-582: Array Declared Public, Final, and Static
-this CWE is demonstated through the implemenation of the following array:
    * private final String[] days

# CWE-460: Improper Cleanup on Thrown Exception
-this CWE is demonstated in divideSupplies(). Specifically where the writer object is closed regardless of what logic 
is executed in the function 

# CWE CASE 06: CWE-125: Out-of-Bounds Read 
-this CWE is demonstated in the function cogTest() Specifically the user has the ability to compare a value via index
where the array set to a size of [7]. Because of this limiation, the function first validates that the user's input is 
in a valid range before accessing the array


# # # ANDREW CONTENT # # # 
# CWE-681: Incorrect Conversion between Numeric Types
-this CWE is demonstrated and avoided by only performing conversions when information is not lost. For example, an integer is converted to a double for a division operation. As this conversion does not lose information and is the intended operation of the program, this weakness is avoided. 

# CWE-628: Function Call with Incorrectly Specified Arguments
-this CWE is demonstrated and avoided by including a variety of validations for function arguments. Doing so ensures that the function works properly as all arguments are expected values.

# CWE-500: Public Static Field Not Marked Final
-this CWE is demonstrated and avoided by the inclusion of a public static variable that is marked final. This ensures that the globally accessable variable is a constant whos value cannot be changed after being set.

# CWE-484: Omitted Break Statement in Switch
-this CWE is demonstrated and avoided by including a break statement after each case in the switch block. Break statements allow for the program to properly exit the block once the case is completed, avoiding fallthrough behavior. 

# CWE-580: clone() method without super.clone()
-this CWE is demonstrated and avoided by ensuring the use of super.clone(). Patient objects can be cloned, and whenever cloning occurs super.clone() is called to ensure the object is of the correct type. 

# CWE-767: Access to Critical Private Variable via Public Method
-this CWE is demonstrated and avoided through preventing easy access to crticial values. Whenever these values are accessed or modified, it should be done in non public methods to prevent malicious users from modifying or accessing values without proper privileges.  

# # # MIKEY CONTENT # # #
# CWE-492
-This CWE is demonstrated and avoided by not using any inner classes to store sensitive data.

# CWE-248
-This CWE is demonstrated by including a catch statement to catch exceptions.

# CWE-607
-This CWE is demonstrated by marking fields private static final in order to avoid public access to 



# # # LIAM'S CONTECT # # #
# CWE-192: Integer Coercion Error
-This CWE is demonstrated by coercing an integer value to short to store in the order "database"

# CWE-190: Integer Overflow or Wraparound
-This CWE

# CWE-572: Call to Thread run() instead of start()
-This CWE is demonstrated by the DaySystem and OrderThread using start()
instead of run() to begin a new thread of control

# CWE-366: Race Condition within a Thread
-This CWE is demonstrated by OrderThread with the use of the mutex.lock()
taking advantage of the locking functionality by locking a thread and unlocking the thread when complete

# CWE-609: Double-Checked Locking
-This CWE is demonstrated in orders, Orders order Orders.getInstance(); to
check if orders is initialized.

# CWE-798: Use of Hard-coded Credentials
-This CWE is demonstrated in the employee class, by storing the employee pin
outside of the code and calling it when need



# # # Trevor's CONTENT # # #
# CWE-209 Generation of Error Message Containing Sensitive Information
-This CWE is demonstrated by avoiding the use of sensitive information in error messages.

# CWE-476 NULL Pointer Dereference
-This CWE is demonstrated by avoiding the use of NULL or performing checks to make sure NULL is avoided or acounted for.

# CWE-478 Missing Default Case in Multiple Condition Expression
-This CWE is demonstrated by using a default or base case when useing statements with multiple conditions.

# CWE-493 Critical Public Variable Without Final Modifier
-This CWE is demonstrated by making sure public variables have the final modifier attached to them.

# CWE-568 Finalize () Method Without super.finalize ()
-This CWE is demonstrated by using super.finalize ()

# CWE-583 Finalize method Declared public
-This CWE is demonstrated by using the protected variable and calling the variable to access the Finalize method

