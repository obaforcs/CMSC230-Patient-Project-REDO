import java.io.File;
import java.util.Scanner;

public class ContraindicationTable {
    private static final int SIZE = 503;
    private Contraindications[] table = new Contraindications[SIZE];
    private long probes = 0;

    private int findSlot(String name) {
        int i = (int) (hash(name) % SIZE);

        while (table[i] != null) {
            if (table[i].getMedName().equals(name)){
                return i;
            } else {
                probes++;
            }
            i = (i + 1) % SIZE;
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
}
