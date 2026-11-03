void main(String[] args){
    // spørg om det er svaneke eller gentofte, og lav derefter 2 file handlers som enten tager fra path svaneke eller path gentofte, hvor den efter følgende fylder listerne ud? og skal det i UI?

    String choice = login();

    FileHandler fileHandler = new FileHandler(Path.of("bibliotek/"+choice), new CSVMapper());
    LoanPolicy loanPolicy = switch (choice) {
        case "svaneke-bibliotek" -> new BornholmLoanPolicy();
        default -> new StandardLoanPolicy();
    };

    List<Resource> resources = new ArrayList<>();
    List<Loan> loans = new ArrayList<>();
    List<Member> members = new ArrayList<>();

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

    // save to CSV
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
            IO.println();
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
