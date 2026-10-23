import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    //test memberName()
    @Test
    void memberName() {
        //Arrange
        Member member = new Member("Thorkild Hansen", 111);
        //Act
        String memberName = member.name();
        //Assert
        assertEquals("Thorkild Hansen", memberName);
    }
    @Test
    void notMemberName() {
        //Arrange
        Member member = new Member("Thorkild Hansen", 111);
        //Act
        String memberName = member.name();
        //Assert
        assertNotEquals("Thorkild Christiansen", memberName);
    }

    //test memberID()
    @Test
    void memberID() {
        //Arrange
        Member member = new Member("Thorkild Hansen", 111);
        //Act
        int memberID = member.memberId();
        //Assert
        assertEquals(111, memberID);
    }
    @Test
    void notMemberID() {
        //Arrange
        Member member = new Member("Thorkild Hansen", 111);
        //Act
        int memberID = member.memberId();
        //Assert
        assertNotEquals(112, memberID);
    }
}
