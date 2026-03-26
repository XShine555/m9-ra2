import java.util.Random;

public class Dona extends Thread {
    private static final Random RANDOM = new Random();

    private String nom;
    private BanyUnisex bany;

    public Dona(String nom, BanyUnisex bany) {
        this.nom = nom;
        this.bany = bany;
    }

    public void entraDona() throws InterruptedException {
        bany.entraDona();
    }

    public void surtDona() throws InterruptedException {
        bany.surtDona();
        System.out.format("%s ha acabat d'utilitzar el bany.%n", nom);
    }

    public void utilitzaLavabo() throws InterruptedException {
        Thread.sleep(RANDOM.nextInt(2000, 3000));
    }

    @Override
    public void run() {
        for (;;) {
            try {
                entraDona();
                utilitzaLavabo();
                surtDona();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
