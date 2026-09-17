import java.time.LocalDate;

public class Loan {

    private final Book book;
    private final Member member;
    final LocalDate borrowedDate;

    public Loan(Book book, Member member, LocalDate borrowedDate) {
        this.book = book;
        this.member = member;
        this.borrowedDate = borrowedDate;
    }

    public String toString() {
        return "Bog: " + book + "\nLånt af: " + member + " Afleveringsfrist: " + getDueDate() + "\n";
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getDueDate() {
        return borrowedDate.plusDays(14);
        }

    public boolean isOverdue() {
       return LocalDate.now().isAfter(getDueDate());
    }
}


