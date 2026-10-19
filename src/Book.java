public class Book extends Resource {
    String ISBN;
    int pageCount;

    public Book(String creator, String title, int pageCount, String ISBN, int id) {
        super(creator, title, id);
        this.ISBN = ISBN;
        this.pageCount = pageCount;
    }

    public String getISBN() { return ISBN; }

    public int getPageCount() { return pageCount; }

    public String toString() {
        return title + ", by " + creator + "; Pages: " + pageCount + " | ISBN " + ISBN + " [ID: " + id + "]";
    }
}

