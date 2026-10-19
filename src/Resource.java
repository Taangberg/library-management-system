public abstract class Resource {
    final String creator;
    final String title;
    int id;

    public Resource (String creator, String title, int id) {
        this.creator = creator;
        this.title = title;
        this.id = id;
    }

    public String getCreator() { return creator; }

    public String getTitle() { return title; }

    public int getId() { return id; }
}
