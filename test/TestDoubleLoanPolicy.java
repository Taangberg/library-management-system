import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TestDoubleLoanPolicy implements LoanPolicy {
    public boolean canLoanCalled = false;

    @Override
    public boolean canLoan(Member member, Resource resource, List<Loan> loans) {
        canLoanCalled = true;
        return true;

    }

    @Override
    public LocalDate calculateDueDate(LocalDate borrowedDate, Resource resource) {
        return borrowedDate.plusDays(30);
    }
}
