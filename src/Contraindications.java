import java.util.Date;

public class Contraindications {
    private String medName;
    private PrescriptionList list = new PrescriptionList();

    public Contraindications(String name) {
        medName = name;
    }

    public String getMedName() {
        return medName;
    }

    public PrescriptionList getList(){
        return list;
    }

    public void addContraindication(String otherMed) {
        PrescriptionList.Iterator it = list.new Iterator();
        Prescription p = it.next();
        while (p != null) {
            if (p.getName().equals(otherMed)){
                return;
            }
            p = it.next();
        }

        Prescription pr = new Prescription(otherMed, new Date(0), 0, "");
        list.add(pr);
    }
}