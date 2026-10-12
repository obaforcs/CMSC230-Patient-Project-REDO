public class Patient {
    private final PatientIdentity id;

    public Patient (PatientIdentity id){
        this.id = id;
    }

    public PatientIdentity getIdentity(){
        return id;
    }

    @Override
    public String toString(){
        return "identity: " + id;
    }
}
