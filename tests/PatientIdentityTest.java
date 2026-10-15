import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PatientIdentityTest {

    @Test
    void getName() {
        Name name = new Name("Jimmy", "John");
        Date dob = new Date(2000);
        PatientIdentity id = new PatientIdentity(name, dob);
        assertEquals(name, id.getName());
    }

    @Test
    void getDateOfBirth() {
        Name name = new Name("Jimmy", "John");
        Date dob = new Date(2000);
        PatientIdentity id = new PatientIdentity(name, dob);
        assertEquals(dob, id.getDateOfBirth());
    }

    @Test
    void match() {
        Name name = new Name("Jimmy", "John");
        Name name1 = new Name("Jimmy", "Jane");
        Date dob = new Date(2000);
        Date date = new Date(1000);
        PatientIdentity id = new PatientIdentity(name, dob);
        PatientIdentity anotherid = new PatientIdentity(name1, date);
        PatientIdentity id3 = new PatientIdentity(name, date);
        PatientIdentity id4 = new PatientIdentity(name, dob);
        assertFalse(id.match(id3));
        assertFalse(id.match(anotherid));
        assertFalse(anotherid.match(id3));
        assertTrue(id.match(id4));
    }

    @Test
    void isLessThan() {
        Name name = new Name("Jimmy", "John");
        Name name1 = new Name("Jimmy", "Jane");
        Date dob = new Date(2000);
        Date date = new Date(1000);
        PatientIdentity id = new PatientIdentity(name, dob);
        PatientIdentity anotherid = new PatientIdentity(name1, date);
        PatientIdentity id3 = new PatientIdentity(name, date);
        PatientIdentity id4 = new PatientIdentity(name, dob);
        assertFalse(id.isLessThan(anotherid));
        assertTrue(id3.isLessThan(id));
    }
}