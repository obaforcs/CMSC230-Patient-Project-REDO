import java.util.Date;

public class PatientIdentity {
    private Name name;
    private Date dateOfBirth;

    public PatientIdentity (Name name, Date dateOfBirth){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Name getName(){
        return name;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }
}
