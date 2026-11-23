public class PatientList {
    private final int MAX_PATIENTS = 1000;
    private Patient[] patientArray;
    private int nextEmpty = 0;

    public PatientList() {
        patientArray = new Patient[MAX_PATIENTS];
    }

    public boolean add (Patient pat){
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

    public Patient find (PatientIdentity id){
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
