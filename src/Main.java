void main(String[] args){

    // Library choice step; selects library filepath and language preference
    String choice = login();

    FileHandler fileHandler = new FileHandler(Path.of("bibliotek/"+choice), new CSVMapper());
    LoanPolicy loanPolicy = switch (choice) {
        case "gentofte-bibliotek" -> new StandardLoanPolicy();
        case "malmö-bibliotek"    -> new StandardLoanPolicy();
        case "svaneke-bibliotek"  -> new BornholmLoanPolicy();
        default                   -> new StandardLoanPolicy();
    };
    Locale locale = switch (choice) {
        case "gentofte-bibliotek" -> Locale.forLanguageTag("da");
        case "malmö-bibliotek"    -> Locale.forLanguageTag("sv");  // <- doesnt exist
        case "svaneke-bibliotek"  -> Locale.forLanguageTag("da");
        default                   -> Locale.forLanguageTag("da");
    };

    // Library setup step; creates necessary content arrays for library
    List<Resource> resources = new ArrayList<>();
    List<Loan> loans = new ArrayList<>();
    List<Member> members = new ArrayList<>();

    // load from CSV; fills library with contents from chosen library's .csv files
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

    // sort array; sorts the array by:
    resources.sort(null);   // title
    members.sort(null);     // memberId
    loans.sort(null);       // borrowDate
    /* then the consoleUI sorts resources by type during prints, so we end up with
       books      A -> Å
       audiobooks A -> Å
       movies     A -> Å
       games      A -> Å
     */

    // Completion step; finally consolidates all the information and runs the program
    Library library = new Library(loanPolicy, resources, members, loans);
    ConsoleUI UI = new ConsoleUI(library, locale);
    UI.run();

    // save to CSV; saves any backend additions to library (likely won't be used again)
  try {
        fileHandler.saveResources(resources);
        fileHandler.saveMembers(members);
        fileHandler.saveLoans(loans);
    } catch (IOException e) {
        IO.println(e.getLocalizedMessage());
    }
}

String login() {
    String RED = "\u001B[31m";
    String WHI = "\u001B[0m";
    boolean running = true;
    while (running) {
        showLogin();
        try {
            int choice = Integer.parseInt(IO.readln("Input library choice: "));
            switch (choice) {
                case 1 -> { return "gentofte-bibliotek"; }
                case 2 -> { return "svaneke-bibliotek"; }
                default -> IO.println(RED + "Invalid input. Try again" + WHI);
            }
        } catch (NumberFormatException e) {
            IO.println(RED + "Invalid input. Must be a number \nTry again \n" + WHI);
        }
    }
    return null;
}

public void showLogin() {
    IO.println();
    IO.println("| 1. Gentofte bibliotek");
    IO.println("| 2. Svaneke bibliotek");
    IO.println();
}
