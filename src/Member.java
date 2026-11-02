public record Member(String name, int memberId) {

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
}
