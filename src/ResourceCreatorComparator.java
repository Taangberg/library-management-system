import java.util.Comparator;

public class ResourceCreatorComparator implements Comparator<Resource> {
    @Override
    public int compare(Resource resource1, Resource resource2) {
        int byCreator = resource1.getCreator().compareTo(resource2.getCreator());
        if (byCreator != 0) {
            return byCreator;
        }
        return resource1.getTitle().compareTo(resource2.getTitle());
    }
}