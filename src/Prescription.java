import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Prescription {
    private String name;
    private Date dateOfIssue;
    private int dosage;
    private String prescriber;

    public Prescription (String name, Date dateOfIssue, int dosage, String prescriber){
        this.name = name;
        this.dateOfIssue = dateOfIssue;
        this.dosage = dosage;
        this.prescriber = prescriber;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public int getDosage() {
        return dosage;
    }

    public String getPrescriber() {
        return prescriber;
    }

    public static Prescription makePrescription(String line) {
        try {
            Scanner sc = new Scanner(line);
            sc.useDelimiter(",\\s*");

            sc.next(); sc.next(); sc.next();   // skip last, first, dob

            String medicine = sc.next();

            String dateText = sc.next();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            fmt.setLenient(false);
            Date date = fmt.parse(dateText);

            int dosage = sc.nextInt();
            String prescriber = sc.next();

            return new Prescription(medicine, date, dosage, prescriber);
        } catch (Exception e) {
            return null;
        }
    }
}
