public class Movie extends Resource {
    String director;
    String runtime;

    public Movie(String title, String director, String studio, String runtime, String EAN) {
        super(title, studio, EAN);
        this.director = director;
        this.runtime = runtime;
    }

    public String getDirector() { return director; }

    public String getRuntime() { return runtime; }

    @Override
    public String toString() {
        return title + ", by " + director + " (" + creator + ");  Runtime: " + runtime + " | EAN: " + id;
    }
}

