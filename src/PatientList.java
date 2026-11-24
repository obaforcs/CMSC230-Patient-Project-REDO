import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PatientList {
    private BinarySearchTree<PatientIdentity, Patient> tree = new BinarySearchTree<>();

    public PatientList() {
    }

    private boolean addOrdered (Patient pat){
        tree.add(pat);
        return true;
    }

    private boolean addUnsorted(Patient pat) {
        tree.add(pat);
        return true;
    }

    public boolean add(Patient pat) {
        return addOrdered(pat);
    }

    private Patient binarySearch (PatientIdentity id){
        return tree.find(id);
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

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean importPrescriptions(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                Patient temp = Patient.makePatient(line);
                if (temp == null) {
                    continue;
                }

                Patient real = find(temp.getIdentity());
                if (real == null) {
                    continue;
                }

                Prescription pr = Prescription.makePrescription(line);
                if (pr == null) {
                    continue;
                }

                real.getPrescriptions().add(pr);
            }

            scanner.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public class Iterator {
        private BinarySearchTree<PatientIdentity, Patient>.Iterator treeIt;

        public Iterator() {
            treeIt = tree.new Iterator();
        }

        public Patient next() {
            return treeIt.next();
        }
    }
}
