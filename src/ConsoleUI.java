import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class ConsoleUI {
    private final Library library;
    private final Comparator<Resource> titleComparator;

    public ConsoleUI(Library library, Locale locale) {
        this.library = library;
        this.titleComparator = new ResourceTitleCollatorComparator(locale);
    }

    String RED = "\u001B[31m";
    String YEL = "\u001B[93m";
    String GRE = "\u001B[38;2;86;170;111m";
    String WHI = "\u001B[0m";

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
            separetedResources(availableResources);
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
        memberLoans.sort(new LoanBorrowDateComparator()); // Sorts by borrowDate instead of DueDate
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
        List<Resource> resources = new ArrayList<>(library.getResources());
        resources.sort(new ResourceTitleComparator()); // Sorts by Title instead of ID
        IO.println("Library");
        printSeperator();
        separetedResources(resources);
        printSeperator();
    }


    // Case 5
    private void showMembers() {
        for (Member member : library.getMembers()) {
            IO.println(member);
        }
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
        printSuccessfullyAdded(name);
    }


    //region // Case 7
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
        if (checkResourceId(ISBN)) {
            return;
        }
        library.addResource(new Book(title, author, pageCount, ISBN));
        printSuccessfullyAdded(title);
    }

    private void addAudioBook() {
        String title = IO.readln("Input title: ");
        String author = IO.readln("Input author: ");
        String runtime = IO.readln("Input runtime: ");
        String ISBN = IO.readln("Input ISBN: ");
        if (checkResourceId(ISBN)) {
            return;
        }
        library.addResource(new AudioBook(title, author, runtime, ISBN));
        printSuccessfullyAdded(title);
    }

    private void addGame() {
        String title = IO.readln("Input title: ");
        String platform = IO.readln("Input platform: ");
        String studio = IO.readln("Input studio: ");
        String EAN = IO.readln("Input EAN: ");
        if (checkResourceId(EAN)) {
            return;
        }
        library.addResource(new Game(title, platform, studio, EAN));
        printSuccessfullyAdded(title);
    }

    private void addMovie() {
        String title = IO.readln("Input title: ");
        String director = IO.readln("Input director: ");
        String studio = IO.readln("Input studio: ");
        String runtime = IO.readln("Input runtime: ");
        String EAN = IO.readln("Input EAN: ");
        if (checkResourceId(EAN)) {
            return;
        }
        library.addResource(new Movie(title, director, studio, runtime, EAN));
        printSuccessfullyAdded(title);
    }

    private boolean checkResourceId(String ID) {
        if (library.getResource(ID) != null) {
            IO.println("Resource already added");
            return true;
        }
        return false;
    }

    private void printSuccessfullyAdded(String title) {
        IO.println(title + " successfully added");
    }
//endregion


    // Aesthetic
    public void separetedResources(List<Resource> resources) {
        IO.println("Books: ");
        for (Resource resource : resources) {
            if (resource instanceof Book book) {
                IO.println(book);
            }
        }
        IO.println();
        IO.println("Audio Books: ");
        for (Resource resource : resources) {
            if (resource instanceof AudioBook audioBook) {
                IO.println(audioBook);
            }
        }
        IO.println();
        IO.println("Games: ");
        for (Resource resource : resources) {
            if (resource instanceof Game game) {
                IO.println(game);
            }
        }
        IO.println();
        IO.println("Movies: ");
        for (Resource resource : resources) {
            if (resource instanceof Movie movie) {
                IO.println(movie);
            }
        }
    }

    private void printSeperator() {
        IO.println("------------------------------------------------------------------------------------");
    }
}
