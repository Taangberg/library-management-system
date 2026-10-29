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
        IO.println("| 0. Quit");
        IO.println();
    }

    // Case 1
    private void borrowBook() {
        int memberId = Integer.parseInt(IO.readln("Input member id: "));
        Member member = library.getMember(memberId);

        if (member == null) {
            IO.println(RED + "Member id does not exist" + WHI);
        }
        else {
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
            case SUCCESS       -> IO.println(GRE + "Item successfully borrowed!" + WHI);
            case LOANED        -> IO.println(YEL + "Item unavailable" + WHI);
            case NO_RESOURCE   -> IO.println(RED + "Item does not exist" + WHI);
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

    private void printSeperator(){
        IO.println("------------------------------------------------------------------------------------");
    }
}
