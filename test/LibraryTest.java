import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {


    //test loanResource()
    @Test
    void testSuccessfulLoan() {
        //Arrange
        Library library = new Library();
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
        Library library = new Library();
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
        Library library = new Library();
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
        Library library = new Library();
        int memberID = 111;
        String resourceID = "9781492072508";

        //Act
        library.loanResource(112, "9781492072508");
        LoanResult result = library.loanResource(memberID, resourceID);

        //Assert
        assertNotEquals(LoanResult.SUCCESS, result);
    }

}
