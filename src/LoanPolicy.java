import java.time.LocalDate;
import java.util.List;

public interface LoanPolicy {
    boolean canLoan(Member member, Resource resource, List<Loan> loans);

    LocalDate calculateDueDate(LocalDate borrowedDate, Resource resource);
}
