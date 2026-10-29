import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {
    Resource resource;
    Member member;
    Loan loan;
    LocalDate borrowedDate = LocalDate.of(2026, 11, 1);
    LocalDate dueDate = LocalDate.of(2026, 12, 1);

    //method to create a run for the whole test
    @BeforeEach
    void setup() {
        resource = new Book("Think Java",
                "Allan B. Downey",
                280,
                "9781492072508");
        member = new Member("Thorkild Hansen", 111);
        loan = new Loan(resource, member, borrowedDate, dueDate);
    }

    //test getResource()
    @Test
    void testGetResource() {
        //Act
        Resource result = loan.resource();
        //Assert
        assertEquals(resource, result);
    }
    @Test
    void testNotEqualsGetResource() {
        //Act
        Resource result = loan.resource();
        //Assert
        assertNotEquals(member, result);
    }

    //test getMember()
    @Test
    void testGetMember() {
        //Act
        Member result = loan.member();
        //Assert
        assertEquals(member,result);
    }
    @Test
    void testNotEqualGetMember() {
        //Act
        Member result = loan.member();
        //Assert
        assertNotEquals(resource, result);
    }

    //test borrowedDate
    @Test
    void testGetBorrowedDate() {
        //Act
        LocalDate result = loan.borrowedDate();
        //Assert
        assertEquals(borrowedDate, result);
    }

    //test dueDate
    @Test
    void testDueDate() {
        //Act
        LocalDate result = loan.dueDate();
        //Assert
        assertEquals(dueDate, result);
    }
}
