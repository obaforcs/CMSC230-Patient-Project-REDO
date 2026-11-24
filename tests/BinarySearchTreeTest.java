import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class BinarySearchTreeTest {
    BinarySearchTree<PatientIdentity, Patient> tree = new BinarySearchTree<>();

    @Test
    void addAndFind() {
        Name name1 = new Name("John", "Smith");
        PatientIdentity id1 = new PatientIdentity(name1, new Date(100));
        Patient smith = new Patient(id1);

        Name name2 = new Name("Mickey", "Mouse");
        PatientIdentity id2 = new PatientIdentity(name2, new Date(200));
        Patient mouse = new Patient(id2);

        Name name3 = new Name("Jose", "Suarez");
        PatientIdentity id3 = new PatientIdentity(name3, new Date(50));
        Patient suarez = new Patient(id3);

        tree.add(smith);
        tree.add(mouse);
        tree.add(suarez);

        assertEquals(mouse, tree.find(id2));
        assertEquals(smith, tree.find(id1));
        assertEquals(suarez, tree.find(id3));
    }

    @Test
    void findMissing() {
        Name name1 = new Name("John", "Smith");
        PatientIdentity id1 = new PatientIdentity(name1, new Date(100));
        Patient smith = new Patient(id1);

        Name name2 = new Name("Mickey", "Mouse");
        PatientIdentity id2 = new PatientIdentity(name2, new Date(200));
        Patient mouse = new Patient(id2);

        tree.add(smith);

        assertNull(tree.find(id2));
    }
}
