import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    //test getCreator()
    @Test
    void getCreator() {
        //Arrange
        Movie movie = new Movie("No Time To Die",
                "Cary Fukunaga",
                "Amazon MGM",
                "2,37",
                "5053083215408");
        //Act
        String creator = movie.getCreator();
        //Assert
        assertEquals("Amazon MGM", creator);
    }
    @Test
    void notGetCreator() {
        //Arrange
        Movie movie = new Movie("No Time To Die",
                "Cary Fukunaga",
                "Amazon MGM",
                "2,37",
                "5053083215408");
        //Act
        String creator = movie.getCreator();
        //Assert
        assertNotEquals("Universal", creator);
    }

    //test getID()
    @Test
    void getID() {
        //Arrange
        Movie movie = new Movie("No Time To Die",
                "Cary Fukunaga",
                "Amazon MGM",
                "2,37",
                "5053083215408");
        //Act
        String ID = movie.getId();
        //Assert
        assertEquals("5053083215408", ID);
    }
    @Test
    void notGetID() {
        //Arrange
        Movie movie = new Movie("No Time To Die",
                "Cary Fukunaga",
                "Amazon MGM",
                "2,37",
                "5053083215408");
        //Act
        String ID = movie.getId();
        //Assert
        assertNotEquals("5053083214932", ID);
    }

    //test getTitle()
    @Test
    void getTitle() {
       //Arrange
       Movie movie = new Movie("No Time To Die",
               "Cary Fukunaga",
               "Amazon MGM",
               "2,37",
               "5053083215408");
       //Act
       String title = movie.getTitle();
       //Assert
       assertEquals("No Time To Die", title);
   }
    @Test
    void notGetTitle() {
        //Arrange
        Movie movie = new Movie("No Time To Die",
                "Cary Fukunaga",
                "Amazon MGM",
                "2,37",
                "5053083215408");
        //Act
        String title = movie.getTitle();
        //Assert
        assertNotEquals("Spectre", title);
    }

    //test getDirector()
    @Test
    void getDirector() {
        //Arrange
        Movie movie = new Movie("No Time To Die",
                "Cary Fukunaga",
                "Amazon MGM",
                "2,37",
                "5053083215408");
        //Act
        String director = movie.getDirector();
        //Assert
        assertEquals("Cary Fukunaga", director);
    }
    @Test
    void notGetDirector() {
        //Arrange
        Movie movie = new Movie("No Time To Die",
                "Cary Fukunaga",
                "Amazon MGM",
                "2,37",
                "5053083215408");
        //Act
        String director = movie.getDirector();
        //Assert
        assertNotEquals("Mickey Mouse", director);
    }

    //test getRuntime()
    @Test
    void getRuntime() {
        //Assert
        Movie movie = new Movie("No Time To Die",
                "Cary Fukunaga",
                "Amazon MGM",
                "2,37",
                "5053083215408");
        //Act
        String runtime = movie.getRuntime();
        //Assert
        assertEquals("2,37", runtime);
    }
    @Test
    void notGetRuntime() {
        //Assert
        Movie movie = new Movie("No Time To Die",
                "Cary Fukunaga",
                "Amazon MGM",
                "2,37",
                "5053083215408");
        //Act
        String runtime = movie.getRuntime();
        //Assert
        assertNotEquals("2.37", runtime);
    }
}
