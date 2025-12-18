
import java.util.Random;

public class Futbolista extends Thread {
    private static final int NUM_JUGADORS = 11;
    private static final int NUM_TIRADES = 20;
    private static final float PROBABILITAT = 0.5f;

    private int ngols = 0;
    private int ntirades = 0;

    public int getGols() {
        return ngols;
    }

    public int getTirades() {
        return ntirades;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (; ntirades < NUM_TIRADES; ntirades++) {
            if (random.nextFloat() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    public static void main(String[] args) {
        String[] players = {
                "Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo",
                "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBappé"
        };
        int[] golsTotals = new int[NUM_JUGADORS];

        System.out.println("Inici dels xuts ----------------");
        for (int i = 0; i < NUM_JUGADORS; i++) {
            Futbolista futbolista = new Futbolista();
            futbolista.start();

            try {
                futbolista.join();
            } catch (InterruptedException e) {
                System.out.format("InterruptedException: %s%n", e.getMessage());
            }

            golsTotals[i] = futbolista.getGols();
        }
        System.out.println("Fi dels xuts --------------------");
        System.out.println("--- Estadístiques ---");
        for (int i = 0; i < NUM_JUGADORS; i++) {
            System.out.format("%s       -> %d%n", players[i], golsTotals[i]);
        }
    }
}
