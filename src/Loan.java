import java.time.LocalDate;

public record Loan(Resource resource, Member member, LocalDate borrowedDate) {

    @Override
    public String toString() {
        return "Item: " + resource + "\nBorrowed on " + borrowedDate + " | Due date: " + getDueDate();
    }   // Need variable name to "Bog: " depending on material

    public LocalDate getDueDate() {
        return borrowedDate.plusDays(14);
    }
}


