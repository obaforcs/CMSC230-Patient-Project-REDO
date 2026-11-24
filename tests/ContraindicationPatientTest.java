import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class ContraindicationPatientTest {

    private int count(Contraindications c) {
        int n = 0;
        PrescriptionList.Iterator it = c.getList().new Iterator();
        while (it.next() != null) n++;
        return n;
    }

    @Test
    public void mingSchmidt() throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        PatientList list = new PatientList();
        list.importFromFile("patients1000.csv");
        list.importPrescriptions("prescriptions1000.csv");
        ContraindicationTable table = new ContraindicationTable();
        table.loadFromFile("interactions.csv");

        Patient ming = list.find(new PatientIdentity(new Name("Ming", "Schmidt"), fmt.parse("1946-02-01")));
        Date today = fmt.parse("2024-09-17");

        assertNotNull(ming);
        assertEquals(69, count(table.contraindicatedFor(ming, today)));
        assertTrue(table.isContraindicated(ming, new Prescription("benzostat", today, 0, "")));
        assertFalse(table.isContraindicated(ming, new Prescription("floxazone", today, 0, "")));
    }

    @Test
    public void mariaHasNothingCurrent() throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        PatientList list = new PatientList();
        list.importFromFile("patients1000.csv");
        list.importPrescriptions("prescriptions1000.csv");
        ContraindicationTable table = new ContraindicationTable();
        table.loadFromFile("interactions.csv");

        Patient maria = list.find(new PatientIdentity(new Name("Maria", "Smith"), fmt.parse("1953-06-16")));
        Date today = fmt.parse("2024-09-17");

        assertNotNull(maria);
        assertEquals(0, count(table.contraindicatedFor(maria, today)));
        assertFalse(table.isContraindicated(maria, new Prescription("diazotan", today, 0, "")));
    }
}