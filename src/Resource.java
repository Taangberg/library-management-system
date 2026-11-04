import java.util.Comparator;

public abstract class Resource implements Comparable<Resource> {
    final String title;
    final String creator;
    final String id;

    public Resource (String title, String creator, String id) {
        this.title = title;
        this.creator = creator;
        this.id = id;
    }

    public String getTitle()   { return title; }

    public String getCreator() { return creator; }

    public String getId()      { return id; }

    public String toString() {
        return title + ", by " + creator + " [ID: " + id + "]";
    }

    @Override
    public int compareTo(Resource other) {
        return this.id.compareTo(other.id);
    }
}
