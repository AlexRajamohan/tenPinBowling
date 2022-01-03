import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RollTU {

    @Test
    public void testCheckValidRoll() throws Exception {

        // should throw "Invalid roll's value"
        String actual1 = assertThrows(Exception.class, () -> {
            new Roll(0, "15");
        }).getMessage();

        // should throw "Invalid roll's value"
        String actual2 = assertThrows(Exception.class, () -> {
            new Roll(1, null);
        }).getMessage();

        // should throw "Invalid roll's index"
        String actual3 = assertThrows(Exception.class, () -> {
            new Roll(4, "X");
        }).getMessage();

        // should be valid
        var validRoll = new Roll(3, "X");

        // Expected msgs
        String expected1 = "Invalid roll's value";
        String expected2 = "Invalid roll's index";

        assertEquals(expected1, actual1);
        assertEquals(expected1, actual2);
        assertEquals(expected2, actual3);
        assertNotNull(validRoll);
    }
}
