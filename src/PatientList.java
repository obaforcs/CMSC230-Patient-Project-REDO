import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PatientList {
    private final int MAX_PATIENTS = 1000;
    private Patient[] patientArray;
    private int nextEmpty = 0;

    public PatientList() {
        patientArray = new Patient[MAX_PATIENTS];
    }

    private boolean addOrdered (Patient pat){
        if (nextEmpty == MAX_PATIENTS){
            return false;
        }
        int i = nextEmpty - 1;

        while (i>= 0 && pat.getIdentity().isLessThan(patientArray[i].getIdentity())){
            patientArray[i + 1] = patientArray[i];
            i--;
        }
        patientArray[i+1] = pat;
        nextEmpty++;
        return true;
    }

    private boolean addUnsorted(Patient pat) {
        if (nextEmpty == MAX_PATIENTS){
            return false;
        }
        patientArray[nextEmpty] = pat;
        nextEmpty++;
        return true;
    }

    public boolean add(Patient pat) {
        return addOrdered(pat);
    }

    private Patient binarySearch (PatientIdentity id){
        int high = nextEmpty - 1;
        int low = 0;

        while (low <= high){
            int mid = (low+high)/2;
            if (id.match(patientArray[mid].getIdentity())){
                return patientArray[mid];
            } else if (id.isLessThan(patientArray[mid].getIdentity())){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return null;
    }

    public Patient find(PatientIdentity id) {
        return binarySearch(id);
    }

    public boolean saveToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            Iterator it = new Iterator();
            Patient p = it.next();
            while (p != null) {
                writer.write(p.toCSV() + "\n");
                p = it.next();
            }

            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean importFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Patient p = Patient.makePatient(line);
                if (p != null){
                    addUnsorted(p);
                }
            }

            scanner.close();
            // sort everything (merge sort, we'll write it next)
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public class Iterator {
        private int current;

        public Iterator() {
            current = 0;
        }

        public Patient next() {
            if (current == nextEmpty) {
                return null;
            } else {
                Patient p = patientArray[current];
                current++;
                return p;
            }
        }

    }
}
