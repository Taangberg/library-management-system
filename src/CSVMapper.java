public class CSVMapper {
    private String d = ","; // delimiter

    // ___________Member___________
    public String memberToCSV(Member member) {
        return member.getName()
                + d + member.getId();
    }

    public Member memberFromCSV(String line) {
        String[] tokens = line.split(d, -1);
        return new Member(tokens[0], Integer.parseInt(tokens[1]));
    }

    // ___________Book___________
    public String bookToCSV(Book book) {
        return book.getTitle()
                + d + book.getCreator()
                + d + book.getPageCount()
                + d + book.getId();
    }

    public Book bookFromCSV(String line) {
        String[] tokens = line.split(d, -1);
        return new Book(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3]);
    }

    // ___________AudioBook___________
    public String audioBookToCSV(AudioBook audioBook) {
        return audioBook.getTitle()
                + d + audioBook.getCreator()
                + d + audioBook.getRuntime()
                + d + audioBook.getId();
    }

    public AudioBook audioBookFromCSV(String line) {
        String[] tokens = line.split(d, -1);
        return new AudioBook(tokens[0], tokens[1], tokens[2], tokens[3]);
    }

    // ___________Movie___________
    public String movieToCSV(Movie movie) {
        return movie.getTitle()
                + d + movie.getDirector()
                + d + movie.getCreator()
                + d + movie.getRuntime()
                + d + movie.getId();
    }

    public Movie movieFromCSV(String line) {
        String[] tokens = line.split(d, -1);
        return new Movie(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
    }

    // ___________Game___________
    public String gameToCSV(Game game) {
        return game.getTitle()
                + d + game.getPlatform()
                + d + game.getCreator()
                + d + game.getId();
    }

    public Game gameFromCSV(String line) {
        String[] tokens = line.split(d, -1);
        return new Game(tokens[0], tokens[1], tokens[2], tokens[3]);
    }

    // ___________Loan___________
    public String loanToCSV(Loan loan) {
        return loan.getResource().getId()
                + d + loan.getMember().getId()
                + d + loan.getBorrowDate()
                + d + loan.getDueDate();
    }

    public Loan loanFromCSV(String line) {
        String[] tokens = line.split(d, -1);
        return new Loan(tokens[0], tokens[1], tokens[2], tokens[3]);
    }   // I dont know how to parse this
}
