import java.time.LocalDate;
import java.util.List;

public class StandardLoanPolicy implements LoanPolicy {

    @Override
    public boolean canLoan(Member member, Resource resource, List<Loan> loans) {
        return true;
    }

    @Override
    public LocalDate calculateDueDate(LocalDate borrowedDate, Resource resource) {
        return borrowedDate.plusDays(30);
    }
}
