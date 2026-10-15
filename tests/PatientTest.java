import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void getIdentity() {
        Name a = new Name("Jimmy", "John");
        Date dob = new Date(200);
        PatientIdentity id = new PatientIdentity(a, dob);
        Patient patient = new Patient(id);
        assertEquals(id, patient.getIdentity());
    }
}