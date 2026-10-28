import java.time.LocalDate;

public record Loan(Resource resource, Member member, LocalDate borrowedDate, LocalDate dueDate) {

    @Override
    public String toString() {
        return "Item: " + resource + "\nBorrowed on " + borrowedDate + " | Due date: " + dueDate;
    }   // Need variable name to "Bog: " depending on material
}


