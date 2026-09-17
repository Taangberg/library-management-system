public record Book(String author, String title, String ISBN, int id, int pageCount) {

    @Override
    public String toString() {
        return author + "; " + title + "; ISBN " + ISBN + " Pages: " + pageCount + " ID: " + id;
    }
}

