import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {


    //test loanResource()
    @Test
    void testSuccessfulLoan() {
        //Arrange
        LoanPolicy loanPolicy = new StandardLoanPolicy();
        Library library = new Library(loanPolicy);
        int memberID = 111;
        String resourceID = "9781492072508";
        //Act
        LoanResult result = library.loanResource(memberID, resourceID);
        //Assert
        assertEquals(LoanResult.SUCCESS, result);
    }

    @Test
    void testNotEqualsSuccessfulLoan() {
        //Arrange
        LoanPolicy loanPolicy = new StandardLoanPolicy();
        Library library = new Library(loanPolicy);
        int memberID = 111;
        String resourceID = "9781492072508";
        //Act
        LoanResult result = library.loanResource(memberID, resourceID);
        //Assert
        assertNotEquals(LoanResult.LOANED, result);
    }

    @Test
    void testAlreadyLoaned() {
        //Arrange
        LoanPolicy loanPolicy = new StandardLoanPolicy();
        Library library = new Library(loanPolicy);
        int memberID = 111;
        String resourceID = "9781492072508";
        //Act
        library.loanResource(112, "9781492072508");
        LoanResult result = library.loanResource(memberID, resourceID);
        //Assert
        assertEquals(LoanResult.LOANED, result);
    }

    @Test
    void testNotEqualsAlreadyLoaned() {
        //Arrange
        LoanPolicy loanPolicy = new StandardLoanPolicy();
        Library library = new Library(loanPolicy);
        int memberID = 111;
        String resourceID = "9781492072508";

        //Act
        library.loanResource(112, "9781492072508");
        LoanResult result = library.loanResource(memberID, resourceID);

        //Assert
        assertNotEquals(LoanResult.SUCCESS, result);
    }

    @Test
    public void testLibraryCallsCanLoan() {
        //Arrange
        TestDoubleLoanPolicy testDouble = new TestDoubleLoanPolicy();
        Library library = new Library(testDouble);

        //Act
        LoanResult result = library.loanResource(112, "9781492072508");
        boolean canLoanCalled = testDouble.canLoanCalled;

        //Assert
        assertEquals(LoanResult.SUCCESS, result);
        assertTrue(canLoanCalled);
    }
}
