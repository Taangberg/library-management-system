import java.util.ArrayList;

public class ConsoleUI {
    private final Library library;

    public ConsoleUI(Library library) {
        this.library = library;
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
                    case 0 -> running = false;
                    default -> IO.println("Invalid input. Try again");
                }
            } catch (NumberFormatException numberFormatException) {
                IO.println("Invalid input. Must be a number \nTry again \n");
            }
        }
    }

    private void showMenu() {
        IO.println();
        IO.println("1. Borrow");
        IO.println("2. Return");
        IO.println("3. View all loans");
        IO.println("4. View library collection");
        IO.println("0. Quit");
        IO.println();
    }

    private void borrowBook() {
        int memberId = Integer.parseInt(IO.readln("Input member id: "));
        Member member = library.getMember(memberId);

        if (member == null) {
            IO.println("Member id does not exist");
        }
        else {
        IO.println("\n" + member + "\n------------------------------------------------------------------------------------");
        ArrayList<Resource> availableResources = library.getAvailableResources();
        for (Resource onShelf : availableResources) {
            IO.println(onShelf + "\n");
        }
        IO.println();

        String resourceID = IO.readln("Input item id: ");
        LoanResult loanResult = library.loanResource(memberId, resourceID);

        switch (loanResult) {
            case SUCCESS     -> IO.println("Item successfully borrowed!");
            case LOANED      -> IO.println("Item unavailable");
            case NO_RESOURCE -> IO.println("Item does not exist");
            }
        }
    }

    private void returnBook() {
        String resourceID = IO.readln("Input item id: ");

        boolean succesReturn = library.returnResource(resourceID);

        IO.println(succesReturn ? "Item successfully returned" : "Item already in library");
    }

    private void showLoans() {
        int memberId = Integer.parseInt(IO.readln("Input member id: "));

        Member member = library.getMember(memberId);
        if (member == null) {
            IO.println("Member does not exist");
            return;
        }
        IO.println("\n" + member + "\n------------------------------------------------------------------------------------");

        ArrayList<Loan> memberLoans = library.loansByMember(memberId);
        if (memberLoans.isEmpty()) {
            IO.println("User has no active loans");
        }
        for (Loan loan : memberLoans) {
            IO.println(loan);
        }
    }

    private void showResources() {
        ArrayList<Resource> b = library.getResources();
        IO.println("Library\n------------------------------------------------------------------------------------");
        for (Resource resource : b) {
            IO.println(resource + "\n");
        }
    }
}
