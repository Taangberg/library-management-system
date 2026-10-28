import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class StandardLoanPolicyTest {

    @Test
    void testCanLoan() {
        //Arrange
        LoanPolicy loanPolicy = new StandardLoanPolicy();
        Member member = new Member("Test", 111);
        Resource resource = new Book("Test book", "Test author", 200 , "1111");

        //Act
        boolean result = loanPolicy.canLoan(member, resource);

        //Arrange
        assertTrue(result);
    }

    @Test
    void testNotEqualsCanLoan() {
        //Arrange
        LoanPolicy loanPolicy = new StandardLoanPolicy();
        Member member = new Member("Test", 111);
        Resource resource = new Book("Test book", "Test author", 200, "1111");

        //Act
        boolean result = loanPolicy.canLoan(member, resource);

        //Arrange
        assertNotEquals(false,result);
    }

    @Test
    void testCalculateDueDate() {
        //Arrange
        LoanPolicy loanPolicy = new StandardLoanPolicy();
        LocalDate borrowedDate = LocalDate.of(2026,10,28);
        LocalDate expectedDate = LocalDate.of(2026, 11, 27);

        //Act
        LocalDate actualDate = loanPolicy.calculateDueDate(borrowedDate);

        //Assert
        assertEquals(expectedDate,actualDate);
    }

    @Test
    void testNotEqualsCalculateDueDate() {
        //Arrange
        LoanPolicy loanPolicy = new StandardLoanPolicy();
        LocalDate borrowedDate = LocalDate.of(2026,10,28);
        LocalDate expectedDate = LocalDate.of(2026, 11, 28);

        //Act
        LocalDate actualDate = loanPolicy.calculateDueDate(borrowedDate);

        //Assert
        assertNotEquals(expectedDate,actualDate);
    }

}
