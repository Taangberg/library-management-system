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
                int choice = Integer.parseInt(IO.readln("Indtast valg: "));
                IO.println();
                switch (choice) {
                    case 1 -> borrowBook();
                    case 2 -> returnBook();
                    case 3 -> showLoans();
                    case 4 -> showBooks();
                    case 0 -> running = false;
                    default -> IO.println("Ugyldigt valg. Prøv igen");
                }
            } catch (NumberFormatException numberFormatException) {
                IO.println("Invalid input. Must be a number \nTry again \n");
            }
        }
    }

    private void showMenu() {
        IO.println();
        IO.println("1. Lån");
        IO.println("2. Aflever");
        IO.println("3. Vis alle lån");
        IO.println("4. Se bøger");
        IO.println("0. Afslut");
        IO.println();
    }

    private void borrowBook() {
        int memberId = Integer.parseInt(IO.readln("Indtast medlemsnummer: "));
        library.getAvailableBooks();
        int bookId = Integer.parseInt(IO.readln("Indtast bog id: "));

        boolean succesLoan = library.loanBook(memberId, bookId);

        IO.println(succesLoan ? "Bogen er lånt!" : "Bogen er allerede udlånt");
    }

    private void returnBook() {
        int bookId = Integer.parseInt(IO.readln("Indtast bog id: "));

        boolean succesReturn = library.returnBook(bookId);

        IO.println(succesReturn ? "Bogen er afleveret" : "Bogen er ikke udlånt");
    }

    private void showLoans() {
        int memberId = Integer.parseInt(IO.readln("Indtast medlemsnummer: "));
        ArrayList<Loan> memberLoans = library.loansByMember(memberId);
        for (Loan loan : memberLoans) {
            IO.println(loan);
        }
    }

    private void showBooks() {
        ArrayList<Book> books = library.getBooks();
        for (Book book : books) {
            IO.println(book);
        }
    }
}
