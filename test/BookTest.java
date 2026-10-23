import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    //test getCreator()
    @Test
    void testGetCreator() {
        //Arrange
        Book book = new Book ("Think Java",
                "Allan B. Downey",
                280,
                "9781492072508");
        //Act
        String creator = book.getCreator();
        //Assert
        assertEquals("Allan B. Downey", creator);
    }
    @Test
    void notTestGetCreator() {
        //Arrange
        Book book = new Book ("Think Java",
                "Allan B. Downey",
                280,
                "9781492072508");
        //Act
        String creator = book.getCreator();
        //Assert
        assertNotEquals("Hans Christian Andersen", creator);
    }

    //test getID()
    @Test
    void testGetID() {
        //Arrange
        Book book = new Book ("Think Java",
                "Allan B. Downey",
                280,
                "9781492072508");
        //Act
        String ID = book.getId();
        //Assert
        assertEquals("9781492072508", ID);
    }
    @Test
    void notTestGetID() {
        //Arrange
        Book book = new Book ("Think Java",
                "Allan B. Downey",
                280,
                "9781492072508");
        //Act
        String ID = book.getId();
        //Assert
        assertNotEquals("24523518812", ID);
    }

    //test getTitle()
    @Test
    void getTitle() {
        //Arrange
        Book book = new Book("Think Java",
                "Allan B. Downey",
                280,
                "9781492072508");
        //Act
        String title = book.getTitle();
        //Assert
        assertEquals("Think Java", title);
    }
    @Test
    void notGetTitle() {
        //Arrange
        Book book = new Book("Think Java",
                "Allan B. Downey",
                280,
                "9781492072508");
        //Act
        String title = book.getTitle();
        //Assert
        assertNotEquals("Dont Think Java", title);
    }

    //test getPageCount()
    @Test
    void getPageCount() {
        //Arrange
        Book book = new Book("Think Java",
                "Allan B. Downey",
                280,
                "9781492072508");
        //Act
        int pageCount = book.getPageCount();
        //Assert
        assertEquals(280, pageCount);
    }
    @Test
    void notGetPageCount() {
        //Arrange
        Book book = new Book("Think Java",
                "Allan B. Downey",
                280,
                "9781492072508");
        //Act
        int pageCount = book.getPageCount();
        //Assert
        assertNotEquals(180, pageCount);
    }
}
