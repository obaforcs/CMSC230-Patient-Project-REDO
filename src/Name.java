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
}
