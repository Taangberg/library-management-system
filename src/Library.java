import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Library {
    private ArrayList<Resource> catalog = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Loan> loans = new ArrayList<>();

    public boolean isAvailable(String resourceID) {
        for (Loan loan : loans) {
            if (loan.resource().getId().equals(resourceID)) {
                return false;
            }
        } return true;
    }

    // Adders
    public void addResource(Resource resource) { catalog.add(resource); }  // <--- Superfluous

    public void addResources(Resource... newResources) { catalog.addAll(Arrays.asList(newResources)); }

    public void addMember(Member member) { members.add(member); }   // <--- Superfluous

    public void addMembers(Member... newMembers) { members.addAll(Arrays.asList(newMembers)); }

    public void addLoan(Loan loan) { loans.add(loan); }

    // Getters
    public ArrayList<Resource> getResources() { return catalog; }

    public ArrayList<Member> getMembers() { return members; }

    public ArrayList<Loan> getLoan() { return loans; }

    public Resource getResource(String resourceID) {
        for (Resource resource : catalog) {
            if (resource.getId().equals(resourceID)) {
                return resource;
            }
        } return null;
    }

    public ArrayList<Resource> getAvailableResources() {
        ArrayList<Resource> availableResources = new ArrayList<>();
        for (Resource resource : catalog) {
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
    public boolean loanResource(int memberID, String resourceID) {
        if (getMember(memberID) != null || getResource(resourceID) != null) {
            if (isAvailable(resourceID)) {
                Loan loan = new Loan(getResource(resourceID), getMember(memberID), LocalDate.now());
                addLoan(loan);
                return true;
            }
        } else {
            return false;       // Enums go here soon
        }
        return false;
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
        for (Resource resource : catalog) {
            IO.println(resource);
        }
    }

    public void printMembers() {
        for (Member member : members) {
            IO.println(member);
        }
    }
}
