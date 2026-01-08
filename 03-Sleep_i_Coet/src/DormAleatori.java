
import java.util.Random;

public class DormAleatori extends Thread {
    private static final Random RANDOM = new Random();
    private final long currentMillis;

    public DormAleatori(String name) {
        setName(name);
        currentMillis = System.currentTimeMillis();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int randomInterval = RANDOM.nextInt(1000);

            System.out.format(
                    "%s (%d) a dormir %dms total %dms%n",
                    getName(),
                    i,
                    randomInterval,
                    (System.currentTimeMillis() - currentMillis));

            try {
                Thread.sleep(randomInterval);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");

        joan.start();
        pep.start();
    }
}
