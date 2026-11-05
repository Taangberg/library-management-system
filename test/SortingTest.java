import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortingTest {

    @Test
    void testResourceNaturalSorting() {
        //Arrange
        List<Resource> resources = new ArrayList<>();
        Book book = new Book("TestBook", "TestAuthor", 12, "3456");
        Movie movie = new Movie("TestMovie", "TestDirector", "TestStudio", "2hrs", "1234");
        Game game = new Game("TestGame", "TestPlatform", "TestGameStudio", "2345");
        resources.add(book);
        resources.add(movie);
        resources.add(game);

        //Act
        resources.sort(null);

        //Assert
        assertEquals(movie, resources.getFirst());
        assertSame(game,resources.get(1));
        assertEquals(book, resources.getLast());
    }

    @Test
    void testMemberNaturalSorting() {
        //Arrange
        List<Member> members = new ArrayList<>();
        Member memberTwo = new Member("2", 222);
        Member memberThree = new Member("3", 333);
        Member memberOne = new Member("1", 111);
        members.add(memberTwo);
        members.add(memberThree);
        members.add(memberOne);

        //Act
        members.sort(null);

        //Assert
        assertSame(memberOne,members.get(0));
        assertEquals(memberTwo,members.get(1));
        assertSame(memberThree,members.getLast());
    }

    @Test
    void testLoanNaturalSortingOrder() {
        //Arrange
        List<Loan> loans = new ArrayList<>();
        Member member = new Member("1", 111);
        Book book = new Book("TestBook", "TestAuthor", 12, "3456");
        Loan loanThree = new Loan(book,member, LocalDate.of(2026,11,4), LocalDate.of(2026,12,3));
        Loan loanOne = new Loan(book,member, LocalDate.of(2026,11,4), LocalDate.of(2026,11,13));
        Loan loanTwo = new Loan(book,member, LocalDate.of(2026,11,4), LocalDate.of(2026,12,1));
        loans.add(loanThree);
        loans.add(loanOne);
        loans.add(loanTwo);

        //Act
        loans.sort(null);

        //Assert
        assertSame(loanOne, loans.getFirst());
        assertSame(loanTwo,loans.get(1));
        assertSame(loanThree,loans.getLast());
    }

    @Test
    void testResourceCreatorComparator() {
        //Arrange
        List<Resource> resources = new ArrayList<>();
        Movie movie = new Movie("TestMovie", "Director", "Studio", "2hrs", "1234"); //Studio is creator
        Book book = new Book("TestBook", "Author", 12, "3456"); //Author is creator
        Game game = new Game("TestGame", "Platform", "GameStudio", "5678"); //Studio is creator
        Game game1 = new Game("GameTest", "Platform", "GameStudio", "2345"); //Studio is creator,
        resources.add(movie);
        resources.add(book);
        resources.add(game);
        resources.add(game1);

        //Act
        resources.sort(new ResourceCreatorComparator());

        //Assert
        assertSame(book, resources.getFirst());
        assertSame(game1,resources.get(1));
        assertSame(game,resources.get(2));
        assertSame(movie, resources.getLast());
    }

    @Test
    void testLoanLoanDateSortingOrder() {
        //Arrange
        List<Loan> loans = new ArrayList<>();
        Member member = new Member("1", 111);
        Book book = new Book("TestBook", "TestAuthor", 12, "3456");
        Loan loanFour = new Loan(book,member, LocalDate.of(2026,11,17), LocalDate.of(2026,12,3));
        Loan loanThree = new Loan(book,member, LocalDate.of(2026,11,12), LocalDate.of(2026,11,13));
        Loan loanTwo = new Loan(book,member, LocalDate.of(2026,11,10), LocalDate.of(2026,12,3));
        Loan loanOne = new Loan(book,member, LocalDate.of(2026,11,10), LocalDate.of(2026,12,2));
        loans.add(loanFour);
        loans.add(loanThree);
        loans.add(loanTwo);
        loans.add(loanOne);

        //Act
        loans.sort(new LoanBorrowDateComparator());

        //Assert
        assertSame(loanTwo, loans.getFirst()); //when same borrowdate, it puts sorts them by first added to the list
        assertSame(loanOne,loans.get(1));
        assertSame(loanThree,loans.get(2));
        assertSame(loanFour,loans.getLast());
    }
}
