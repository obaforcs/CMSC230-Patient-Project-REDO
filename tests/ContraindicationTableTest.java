import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContraindicationTableTest {

    private int count(Contraindications c) {
        int n = 0;
        PrescriptionList.Iterator it = c.getList().new Iterator();
        while (it.next() != null) n++;
        return n;
    }

    @Test
    public void loadsAndCounts() {
        ContraindicationTable table = new ContraindicationTable();
        assertTrue(table.loadFromFile("interactions.csv"));

        Contraindications flox = table.find("floxapril");
        assertNotNull(flox);
        assertEquals(55, count(flox));
        assertEquals(118, count(table.find("gammastat")));
    }

    @Test
    public void findMissing() {
        ContraindicationTable table = new ContraindicationTable();
        table.loadFromFile("interactions.csv");
        assertNull(table.find("notarealmed"));
    }
}