import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {

    public static Barberia instance;

    private final Queue<Client> salaEspera = new LinkedList<>();
    private final int maxCadires;
    public final Object condBarber = new Object();

    public Barberia(int maxCadires) {
        this.maxCadires = maxCadires;
        instance = this;
    }

    public Client seguentClient() {
        synchronized (salaEspera) {
            if (salaEspera.isEmpty()) {
                return null;
            }
            return salaEspera.poll();
        }
    }

    public void entrar(Client c) {
        synchronized (salaEspera) {
            if (salaEspera.size() >= maxCadires) {
                System.out.format("No queden cadires, client %s se'n va%n",
                        c.getNom());
                return;
            }
            salaEspera.add(c);
            System.out.format("Client %s en espera%n",
                    c.getNom());
            synchronized (condBarber) {
                condBarber.notify();
            }
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                entrar(new Client(i));
                Thread.sleep(500);
            }

            Thread.sleep(10000);

            for (int i = 11; i <= 20; i++) {
                entrar(new Client(i));
                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
        Barber barber = new Barber("Pepe");

        barber.start();
        barberia.start();
    }
}