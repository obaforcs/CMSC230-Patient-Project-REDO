import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Patient {
    private final PatientIdentity id;

    public Patient (PatientIdentity id){
        if (id == null || id.getName() == null || id.getDateOfBirth() == null){
            throw new IllegalArgumentException("Patient needs a name and date of birth");
        }
        this.id = id;
    }

    public PatientIdentity getIdentity(){
        return id;
    }

    public String toCSV() {
        Name name = id.getName();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String dob = fmt.format(id.getDateOfBirth());
        return name.getLastName() + ", " + name.getFirstName() + ", " + dob;
    }

    public static Patient makePatient(String line) {
        try {
            Scanner sc = new Scanner(line);
            sc.useDelimiter(",\\s*");

            String last = sc.next();
            String first = sc.next();
            String dobText = sc.next();

            Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobText);
            Name name = new Name(first, last);

            PatientIdentity id1 = new PatientIdentity(name, dob);
            return new Patient(id1);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString(){
        return "identity: " + id;
    }
}
