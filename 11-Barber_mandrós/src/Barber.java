import java.util.Random;

public class Barber extends Thread {
    private static final Random RANDOM = new Random();

    public Barber(String nom) {
        setName(nom);
    }

    @Override
    public void run() {
        while (true) {
            Client c = Barberia.instance.seguentClient();
            if (c == null) {
                System.out.format("Ningú en espera. Barber %s dormint%n",
                        getName());
                synchronized (Barberia.instance.condBarber) {
                    try {
                        Barberia.instance.condBarber.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                continue;
            }

            System.out.format("Li toca al client %s%n",
                    c.getNom());
            c.tallarseElCabell();

            try {
                Thread.sleep(900 + RANDOM.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}