public class Name {
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String fullName() {
        return lastName + ", " + firstName;
    }

    public boolean match(Name other){
        String myFirst = this.firstName.toLowerCase();
        String theirFirst = other.getFirstName().toLowerCase();
        boolean firstSame = myFirst.equals(theirFirst);

        String myLast = this.lastName.toLowerCase();
        String theirLast = other.getLastName().toLowerCase();
        boolean lastSame = myLast.equals(theirLast);

        return firstSame && lastSame;
    }

    public boolean isLessThan(Name other){
        String myLast = this.lastName.toLowerCase();
        String theirLast = other.getLastName().toLowerCase();
        int lastCompare = myLast.compareTo(theirLast);

        if (lastCompare < 0){
            return true;
        }
        if (lastCompare > 0){
            return false;
        }

        String myFirst = this.firstName.toLowerCase();
        String theirFirst = other.getFirstName().toLowerCase();
        int firstCompare = myFirst.compareTo(theirFirst);

        if (firstCompare < 0){
            return true;
        }
        if (firstCompare > 0){
            return false;
        }
        return false;
    }

    @Override
    public String toString(){
        return fullName();
    }
}
