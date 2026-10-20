public class AudioBook extends Resource {
  //String narrator;
    String runtime;

    public AudioBook(String title, String author, String runtime, String ISBN) {
        super(title,author, ISBN);
      //this.narrator = narrator;
        this.runtime = runtime;
    }

    public String getRuntime()  { return runtime; }

  //public String getNarrator() { return narrator; }

    @Override
    public String toString() {
        return title + ", by " + creator + "; Runtime: " + runtime + " | ISBN " + id;
    } //", narrated by " + narrator + //
}
