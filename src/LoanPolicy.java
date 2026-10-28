import java.time.LocalDate;

public interface LoanPolicy {
    boolean canLoan(Member member, Resource resource);
    LocalDate calculateDueDate(LocalDate borrowedDate);
}
