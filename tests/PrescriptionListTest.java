import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionListTest {
    @Test
    void addKeepsNewestFirst() throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        PrescriptionList list = new PrescriptionList();

        Prescription old = new Prescription("aspirin", fmt.parse("2020-01-01"), 50, "Lee");
        Prescription mid = new Prescription("tylenol", fmt.parse("2022-06-15"), 100, "Ali");
        Prescription recent = new Prescription("advil", fmt.parse("2024-03-10"), 200, "Li");

        list.add(mid);
        list.add(old);
        list.add(recent);

        PrescriptionList.Iterator it = list.new Iterator();
        assertEquals(recent, it.next());
    }

    @Test
    void importPrescriptionsForMaria() throws Exception {
        PatientList list = new PatientList();
        assertTrue(list.importFromFile("patients1000.csv"));
        assertTrue(list.importPrescriptions("prescriptions1000.csv"));

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Name n = new Name("Maria", "Smith");
        PatientIdentity id = new PatientIdentity(n, fmt.parse("1953-06-16"));

        Patient maria = list.find(id);
        assertNotNull(maria);

        PrescriptionList.Iterator it = maria.getPrescriptions().new Iterator();
        assertEquals("heptapone", it.next().getName());
        assertEquals("phytochlor", it.next().getName());
        assertEquals("heptastat", it.next().getName());
        assertNull(it.next());
    }
}
