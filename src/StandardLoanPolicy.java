import java.time.LocalDate;

public class StandardLoanPolicy implements LoanPolicy {

    public boolean canLoan(Member member, Resource resource) {
        return true;
    }

    @Override
    public LocalDate calculateDueDate(LocalDate borrowedDate) {
        return borrowedDate.plusDays(30);
    }
}
