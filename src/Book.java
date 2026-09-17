public record Book(String author, String title, String ISBN, int id, int pageCount) {

    @Override
    public String toString() {
        return author + "; " + title + "; Pages: " + pageCount + " | ISBN " + ISBN + " [ID: " + id +"]";
    }
}

