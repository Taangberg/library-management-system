import java.time.LocalDate;
import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Loan> loans = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<Loan> getLoan() {
        return loans;
    }

    public void printBooks() {
        for (Book book : books) {
            IO.println(book);
        }
    }

    public void printMembers() {
        for (Member member : members) {
            IO.println(member);
        }
    }

    public Book getBook(int id) {
        for (Book book : books) {
            if (book.id() == id) {
                return book;
            }
        }
        return null;
    }

    public boolean isAvailable(int id) {
        for (Loan loan : loans) {
            if (loan.book().id() == id) {
                return false;
            }
        }
        return true;
    }

    public Member getMember(int id) {
        for (Member member : members) {
            if (member.memberId() == id) {
                return member;
            }
        }
        return null;
    }

    public ArrayList<Loan> loansByMember(int memberId) {
        ArrayList<Loan> memberLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.member().memberId() == memberId) {
                memberLoans.add(loan);
            }
        }
        return memberLoans;
    }

    public boolean loanBook(int memberID, int bookID) {
        if (getMember(memberID) != null || getBook(bookID) != null) {
            if (isAvailable(bookID)) {
                Loan loan = new Loan(getBook(bookID), getMember(memberID), LocalDate.now());
                addLoan(loan);
                return true;
            }

        } else {
            return false;
        }

        return false;
    }

    public boolean returnBook(int bookID) {
        for (int i = 0; i < loans.size(); i++) {
            if (loans.get(i).book().id() == bookID) {
                loans.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (isAvailable(book.id())) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}
