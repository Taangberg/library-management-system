import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class FileHandlerTest {

    @TempDir
    Path tempDir;

    @Test
    void testSaveAndLoadMembers() throws IOException {

        //ARRANGE
        CSVMapper csvMapper = new CSVMapper();
        FileHandler fileHandler = new FileHandler(tempDir, csvMapper);
        Member member = new Member("Peter", 1234);

        //ACT
        fileHandler.saveMembers(List.of(member));
        List<Member> loadedMembers = fileHandler.loadMembers();

        //ASSERT
        assertEquals(1, loadedMembers.size());
        assertEquals("Peter", loadedMembers.get(0).getName());
        assertEquals(1234, loadedMembers.get(0).getId());
    }



}
