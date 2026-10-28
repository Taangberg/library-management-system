import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StandardLoanPolicyTest {

    @Test
    void testCanLoan() {
        //Arrange
        LoanPolicy loanPolicy = new StandardLoanPolicy();
        Member member = new Member("Test", 111);
        Resource resource = new Book("Test book", "Test author", 200, "1111");
        List<Loan> loans = new ArrayList<>();

        //Act
        boolean result = loanPolicy.canLoan(member, resource, loans);

        //Assert
        assertTrue(result);
    }

    @Test
    void testCalculateDueDate() {
        //Arrange
        LoanPolicy loanPolicy = new StandardLoanPolicy();
        LocalDate borrowedDate = LocalDate.of(2026, 10, 28);
        LocalDate expectedDate = LocalDate.of(2026, 11, 27);
        Resource resource = new Book("Test book", "Test author", 200, "1111");
        //Act
        LocalDate actualDate = loanPolicy.calculateDueDate(borrowedDate, resource);

        //Assert
        assertEquals(expectedDate, actualDate);
    }

    @Test
    void testCalculateDueDateLeapYear() {
        //Arrange
        LoanPolicy loanPolicy = new StandardLoanPolicy();
        LocalDate borrowedDate = LocalDate.of(2028, 2, 3);
        LocalDate expectedDate = LocalDate.of(2028, 3, 4);
        Resource resource = new Book("Test book", "Test author", 200, "1111");
        //Act
        LocalDate actualDate = loanPolicy.calculateDueDate(borrowedDate, resource);

        //Assert
        assertEquals(expectedDate, actualDate);
    }
}
