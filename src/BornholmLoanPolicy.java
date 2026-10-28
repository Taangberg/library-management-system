import java.time.LocalDate;
import java.util.List;

public class BornholmLoanPolicy implements LoanPolicy {

    @Override
    public boolean canLoan(Member member, Resource resource, List<Loan> loans) {
        return true;
    }

    @Override
    public LocalDate calculateDueDate(LocalDate borrowedDate, Resource resource) {
        if (resource instanceof AudioBook) {
            return borrowedDate.plusDays(14);
        }
        return borrowedDate.plusDays(30);
    }
}
