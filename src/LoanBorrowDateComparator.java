import java.util.Comparator;

public class LoanBorrowDateComparator implements Comparator<Loan> {
    @Override
    public int compare(Loan loan1, Loan loan2) {
        return loan1.getBorrowDate().compareTo(loan2.getBorrowDate());
    }
}