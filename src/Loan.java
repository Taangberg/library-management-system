import java.time.LocalDate;

public record Loan(Book book, Member member, LocalDate borrowedDate) {

    @Override
    public String toString() {
        return "Bog: " + book + "\nLånt af: " + member + " Afleveringsfrist: " + getDueDate() + "\n";
    }

    public LocalDate getDueDate() {
        return borrowedDate.plusDays(14);
    }

/*
    public boolean isOverdue() {
       return LocalDate.now().isAfter(getDueDate());
    }
*/
}


