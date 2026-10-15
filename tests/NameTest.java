import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void getFirstName() {
        Name name = new Name("Mickey", "Mouse");
        String result = name.getFirstName();
        assertEquals("Mickey", result);
    }

    @Test
    void getLastName() {
        Name name = new Name("Mickey", "Mouse");
        String result = name.getLastName();
        assertEquals("Mouse", result);
    }

    @Test
    void fullName() {
        Name name = new Name("Mickey", "Mouse");
        String result = name.fullName();
        assertEquals("Mouse, Mickey", result);
    }

    @Test
    void match() {
        Name a = new Name("MICKEY", "Mouse");
        Name b = new Name("Minnie", "Mouse");
        Name c = new Name("Mickey", "Mouse");
        assertFalse(b.match(a));
        assertTrue(a.match(c));
    }

    @Test
    void isLessThan() {
        Name a = new Name("Jimmy", "John");
        Name b = new Name("Jimmy", "Jane");
        assertTrue(b.isLessThan(a));
        assertFalse(a.isLessThan(b));
    }

    @Test
    void testToString() {
        Name a = new Name("Jimmy", "John");
        assertEquals("John, Jimmy", a.toString());
    }
}