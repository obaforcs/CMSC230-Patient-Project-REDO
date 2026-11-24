import org.junit.jupiter.api.Test;

public class HashExperimentTest {

    @Test
    public void compareHashes() {
        int[] sizes = {503, 1009, 2003, 5009};

        for (int s : sizes) {
            ContraindicationTable t8 = new ContraindicationTable(s, false);
            t8.loadFromFile("interactions.csv");

            ContraindicationTable t4 = new ContraindicationTable(s, true);
            t4.loadFromFile("interactions.csv");

            System.out.println("size " + s + ":  8-letter = " + t8.getProbes()
                    + "   4-letter = " + t4.getProbes());
        }
    }
}