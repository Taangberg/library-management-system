import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private final String author;
    private final String title;
    private final String ISBN;
    private final int id;
    private final int pages;
    Member borrowedBy;


    public Book(String author, String title, String ISBN, int id, int pages) {
        this.author = author;
        this.title = title;
        this.ISBN = ISBN;
        this.id = id;
        this.pages = pages;
    }


    public Book(String author, String title, String ISBN, int id) {
        this.author = author;
        this.title = title;
        this.ISBN = ISBN;
        this.id = id;
        pages = 0;
    }

    public String toString() {
        return author + "; " + title + "; ISBN " + ISBN  + " Pages: " + pages + " ID: " + id;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == null || getClass() != otherObj.getClass()) return false;
        Book book = (Book) otherObj;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(ISBN, book.ISBN) && id == book.id;
    }
    public int getId(){
        return id;
    }
}

