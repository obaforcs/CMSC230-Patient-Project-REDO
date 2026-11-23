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
        return null;
    }
}
