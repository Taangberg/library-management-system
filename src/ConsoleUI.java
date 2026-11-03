import java.util.List;

public class ConsoleUI {
    private final Library library;

    public ConsoleUI(Library library) {
        this.library = library;
    }

    String RED = "\u001B[31m";
    String YEL = "\u001B[93m";
    String GRE = "\u001B[38;2;86;170;111m";
    String WHI = "\u001B[0m";

    public void login() {
        boolean running = true;
        while (running) {
            showLogin();
            try {
                int choice = Integer.parseInt(IO.readln("Input library choice: "));
                IO.println();
                switch (choice) {
                    case 1 -> selectPath(1);
                    case 2 -> selectPath(2);
                    default -> IO.println(RED + "Invalid input. Try again" + WHI);
                }
            } catch (NumberFormatException e) {
                IO.println(RED + "Invalid input. Must be a number \nTry again \n" + WHI);
            }
        }
    }

    public void run() {
        boolean running = true;
        while (running) {
            showMenu();
            try {
                int choice = Integer.parseInt(IO.readln("Input menu choice: "));
                IO.println();
                switch (choice) {
                    case 1 -> borrowBook();
                    case 2 -> returnBook();
                    case 3 -> showLoans();
                    case 4 -> showResources();
                    case 5 -> showMembers();
                    case 6 -> addMember();
                    case 7 -> addResource();
                    case 0 -> running = false;
                    default -> IO.println(RED + "Invalid input. Try again" + WHI);
                }
            } catch (NumberFormatException e) {
                IO.println(RED + "Invalid input. Must be a number \nTry again \n" + WHI);
            }
        }
    }

    public void showLogin() {
        IO.println();
        IO.println("| 1. Gentofte bibliotek");
        IO.println("| 2. Svaneke bibliotek");
        IO.println();
    }

    public String selectPath(int choice) {
        if (choice == 1) {
            return "gentofte-bibliotek";
        }
        return "svaneke-bibliotek";
    }

    private void showMenu() {
        IO.println();
        IO.println("| 1. Borrow");
        IO.println("| 2. Return");
        IO.println("| 3. View all loans");
        IO.println("| 4. View library collection");
        IO.println("| 5. Show members");
        IO.println("| 6. Add member");
        IO.println("| 7. Add resource");
        IO.println("| 0. Quit");
        IO.println();
    }


    // Case 1
    private void borrowBook() {
        int memberId = Integer.parseInt(IO.readln("Input member id: "));
        Member member = library.getMember(memberId);

        if (member == null) {
            IO.println(RED + "Member id does not exist" + WHI);
        } else {
            IO.println("\n" + member);
            printSeperator();
            List<Resource> availableResources = library.getAvailableResources();
            for (Resource onShelf : availableResources) {
                IO.println(onShelf);
            }
            printSeperator();
            IO.println();

            String resourceID = IO.readln("Input item id: ");
            LoanResult loanResult = library.loanResource(memberId, resourceID);

            switch (loanResult) {
                case SUCCESS -> IO.println(GRE + "Item successfully borrowed!" + WHI);
                case LOANED -> IO.println(YEL + "Item unavailable" + WHI);
                case NO_RESOURCE -> IO.println(RED + "Item does not exist" + WHI);
                case NOT_PERMITTED -> IO.println(RED + "Member is not permitted to borrow this item" + WHI);
            }
        }
    }

    // Case 2
    private void returnBook() {
        String resourceID = IO.readln("Input item id: ");

        boolean succesReturn = library.returnResource(resourceID);

        IO.println(succesReturn ? GRE + "Item successfully returned" + WHI : YEL + "Item already in library" + WHI);
    }


    // Case 3
    private void showLoans() {
        int memberId = Integer.parseInt(IO.readln("Input member id: "));

        Member member = library.getMember(memberId);
        if (member == null) {
            IO.println(RED + "Member does not exist" + WHI);
            return;
        }
        IO.println("\n" + member);
        printSeperator();

        List<Loan> memberLoans = library.loansByMember(memberId);
        if (memberLoans.isEmpty()) {
            IO.println(YEL + "User has no active loans" + WHI);
        }
        for (Loan loan : memberLoans) {
            IO.println(loan);
        }
        printSeperator();
    }


    // Case 4
    private void showResources() {
        List<Resource> resources = library.getResources();
        IO.println("Library");
        printSeperator();
        for (Resource resource : resources) {
            IO.println(resource);
        }
        printSeperator();
    }


    // Case 5
    private void showMembers() {
        library.printMembers();
    }

    // Case 6
    private void addMember() {
        String name = IO.readln("Input name: ");
        int id = Integer.parseInt(IO.readln("Input new id: "));
        if (library.getMember(id) != null) {
            IO.println("ID already used");
            return;
        }
        library.addMember(new Member(name, id));
        successfullyAdded(name);
    }


    // Case 7
    private void addResource() {
        showAddResource();
        int choice = Integer.parseInt(IO.readln("Input menu choice: "));
        switch (choice) {
            case 1 -> addBook();
            case 2 -> addAudioBook();
            case 3 -> addGame();
            case 4 -> addMovie();
        }
    }

    private void showAddResource() {
        IO.println();
        IO.println("| 1. Book");
        IO.println("| 2. AudioBook");
        IO.println("| 3. Movie");
        IO.println("| 4. Game");
        IO.println();
    }

    private void addBook() {
        String title = IO.readln("Input title: ");
        String author = IO.readln("Input author: ");
        int pageCount = Integer.parseInt(IO.readln("Input pagecount: "));
        String ISBN = IO.readln("Input ISBN: ");
        checkResourceId(ISBN);
        library.addResource(new Book(title, author, pageCount, ISBN));
        successfullyAdded(title);
    }

    private void addAudioBook() {
        String title = IO.readln("Input title: ");
        String author = IO.readln("Input author: ");
        String runtime = IO.readln("Input runtime: ");
        String ISBN = IO.readln("Input ISBN: ");
        checkResourceId(ISBN);
        library.addResource(new AudioBook(title, author, runtime, ISBN));
        successfullyAdded(title);
    }

    private void addGame() {
        String title = IO.readln("Input title: ");
        String platform = IO.readln("Input platform: ");
        String studio = IO.readln("Input studio: ");
        String EAN = IO.readln("Input EAN: ");
        checkResourceId(EAN);
        library.addResource(new Game(title, platform, studio, EAN));
        successfullyAdded(title);
    }

    private void addMovie() {
        String title = IO.readln("Input title: ");
        String director = IO.readln("Input director: ");
        String studio = IO.readln("Input studio: ");
        String runtime = IO.readln("Input runtime: ");
        String EAN = IO.readln("Input EAN: ");
        checkResourceId(EAN);
        library.addResource(new Movie(title, director, studio, runtime, EAN));
        successfullyAdded(title);
    }

    private void checkResourceId(String ID) {
        if (library.getResource(ID) != null) {
            IO.println("Resource already added");
        }
    }

    private void successfullyAdded(String title) {
        IO.println(title + " successfully added");
    }

    private void printSeperator() {
        IO.println("------------------------------------------------------------------------------------");
    }
}
