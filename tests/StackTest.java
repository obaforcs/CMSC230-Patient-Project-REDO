import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    @Test
    void pushPopReverseOrder() {
        Stack<String> s = new Stack<>(10);
        s.push("a");
        s.push("b");
        s.push("c");
        assertEquals("c", s.pop());
        assertEquals("b", s.pop());
        assertEquals("a", s.pop());
    }

    @Test
    void popEmptyReturnsNull() {
        Stack<String> s = new Stack<>(10);
        assertTrue(s.isEmpty());
        assertNull(s.pop());
    }
}
