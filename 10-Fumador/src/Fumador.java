import java.util.Random;

public class Fumador extends Thread {
    private static final Random RANDOM = new Random();

    private final Estanc estanc;
    private final int id;
    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;
    private int numFumats;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
        setName(String.format("Fumador %s", this.id));
    }

    public void fuma() {
        tabac = null;
        llumi = null;
        paper = null;
        numFumats++;
        System.out.format(
            "%s fumant%n",
            getName()
        );
    }

    @Override
    public void run() {
        while (numFumats < 3) {
            if (tabac == null) {
                tabac = estanc.venTabac();
                System.out.format(
                        "%s comprant Tabac%n",
                        getName());
            }
            if (llumi == null) {
                llumi = estanc.venLlumi();
                System.out.format(
                        "%s comprant Llumi%n",
                        getName());
            }
            if (paper == null) {
                paper = estanc.venPaper();
                System.out.format(
                        "%s comprant Paper%n",
                        getName());
            }
            fuma();
            int sleepTime = RANDOM.nextInt(500, 1500);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.format(
                    "%s ha fumat %d vegades%n",
                    getName(),
                    numFumats);
        }
    }
}
