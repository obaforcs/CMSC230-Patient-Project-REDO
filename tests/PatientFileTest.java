import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PatientFileTest {
    @Test
    void importSorted() {
        PatientList list = new PatientList();
        assertTrue(list.importFromFile("patients1000.csv"));

        PatientList.Iterator it = list.new Iterator();
        Patient prev = it.next();
        Patient cur = it.next();
        int count = 1;
        while (cur != null) {
            assertFalse(cur.getIdentity().isLessThan(prev.getIdentity()));
            prev = cur;
            cur = it.next();
            count++;
        }
        assertEquals(1000, count);
    }

    @Test
    void importSkipsBadLines() {
        PatientList list = new PatientList();
        assertTrue(list.importFromFile("patientsErrors.csv"));

        PatientList.Iterator it = list.new Iterator();
        Patient prev = it.next();
        Patient cur = it.next();
        int count = 1;
        while (cur != null) {
            assertFalse(cur.getIdentity().isLessThan(prev.getIdentity()));
            prev = cur;
            cur = it.next();
            count++;
        }

        assertEquals(7, count);
    }

    @Test
    void saveAndReload() throws Exception {
        PatientList list = new PatientList();

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Name name1 = new Name("John", "Smith");
        PatientIdentity id1 = new PatientIdentity(name1, fmt.parse("1990-01-01"));
        Patient smith = new Patient(id1);

        Name name2 = new Name("Mickey", "Mouse");
        PatientIdentity id2 = new PatientIdentity(name2, fmt.parse("1985-05-05"));
        Patient mouse = new Patient(id2);

        Name name3 = new Name("Jose", "Suarez");
        PatientIdentity id3 = new PatientIdentity(name3, fmt.parse("1985-05-05"));
        Patient suarez = new Patient(id3);

        list.add(smith);
        list.add(mouse);
        list.add(suarez);

        assertTrue(list.saveToFile("test_output.csv"));

        PatientList reloaded = new PatientList();
        assertTrue(reloaded.importFromFile("test_output.csv"));

        assertNotNull(reloaded.find(id1));
        assertNotNull(reloaded.find(id2));
        assertNotNull(reloaded.find(id3));


    }
}
