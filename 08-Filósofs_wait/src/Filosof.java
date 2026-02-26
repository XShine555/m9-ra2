import java.util.Random;

public class Filosof extends Thread {
    private static final Random RANDOM = new Random();

    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private int gana = 0;
    private final int numComensal;

    public Filosof(int numComensal) {
        this.numComensal = numComensal;
        setName("Fil " + numComensal);
    }

    private void menjar() throws InterruptedException {
        System.out.format("Filòsof: %s menja%n", getName());
        Thread.sleep(RANDOM.nextInt(1000, 2000));
        System.out.format("Filòsof: %s ha acabat de menjar%n", getName());

        forquillaEsquerra.deixar();
        forquillaDreta.deixar();
        gana = 0;
    }

    private void pensar() throws InterruptedException {
        System.out.format("Filòsof: %s pensant%n", getName());
        Thread.sleep(1000 + RANDOM.nextInt(1000));
    }

    @Override
    public void run() {
        for (;;) {
            try {
                pensar();

                boolean menjat = false;
                while (!menjat) {
                    if (agafarForquillaEsquerra()) {
                        if (agafarForquillaDreta()) {
                            menjar();
                            menjat = true;
                        } else {
                            forquillaEsquerra.deixar();
                            Thread.sleep(RANDOM.nextInt(500, 1000));
                        }

                    } else {
                        Thread.sleep(RANDOM.nextInt(500, 1000));
                    }

                }

                Thread.sleep(RANDOM.nextInt(1000, 2000));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setForquillaDreta(Forquilla forquillaDreta) {
        this.forquillaDreta = forquillaDreta;
    }

    public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
        this.forquillaEsquerra = forquillaEsquerra;
    }

    public boolean agafarForquillaDreta() {
        boolean resultat = forquillaDreta.agafar(numComensal);
        System.out.format(
                "Filòsof: %s agafa la forquilla dreta %d%n",
                getName(),
                forquillaDreta.getNumero());
        return resultat;
    }

    public boolean agafarForquillaEsquerra() {
        boolean resultat = forquillaEsquerra.agafar(numComensal);
        System.out.format(
                "Filòsof: %s agafa la forquilla esquerra %d%n",
                getName(),
                forquillaEsquerra.getNumero());
        return resultat;
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public void agafarForquilles() {
        agafarForquillaEsquerra();
        agafarForquillaDreta();
    }

    public void deixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();

        System.out.format(
                "Filòsof: %s deixa les forquilles %d i %d%n",
                getName(),
                forquillaEsquerra.getNumero(),
                forquillaDreta.getNumero());
    }
}