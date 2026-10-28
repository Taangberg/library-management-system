import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AalborgLoanPolicyTest {

    @Test
    void testCanLoan() {
        //Arrange
        LoanPolicy loanPolicy = new AalborgLoanPolicy();
        Member member = new Member("test", 11);
        Resource movie1 = new Movie("test1", "test1", "test1", "test1", "test1");
        List<Loan> loans = new ArrayList<>();
        //Act
        boolean result = loanPolicy.canLoan(member, movie1, loans);
        //Assert
        assertTrue(result);
    }

    @Test
    void testCanLoanTwoMovies() {
        //Arrange
        LoanPolicy loanPolicy = new AalborgLoanPolicy();
        Member member = new Member("test", 11);
        Resource movie1 = new Movie("test1", "test1", "test1", "test1", "test1");
        Resource movie2 = new Movie("test2", "test2", "test2", "test2", "test2");
        Loan loan1 = new Loan(movie1, member, LocalDate.now(), LocalDate.now().plusDays(30));
        ArrayList<Loan> loans = new ArrayList<>();
        loans.add(loan1);

        //Act
        boolean result = loanPolicy.canLoan(member, movie2, loans);

        //Assert
        assertTrue(result);
    }

    @Test
    void testCanLoanFourMoviesOrGames() {
        //Arrange
        LoanPolicy loanPolicy = new AalborgLoanPolicy();
        Member member = new Member("test", 11);
        Resource movie1 = new Movie("test1", "test1", "test1", "test1", "test1");
        Resource movie2 = new Movie("test2", "test2", "test2", "test2", "test2");
        Resource game1 = new Game("test3", "test3", "test3", "test3");
        Resource game2 = new Game("test4", "test4", "test4", "test4");
        Loan loan1 = new Loan(movie1, member, LocalDate.now(), LocalDate.now().plusDays(30));
        Loan loan2 = new Loan(movie2, member, LocalDate.now(), LocalDate.now().plusDays(30));
        Loan loan3 = new Loan(game1, member, LocalDate.now(), LocalDate.now().plusDays(30));
        ArrayList<Loan> loans = new ArrayList<>();
        loans.addAll(Arrays.asList(loan1, loan2, loan3));

        //Act
        boolean result = loanPolicy.canLoan(member, game2, loans);

        //Assert
        assertFalse(result);

    }

    @Test
    void testCanLoanThreeMovies() {
        //Arrange
        LoanPolicy loanPolicy = new AalborgLoanPolicy();
        Member member = new Member("test", 11);
        Resource movie1 = new Movie("test1", "test1", "test1", "test1", "test1");
        Resource movie2 = new Movie("test2", "test2", "test2", "test2", "test2");
        Resource movie3 = new Movie("test3", "test3", "test3", "test3", "test3");
        Loan loan1 = new Loan(movie1, member, LocalDate.now(), LocalDate.now().plusDays(30));
        Loan loan2 = new Loan(movie2, member, LocalDate.now(), LocalDate.now().plusDays(30));
        ArrayList<Loan> loans = new ArrayList<>();
        loans.addAll(Arrays.asList(loan1, loan2));

        //Act
        boolean result = loanPolicy.canLoan(member, movie3, loans);

        //Assert
        assertTrue(result);

    }

    @Test
    void testCanLoanThreeMovieAndABook() {
        //Arrange
        LoanPolicy loanPolicy = new AalborgLoanPolicy();
        Member member = new Member("test", 11);
        Resource movie1 = new Movie("test1", "test1", "test1", "test1", "test1");
        Resource movie2 = new Movie("test2", "test2", "test2", "test2", "test2");
        Resource movie3 = new Movie("test3", "test3", "test3", "test3", "test3");
        Resource book = new Book("test", "test", 200, "test");
        Loan loan1 = new Loan(movie1, member, LocalDate.now(), LocalDate.now().plusDays(30));
        Loan loan2 = new Loan(movie2, member, LocalDate.now(), LocalDate.now().plusDays(30));
        Loan loan3 = new Loan(movie3, member, LocalDate.now(), LocalDate.now().plusDays(30));
        ArrayList<Loan> loans = new ArrayList<>();
        loans.addAll(Arrays.asList(loan1, loan2, loan3));

        //Act
        boolean result = loanPolicy.canLoan(member, book, loans);

        //Assert
        assertTrue(result);

    }

    @Test
    void testOneMemberReachedLimitAnotherTryingToLoan() {
        //Arrange
        LoanPolicy loanPolicy = new AalborgLoanPolicy();
        Member member1 = new Member("test1", 11);
        Member member2 = new Member("test2", 12);
        Resource movie1 = new Movie("test1", "test1", "test1", "test1", "test1");
        Resource movie2 = new Movie("test2", "test2", "test2", "test2", "test2");
        Resource game1 = new Game("test3", "test3", "test3", "test3");
        Resource game2 = new Game("test4", "test4", "test4", "test4");
        Loan loan1 = new Loan(movie1, member1, LocalDate.now(), LocalDate.now().plusDays(30));
        Loan loan2 = new Loan(movie2, member1, LocalDate.now(), LocalDate.now().plusDays(30));
        Loan loan3 = new Loan(game1, member1, LocalDate.now(), LocalDate.now().plusDays(30));
        ArrayList<Loan> loans = new ArrayList<>();
        loans.addAll(Arrays.asList(loan1, loan2, loan3));

        //Act
        boolean result = loanPolicy.canLoan(member2, game2, loans);

        //Assert
        assertTrue(result);
    }
}
