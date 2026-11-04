import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private List<Resource> resources;
    private List<Member> members;
    private List<Loan> loans;
    private LoanPolicy loanPolicy;
    private Library library;

    @BeforeEach
    void setup() {
        resources = new ArrayList<>();
        members = new ArrayList<>();
        loans = new ArrayList<>();

        loanPolicy = new StandardLoanPolicy();

        library = new Library(loanPolicy, resources, members, loans);

        members.add(new Member("Test", 111));
        resources.add(new Book("Test", "Test", 1, "1234"));
    }

    //test loanResource()
    @Test
    void testSuccessfulLoan() {
        //Act
        LoanResult result = library.loanResource(111, "1234");
        //Assert
        assertEquals(LoanResult.SUCCESS, result);
    }

    @Test
    void testAlreadyLoaned() {
        //Arrange
        members.add(new Member("Test2", 112));
        //Act
        library.loanResource(111, "1234");
        LoanResult result = library.loanResource(112, "1234");
        //Assert
        assertEquals(LoanResult.LOANED, result);
    }

    @Test
    public void testLibraryCallsCanLoan() {
        //Arrange
        TestDoubleLoanPolicy testDouble = new TestDoubleLoanPolicy();
        Library library = new Library(testDouble, resources, members, loans);

        //Act
        LoanResult result = library.loanResource(111, "1234");
        boolean canLoanCalled = testDouble.canLoanCalled;

        //Assert
        assertEquals(LoanResult.SUCCESS, result);
        assertTrue(canLoanCalled);
    }
}
