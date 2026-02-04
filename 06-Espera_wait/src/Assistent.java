import java.util.Random;

public class Assistent extends Thread {
    private static final Random RANDOM = new Random();
    private Esdeveniment esdeveniment;

    public Assistent(String name, Esdeveniment esdeveniment) {
        setName(name);
        this.esdeveniment = esdeveniment;
    }

    @Override
    public void run() {
        for (;;) {
            if (RANDOM.nextInt(0, 100) < 70) {
                esdeveniment.ferReserva(this);
            } else {
                esdeveniment.cancelaReserva(this);
            }
            try {
                Thread.sleep(RANDOM.nextInt(0, 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
