public class Book extends Resource {
    final int pageCount;

    public Book(String title, String author, int pageCount, String ISBN) {
        super(title, author, ISBN);
        this.pageCount = pageCount;
    }

    public int getPageCount() { return pageCount; }

    @Override
    public String toString() {
        return title + ", by " + creator + "; Pages: " + pageCount + " | ISBN " + id;
    }
}

