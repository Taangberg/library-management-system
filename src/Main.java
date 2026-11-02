void main(){
    LoanPolicy loanPolicy = new StandardLoanPolicy();

    String dir = "bibliotek";

    FileHandler fileHandler = new FileHandler(Path.of(dir), new CSVMapper());
    List<Resource> resources = new ArrayList<>();
    List<Loan> loans = new ArrayList<>();
    List<Member> members;

    // Resources
    List<AudioBook> audioBooks;
    List<Book> books;
    List<Game> games;
    List<Movie> movies = new ArrayList<>();

    // save to CSV
/*    try {
        fileHandler.saveAudioBooks(audioBooks);
        fileHandler.saveBooks(books);
        fileHandler.saveGames(games);
        fileHandler.saveMovies(movies);
        fileHandler.saveMembers(members);
        fileHandler.saveLoans(loans);
    } catch (IOException e) {
        IO.println(e.getLocalizedMessage());
    } */

    // load from CSV
    try {
        resources.addAll(fileHandler.loadAudioBooks());
        resources.addAll(fileHandler.loadBooks());
        resources.addAll(fileHandler.loadGames());
        resources.addAll(fileHandler.loadMovies());

        members.addAll(fileHandler.loadMembers());

        loans.addAll(fileHandler.loadLoans(members, resources));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    Library library = new Library(loanPolicy, resources, members, loans);
    ConsoleUI UI = new ConsoleUI(library);
    UI.run();
}