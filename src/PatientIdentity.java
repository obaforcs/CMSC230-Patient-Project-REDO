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

    public boolean match(PatientIdentity other){
        Name myName = this.name;
        Name theirName = other.getName();
        boolean nameSame = myName.match(theirName);

        Date myDob = this.dateOfBirth;
        Date theirDob = other.getDateOfBirth();
        boolean dobSame = myDob.equals(theirDob);

        return nameSame && dobSame;
    }

    public boolean isLessThan(PatientIdentity other){
        Name myName = this.name;
        Name theirName = other.getName();

        if (myName.isLessThan(theirName)){
            return true;
        }

        if (myName.match(theirName)){
            Date myDob = this.dateOfBirth;
            Date theirDob = other.getDateOfBirth();
            int dobCompare = myDob.compareTo(theirDob);

            if (dobCompare < 0){
                return true;
            }
        }
        return false;

    }
}
