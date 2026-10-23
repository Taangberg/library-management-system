import java.time.LocalDate;
import java.util.ArrayList;

public class Library {
    private ArrayList<Resource> resources;
    private ArrayList<Member> members;
    private ArrayList<Loan> loans;

    public Library() {
        Catalog catalog = new Catalog();
        resources = catalog.getResources();
        members = catalog.getMembers();
        loans = catalog.getLoans();
    }

    public boolean isAvailable(String resourceID) {
        for (Loan loan : loans) {
            if (loan.resource().getId().equals(resourceID)) {
                return false;
            }
        } return true;
    }

  /*public void addResource(Resource resource) { resources.add(resource); }  // <--- Superfluous
    public void addResources(Resource... newResources) { resources.addAll(Arrays.asList(newResources)); }

    public void addMember(Member member) { members.add(member); }   // <--- Superfluous
    public void addMembers(Member... newMembers) { members.addAll(Arrays.asList(newMembers)); }*/

    public void addLoan(Loan loan) { loans.add(loan); }

    // Getters
    public ArrayList<Resource> getResources() { return resources; }

    public ArrayList<Member> getMembers() { return members; }

    public ArrayList<Loan> getLoan() { return loans; }

    public Resource getResource(String resourceID) {
        for (Resource resource : resources) {
            if (resource.getId().equals(resourceID)) {
                return resource;
            }
        } return null;
    }

    public ArrayList<Resource> getAvailableResources() {
        ArrayList<Resource> availableResources = new ArrayList<>();
        for (Resource resource : resources) {
            if (isAvailable(resource.getId())) {
                availableResources.add(resource);
            }
        }
        return availableResources;
    }

    public Member getMember(int id) {
        for (Member member : members) {
            if (member.memberId() == id) {
                return member;
            }
        } return null;
    }

    public ArrayList<Loan> loansByMember(int memberId) {
        ArrayList<Loan> memberLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.member().memberId() == memberId) {
                memberLoans.add(loan);
            }
        }
        return memberLoans;
    }

    // Loan / Return
    public LoanResult loanResource(int memberID, String resourceID) {
        Member member = getMember(memberID);
        Resource resource = getResource(resourceID);
        if (member != null && resource != null) {
            if (isAvailable(resourceID)) {
                Loan loan = new Loan(resource, member, LocalDate.now());
                addLoan(loan);
                return LoanResult.SUCCESS;
            } else {
                return LoanResult.LOANED;
            }
        }
        return LoanResult.NO_RESOURCE;
    }

    public boolean returnResource(String resourceID) {
        for (int i = 0; i < loans.size(); i++) {
            if (loans.get(i).resource().getId().equals(resourceID)) {
                loans.remove(i);
                return true;
            }
        }
        return false;
    }

    // Printers     // Should probably be in ConsoleUI
    public void printResources() {
        for (Resource resource : resources) {
            IO.println(resource);
        }
    }

    public void printMembers() {
        for (Member member : members) {
            IO.println(member);
        }
    }
}
