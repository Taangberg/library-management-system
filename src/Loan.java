import java.time.LocalDate;

public record Loan(Resource resource, Member member, LocalDate borrowedDate, LocalDate dueDate) implements Comparable<Loan> {

    @Override
    public String toString() {
        return "Item: " + resource + "\nBorrowed on " + borrowedDate + " | Due date: " + dueDate;
    }   // Need variable name to "Bog: " depending on material

    public Resource getResource() {
        return resource;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getBorrowDate() {
        return borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public int compareTo(Loan other) {
        return this.dueDate.compareTo(other.dueDate);
    }
}


