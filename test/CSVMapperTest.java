import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVMapperTest {

    //Test af members
    @Test
    void testMemberToCSV() {
        //Arrange
        CSVMapper mapper = new CSVMapper();
        Member member = new Member("Test Testensen", 111);

        //Act
        String result = mapper.memberToCSV(member);

        //Assert
        assertEquals("Test Testensen,111", result);
    }

    @Test
    void testMemberFromCSV() {
        //Arrange
        CSVMapper mapper = new CSVMapper();
        String line = "Test Testensen,111";

        //Act
        Member member = mapper.memberFromCSV(line);

        //Assert
        assertEquals("Test Testensen", member.getName());
        assertEquals(111, member.getId());
    }

    //Test af books
    @Test
    void testBooksToCSV() {
        //Arrange
        CSVMapper mapper = new CSVMapper();
        Book book = new Book("Test", "Test", 1, "1234");

        //Act
        String result = mapper.bookToCSV(book);

        //Assert
        assertEquals("Test,Test,1,1234", result);
    }

    @Test
    void testBooksFromCSV() {
        //Arrange
        CSVMapper mapper = new CSVMapper();
        String line = "TestTitle,TestAuthor,1,1234";

        //Act
        Book book = mapper.bookFromCSV(line);

        //Assert
        assertEquals("TestTitle", book.getTitle());
        assertEquals("TestAuthor", book.getCreator());
        assertEquals(1, book.getPageCount());
        assertEquals("1234", book.getId());
    }

    //Test af audioBooks
    @Test
    void testAudioBooksToCSV() {
        //Arrange
        CSVMapper mapper = new CSVMapper();
        AudioBook audioBook = new AudioBook("Test", "Test", "1hrs", "1234");

        //Act
        String result = mapper.audioBookToCSV(audioBook);

        //Assert
        assertEquals("Test,Test,1hrs,1234", result);
    }

    @Test
    void testAudioBooksFromCSV() {
        //Arrange
        CSVMapper mapper = new CSVMapper();
        String line = "TestTitle,TestAuthor,TestLength,1234";

        //Act
        AudioBook audioBook = mapper.audioBookFromCSV(line);

        //Assert
        assertEquals("TestTitle", audioBook.getTitle());
        assertEquals("TestAuthor", audioBook.getCreator());
        assertEquals("TestLength", audioBook.getRuntime());
        assertEquals("1234", audioBook.getId());
    }

    //Test af movies
    @Test
    void testMoviesToCSV() {
        //Arrange
        CSVMapper mapper = new CSVMapper();
        Movie movie = new Movie("TestTitle", "TestDirector", "TestStudio", "1hrs", "1234");

        //Act
        String result = mapper.movieToCSV(movie);

        //Assert
        assertEquals("TestTitle,TestDirector,TestStudio,1hrs,1234", result);
    }

    @Test
    void testMoviesFromCSV() {
        //Arrange
        CSVMapper mapper = new CSVMapper();
        String line = "TestTitle,TestDirector,TestStudio,1hrs,1234";

        //Act
        Movie movie = mapper.movieFromCSV(line);

        //Assert
        assertEquals("TestTitle", movie.getTitle());
        assertEquals("TestDirector", movie.getDirector());
        assertEquals("TestStudio", movie.getCreator());
        assertEquals("1hrs", movie.getRuntime());
        assertEquals("1234", movie.getId());
    }

    @Test
    void testLoanToCsv() {
        //Arrange
        CSVMapper mapper = new CSVMapper();
        Resource resource = new Book("Test", "Test", 1, "1234");
        Member member = new Member("Test Testensen", 111);
        Loan loan = new Loan(resource, member, LocalDate.of(2026, 11, 4), LocalDate.of(2026, 12, 3));

        //Act
        String result = mapper.loanToCSV(loan);

        //Assert
        assertEquals("1234,111,2026-11-04,2026-12-03", result);
    }

    @Test
    void testLoanFromCsv() {
        //Arrange
        List<Resource> resources = new ArrayList<>();
        List<Member> members = new ArrayList<>();
        Book book = new Book("Test", "Test", 1, "1234");
        Member member = new Member("Test Testensen", 111);
        resources.add(book);
        members.add(member);

        CSVMapper mapper = new CSVMapper();
        String line = "1234,111,2026-11-04,2026-12-03";

        //Act
        Loan loan = mapper.loanFromCSV(line, members, resources);

        //Assert
        assertNotNull(loan);
        assertEquals(111, loan.getMember().getId());
        assertEquals("1234", loan.getResource().getId());
        assertEquals(LocalDate.of(2026, 11, 4), loan.getBorrowDate());
        assertEquals(LocalDate.of(2026, 12, 3), loan.getDueDate());
        assertSame(book, loan.getResource());
        assertSame(member, loan.getMember());
    }
}
