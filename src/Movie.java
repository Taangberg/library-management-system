public class Movie extends Resource {
    String EAN;
    String director;
    String runtime;

    public Movie(String creator, String director, String title, String runtime, String EAN, int id) {
        super(creator, title, id);
        this.EAN = EAN;
        this.director = director;
        this.runtime = runtime;
    }

    public String getEAN() { return EAN; }

    public String getDirector() { return director; }

    public String getRuntime() { return runtime; }

    public String toString() {
        return title + ", by " + director + " (" + creator + ");  Runtime: " + runtime + " | EAN: " + EAN + " [ID: " + id + "]";
    }
}

