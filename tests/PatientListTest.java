import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PatientListTest {
    @Test
    void addAndFind() {
        PatientList list = new PatientList();

        Name name1 = new Name("John", "Smith");
        PatientIdentity id1 = new PatientIdentity(name1, new Date(100));
        Patient smith = new Patient(id1);

        Name name2 = new Name("Mickey", "Mouse");
        PatientIdentity id2 = new PatientIdentity(name2, new Date(200));
        Patient mouse = new Patient(id2);

        Name name3 = new Name("Jose", "Suarez");
        PatientIdentity id3 = new PatientIdentity(name3, new Date(50));
        Patient suarez = new Patient(id3);

        list.add(smith);
        list.add(mouse);
        list.add(suarez);

        assertEquals(mouse, list.find(id2));
        assertEquals(smith, list.find(id1));
        assertEquals(suarez, list.find(id3));
    }

    @Test
    void findMissing() {
        PatientList list = new PatientList();

        Name name1 = new Name("John", "Smith");
        PatientIdentity id1 = new PatientIdentity(name1, new Date(100));
        Patient smith = new Patient(id1);

        list.add(smith);

        Name name2 = new Name("Mickey", "Mouse");
        PatientIdentity id2 = new PatientIdentity(name2, new Date(200));
        Patient mouse = new Patient(id2);

        assertNull(list.find(id2));
    }
}
