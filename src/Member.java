public class Member {

    final String name;
    final int memberId;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    public String toString() {
        return name + " (Lånernummer: " + memberId + ")";
    }

    public int getMemberId() {
        return memberId;
    }

}
