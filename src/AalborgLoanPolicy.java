import java.time.LocalDate;
import java.util.List;

public class AalborgLoanPolicy implements LoanPolicy {

    @Override
    public boolean canLoan(Member member, Resource resource, List<Loan> loans) {
        if (resource instanceof Movie || resource instanceof Game) {
            int count = 0;

            for (Loan loan : loans) {
                if (loan.member().equals(member)) {
                    if (loan.resource() instanceof Movie || loan.resource() instanceof Game) {
                        count++;
                    }
                }
            }

            if (count >= 3) {
                return false;
            }
        }
        return true;
    }

    @Override
    public LocalDate calculateDueDate(LocalDate borrowedDate, Resource resource) {
        return borrowedDate.plusDays(30);
    }
}
