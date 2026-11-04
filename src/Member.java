import java.util.Comparator;

public record Member(String name, int memberId) implements Comparable<Member> {

    @Override
    public String toString() {
        return name + " (Member id: " + memberId + ")";
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return memberId;
    }

    @Override
    public int compareTo(Member other) {
        return Integer.compare(this.memberId, other.memberId);
    }
}
