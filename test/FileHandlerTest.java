import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class FileHandlerTest {

    //path
    @TempDir
    Path tempDir;

    //test af saveResources
    @Test
    void testSaveResources() throws IOException {
        //Arrange
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());
        List<Resource> resources = new ArrayList<>();
        resources.add(new Book("TestBookTitle", "TestBookAuthor", 1, "1234"));
        resources.add(new AudioBook("TestAudioBookTitle", "TestAudioBookAuthor", "1hrs", "2345"));
        resources.add(new Movie("TestMovieTitle", "TestMovieDirector", "TestMovieStudio", "2hrs", "3456"));
        resources.add(new Game("TestGameTitle", "TestGamePlatform", "TestGameStudio", "4567"));
        //Act
        fileHandler.saveResources(resources);

        Path bookFile = tempDir.resolve("books.csv");
        List<String> bookLines = Files.readAllLines(bookFile);

        Path audioBookFile = tempDir.resolve("audiobooks.csv");
        List<String> audioBooksLines = Files.readAllLines(audioBookFile);

        Path movieFile = tempDir.resolve("movies.csv");
        List<String> movieLines = Files.readAllLines(movieFile);

        Path gameFile = tempDir.resolve("games.csv");
        List<String> gameLines = Files.readAllLines(gameFile);

        //Assert
        assertEquals(1, bookLines.size());
        assertEquals("TestBookTitle,TestBookAuthor,1,1234", bookLines.getFirst());

        assertEquals(1, audioBooksLines.size());
        assertEquals("TestAudioBookTitle,TestAudioBookAuthor,1hrs,2345", audioBooksLines.getFirst());

        assertEquals(1, movieLines.size());
        assertEquals("TestMovieTitle,TestMovieDirector,TestMovieStudio,2hrs,3456", movieLines.getFirst());

        assertEquals(1, gameLines.size());
        assertEquals("TestGameTitle,TestGamePlatform,TestGameStudio,4567", gameLines.getFirst());
    }

    @Test
    void testMultipleSaveResources() throws IOException {
        //Arrange
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());
        List<Resource> resources = new ArrayList<>();
        resources.add(new Book("TestBookTitle", "TestBookAuthor", 1, "1234"));
        resources.add(new Book("TestBookTitle2", "TestBookAuthor2", 2, "5678"));
        resources.add(new AudioBook("TestAudioBookTitle", "TestAudioBookAuthor", "1hrs", "2345"));
        resources.add(new Movie("TestMovieTitle", "TestMovieDirector", "TestMovieStudio", "2hrs", "3456"));
        resources.add(new Game("TestGameTitle", "TestGamePlatform", "TestGameStudio", "4567"));

        //Act
        fileHandler.saveResources(resources);

        Path bookFile = tempDir.resolve("books.csv");
        List<String> bookLines = Files.readAllLines(bookFile);

        Path audioBookFile = tempDir.resolve("audiobooks.csv");
        List<String> audioBooksLines = Files.readAllLines(audioBookFile);

        Path movieFile = tempDir.resolve("movies.csv");
        List<String> movieLines = Files.readAllLines(movieFile);

        Path gameFile = tempDir.resolve("games.csv");
        List<String> gameLines = Files.readAllLines(gameFile);

        //Assert
        assertEquals(2, bookLines.size());
        assertEquals("TestBookTitle,TestBookAuthor,1,1234", bookLines.getFirst());
        assertEquals("TestBookTitle2,TestBookAuthor2,2,5678", bookLines.get(1));

        assertEquals(1, audioBooksLines.size());
        assertEquals("TestAudioBookTitle,TestAudioBookAuthor,1hrs,2345", audioBooksLines.getFirst());

        assertEquals(1, movieLines.size());
        assertEquals("TestMovieTitle,TestMovieDirector,TestMovieStudio,2hrs,3456", movieLines.getFirst());

        assertEquals(1, gameLines.size());
        assertEquals("TestGameTitle,TestGamePlatform,TestGameStudio,4567", gameLines.getFirst());
    }

    //test af members
    @Test
    void testSaveMember() throws IOException {
        //Arrange
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());
        List<Member> members = new ArrayList<>();
        members.add(new Member("Test Testensen", 111));

        //Act
        fileHandler.saveMembers(members);
        Path file = tempDir.resolve("members.csv");
        List<String> result = Files.readAllLines(file);

        //Assert
        assertEquals(1, result.size());
        assertEquals("Test Testensen,111", result.getFirst());
    }

    @Test
    void testLoadMember() throws IOException {
        //Arrange
        Path file = tempDir.resolve("members.csv");
        Files.writeString(file, "Test Testensen,111");
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());

        //Act
        List<Member> result = fileHandler.loadMembers();

        //Assert
        assertEquals(1, result.size());
        assertEquals("Test Testensen", result.getFirst().getName());
        assertEquals(111, result.getFirst().getId());
    }

    //test af books
    @Test
    void testSaveBooks() throws IOException {
        //Arrange
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());
        List<Book> books = new ArrayList<>();
        books.add(new Book("TestTitle", "TestAuthor", 1, "1234"));
        books.add(new Book("TestTitle2", "TestAuthor2", 2, "2345"));

        //Act
        fileHandler.saveBooks(books);
        Path file = tempDir.resolve("books.csv");
        List<String> result = Files.readAllLines(file);

        //Assert
        assertEquals(2, result.size());
        assertEquals("TestTitle,TestAuthor,1,1234", result.getFirst());
        assertEquals("TestTitle2,TestAuthor2,2,2345", result.get(1));
    }

    @Test
    void testLoadBooks() throws IOException {
        //Arrange
        Path file = tempDir.resolve("books.csv");
        Files.writeString(file, "TestTitle,TestAuthor,1,1234\n" + "TestTitle2,TestAuthor2,2,2345");
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());

        //Act
        List<Book> result = fileHandler.loadBooks();

        //Assert
        assertEquals(2, result.size());
        assertEquals("TestTitle", result.getFirst().getTitle());
        assertEquals("TestAuthor2", result.get(1).getCreator());
        assertEquals("2345", result.get(1).getId());
    }

    //test af audioBooks
    @Test
    void testSaveAudioBooks() throws IOException {
        //Arrange
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());
        List<AudioBook> audioBooks = new ArrayList<>();
        audioBooks.add(new AudioBook("TestTitle", "TestAuthor", "1hrs", "1234"));
        audioBooks.add(new AudioBook("TestTitle2", "TestAuthor2", "2hrs", "2345"));

        //Act
        fileHandler.saveAudioBooks(audioBooks);
        Path file = tempDir.resolve("audiobooks.csv");
        List<String> result = Files.readAllLines(file);

        //Assert
        assertEquals(2, result.size());
        assertEquals("TestTitle,TestAuthor,1hrs,1234", result.getFirst());
        assertEquals("TestTitle2,TestAuthor2,2hrs,2345", result.get(1));
    }

    @Test
    void testLoadAudioBooks() throws IOException {
        //Arrange
        Path file = tempDir.resolve("audiobooks.csv");
        Files.writeString(file, "TestTitle,TestAuthor,1hrs,1234\n" + "TestTitle2,TestAuthor2,2hrs,2345");
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());

        //Act
        List<AudioBook> result = fileHandler.loadAudioBooks();

        //Assert
        assertEquals(2, result.size());
        assertEquals("TestTitle", result.getFirst().getTitle());
        assertEquals("TestAuthor2", result.get(1).getCreator());
        assertEquals("2345", result.get(1).getId());
    }

    //test af movies
    @Test
    void testSaveMovies() throws IOException {
        //Arrange
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("TestMovieTitle", "TestMovieDirector", "TestMovieStudio", "2hrs", "3456"));
        movies.add(new Movie("TestMovieTitle2", "TestMovieDirector2", "TestMovieStudio2", "3hrs", "4567"));

        //Act
        fileHandler.saveMovies(movies);
        Path file = tempDir.resolve("movies.csv");
        List<String> result = Files.readAllLines(file);

        //Assert
        assertEquals(2, result.size());
        assertEquals("TestMovieTitle,TestMovieDirector,TestMovieStudio,2hrs,3456", result.getFirst());
        assertEquals("TestMovieTitle2,TestMovieDirector2,TestMovieStudio2,3hrs,4567", result.get(1));
    }

    @Test
    void testLoadMovies() throws IOException {
        //Arrange
        Path file = tempDir.resolve("movies.csv");
        Files.writeString(file, "TestMovieTitle,TestMovieDirector,TestMovieStudio,2hrs,3456\n" + "TestMovieTitle2,TestMovieDirector2,TestMovieStudio2,3hrs,4567");
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());

        //Act
        List<Movie> result = fileHandler.loadMovies();

        //Assert
        assertEquals(2, result.size());
        assertEquals("TestMovieTitle", result.getFirst().getTitle());
        assertEquals("TestMovieDirector2", result.get(1).getDirector());
        assertEquals("4567",result.get(1).getId());
    }

    //test af games
    @Test
    void testSaveGames() throws IOException {
        //Arrange
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());
        List<Game> games = new ArrayList<>();
        games.add(new Game("TestGameTitle", "TestGamePlatform", "TestGameStudio", "4567"));
        games.add(new Game("TestGameTitle2", "TestGamePlatform2", "TestGameStudio2", "5678"));

        //Act
        fileHandler.saveGames(games);
        Path file = tempDir.resolve("games.csv");
        List<String> result = Files.readAllLines(file);

        //Assert
        assertEquals(2, result.size());
        assertEquals("TestGameTitle,TestGamePlatform,TestGameStudio,4567", result.getFirst());
        assertEquals("TestGameTitle2,TestGamePlatform2,TestGameStudio2,5678", result.get(1));
    }

    @Test
    void testLoadGames() throws IOException {
        //Arrange
        Path file = tempDir.resolve("games.csv");
        Files.writeString(file, "TestGameTitle,TestGamePlatform,TestGameStudio,4567\n" + "TestGameTitle2,TestGamePlatform2,TestGameStudio2,5678");
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());

        //Act
        List<Game> result = fileHandler.loadGames();

        //Assert
        assertEquals(2, result.size());
        assertEquals("TestGameTitle", result.getFirst().getTitle());
        assertEquals("TestGameStudio2", result.get(1).getCreator());
        assertEquals("5678",result.get(1).getId());
    }

    //test af loans
    @Test
    void testSaveLoans() throws IOException {
        //Arrange
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());
        Resource resource = new Book("TestTitle", "TestAuthor", 1, "1234");
        Member member = new Member("Test Testensen", 111);
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(resource, member, LocalDate.of(2026, 11, 4), LocalDate.of(2026, 12, 3)));

        //Act
        fileHandler.saveLoans(loans);
        Path file = tempDir.resolve("loans.csv");
        List<String> result = Files.readAllLines(file);

        //Assert
        assertEquals(1, result.size());
        assertEquals("1234,111,2026-11-04,2026-12-03", result.getFirst());
    }

    @Test
    void testLoadLoans() throws IOException {
        //Arrange
        Path file = tempDir.resolve("loans.csv");
        Files.writeString(file,"1234,111,2026-11-04,2026-12-03");
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());
        List<Member> members = List.of(new Member("Test Testensen", 111));
        List<Resource> resources = List.of(new Book("Test", "Test", 1, "1234"));


        //Act
        List<Loan> result = fileHandler.loadLoans(members,resources);

        //Assert
        assertEquals(1, result.size());
        assertEquals("1234", result.getFirst().getResource().getId());
        assertEquals(111,result.getFirst().getMember().getId());
        assertEquals(LocalDate.of(2026,11,4),result.getFirst().getBorrowDate());
        assertEquals(LocalDate.of(2026,12,3),result.getFirst().getDueDate());
    }
}
