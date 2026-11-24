import java.io.File;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ContraindicationTable {
    private final int size;
    private final boolean useFour;
    private Contraindications[] table;
    private long probes = 0;

    public ContraindicationTable() {
        this(503, false);
    }

    public ContraindicationTable(int size, boolean useFour) {
        this.size = size;
        this.useFour = useFour;
        table = new Contraindications[size];
    }

    public long getProbes() {
        return probes;
    }

    private int findSlot(String name) {
        long h = useFour ? hash4(name) : hash(name);
        int i = (int) (h % size);

        while (table[i] != null) {
            if (table[i].getMedName().equals(name)){
                return i;
            } else {
                probes++;
            }
            i = (i + 1) % size;
        }
        return i;
    }

    public Contraindications find(String name) {
        return table[findSlot(name)];
    }

    private Contraindications getOrCreate(String name) {
        int slot = findSlot(name);
        if (table[slot] == null) {
            table[slot] = new Contraindications(name);
        }
        return table[slot];
    }

    public void addPair(String a, String b) {
        getOrCreate(a).addContraindication(b);

        if (!a.equals(b)) {
            getOrCreate(b).addContraindication(a);
        }
    }

    public boolean loadFromFile(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",\\s*");
                if (parts.length != 2){
                    continue;
                }
                addPair(parts[0], parts[1]);
            }
            sc.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static long daysBetween(Date earlier, Date later) {
        long diffMillis = later.getTime() - earlier.getTime();
        return TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
    }

    private static boolean isCurrent(Prescription pr, Date today) {
        long days = daysBetween(pr.getDateOfIssue(), today);
        if (days >= 0 && days <= 365){
            return true;
        }
        return false;
    }

    public Contraindications contraindicatedFor(Patient pat, Date today) {
        Contraindications result = new Contraindications(pat.getIdentity().toString());

        PrescriptionList.Iterator it = pat.getPrescriptions().new Iterator();
        Prescription pr = it.next();
        while (pr != null) {
            if (isCurrent(pr, today)){
                Contraindications c = find(pr.getName());
                if (c != null){
                    PrescriptionList.Iterator cit = c.getList().new Iterator();
                    Prescription bad = cit.next();
                    while (bad != null) {
                        result.addContraindication(bad.getName());
                        bad = cit.next();
                    }
                }
            }
            pr = it.next();
        }
        return result;
    }

    public boolean isContraindicated(Patient pat, Prescription proposed) {
        Contraindications bad = contraindicatedFor(pat, proposed.getDateOfIssue());

        PrescriptionList.Iterator it = bad.getList().new Iterator();
        Prescription p = it.next();
        while (p != null) {
            if(p.getName().equals(proposed.getName())){
                return true;
            }
            p = it.next();
        }
        return false;
    }


    public static long hash(String name) {
        int len = name.length();
        int[] spots = {0, 1, len/3, len/2, len/2 + 1, 2*len/3, len - 2, len - 1};

        long key = 0;
        for (int i = 0; i < 8; i++) {
            byte b = (byte) name.charAt(spots[i]);
            key |= ((long) b) << (8 * i);
        }
        return key;
    }

    public static long hash4(String name) {
        int len = name.length();
        int[] spots = {0, len/3, 2*len/3, len - 1};

        long key = 0;
        for (int i = 0; i < 4; i++) {
            byte b = (byte) name.charAt(spots[i]);
            key |= ((long) b) << (8 * i);
        }
        return key;
    }
}
