public class Game extends Resource {
    String platform;    // potential enum

    public Game(String title, String platform, String studio, String EAN) {
        super(title, studio, EAN);
        this.platform = platform;
    }

    public String getPlatform() { return platform; }

    @Override
    public String toString() {
        return title + " (" + platform + "), by " + creator + "; EAN: " + id;
    }
}

