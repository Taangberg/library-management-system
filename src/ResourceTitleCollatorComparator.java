import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class ResourceTitleCollatorComparator implements Comparator<Resource> {
    private final Collator collator;

    public ResourceTitleCollatorComparator(Locale locale) {
        this.collator = Collator.getInstance(locale);
    }

    @Override
    public int compare(Resource resource1, Resource resource2) {
        return collator.compare(resource1.getTitle(), resource2.getTitle());
    }
}