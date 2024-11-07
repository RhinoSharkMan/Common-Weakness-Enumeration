# Common-Weakness-Enumeration
Project in IT 355

----CWE's in Use:----

CHASE CONTENT
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
-this CWE is demonstated 

# CWE CASE 06: CWE-125: Out-of-Bounds Read 
-this CWE is demonstated 