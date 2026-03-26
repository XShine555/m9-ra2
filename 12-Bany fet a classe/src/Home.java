import java.util.Random;

public class Home extends Thread {
    private static final Random RANDOM = new Random();

    private String nom;
    private BanyUnisex bany;

    public Home(String nom, BanyUnisex bany) {
        this.nom = nom;
        this.bany = bany;
    }

    public void entraHome() throws InterruptedException {
        bany.entraHome();
    }

    public void surtHome() throws InterruptedException {
        bany.surtHome();
        System.out.format("%s ha acabat d'utilitzar el bany.%n", nom);
    }

    public void utilitzaLavabo() throws InterruptedException {
        Thread.sleep(RANDOM.nextInt(1000, 2000));
    }

    @Override
    public void run() {
        for (;;) {
            try {
                entraHome();
                utilitzaLavabo();
                surtHome();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
