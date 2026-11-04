import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class FileHandlerTest {

    //path
    @TempDir
    Path tempDir;

    //test af saveResources
    @Test
    void testSaveResources() throws IOException{
        //Arrange
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());
        List<Resource> resources = new ArrayList<>();
        resources.add(new Book("TestBookTitle", "TestBookAuthor", 1, "1234"));
        resources.add(new AudioBook("TestAudioBookTitle", "TestAudioBookAuthor", "1hrs", "2345"));
        resources.add(new Movie("TestMovieTitle", "TestMovieDirector", "TestMovieStudio", "2hrs", "3456"));
        resources.add(new Game("TestGameTitle","TestGamePlatform","TestGameStudio","4567"));
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
        assertEquals(1,bookLines.size());
        assertEquals("TestBookTitle,TestBookAuthor,1,1234", bookLines.getFirst());

        assertEquals(1, audioBooksLines.size());
        assertEquals("TestAudioBookTitle,TestAudioBookAuthor,1hrs,2345", audioBooksLines.getFirst());

        assertEquals(1,movieLines.size());
        assertEquals("TestMovieTitle,TestMovieDirector,TestMovieStudio,2hrs,3456", movieLines.getFirst());

        assertEquals(1, gameLines.size());
        assertEquals("TestGameTitle,TestGamePlatform,TestGameStudio,4567", gameLines.getFirst());
    }

    @Test
    void testMultipleSaveResources() throws IOException{
        //Arrange
        FileHandler fileHandler = new FileHandler(tempDir, new CSVMapper());
        List<Resource> resources = new ArrayList<>();
        resources.add(new Book("TestBookTitle", "TestBookAuthor", 1, "1234"));
        resources.add(new Book("TestBookTitle2", "TestBookAuthor2", 2, "5678"));
        resources.add(new AudioBook("TestAudioBookTitle", "TestAudioBookAuthor", "1hrs", "2345"));
        resources.add(new Movie("TestMovieTitle", "TestMovieDirector", "TestMovieStudio", "2hrs", "3456"));
        resources.add(new Game("TestGameTitle","TestGamePlatform","TestGameStudio","4567"));

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
        assertEquals(2,bookLines.size());
        assertEquals("TestBookTitle,TestBookAuthor,1,1234", bookLines.getFirst());
        assertEquals("TestBookTitle2,TestBookAuthor2,2,5678",bookLines.get(1));

        assertEquals(1, audioBooksLines.size());
        assertEquals("TestAudioBookTitle,TestAudioBookAuthor,1hrs,2345", audioBooksLines.getFirst());

        assertEquals(1,movieLines.size());
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
        assertEquals("2345",result.get(1).getId());
    }
}
