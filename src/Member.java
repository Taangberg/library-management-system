public record Member(String name, int memberId) {

    @Override
    public String toString() {
        return name + " (Lånernummer: " + memberId + ")";
    }

}
