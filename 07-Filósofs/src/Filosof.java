import java.util.Random;

public class Filosof extends Thread {
    private static final Random RANDOM = new Random();

    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private int gana = 0;

    public Filosof(String name) {
        setName(name);
    }

    private void menjar() throws InterruptedException {
        System.out.format("Filòsof: %s menja%n", getName());
        Thread.sleep(1000 + RANDOM.nextInt(1000));
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
                    if (forquillaEsquerra.agafar()) {

                        System.out.format(
                                "Filòsof: %s agafa la forquilla esquerra %d%n",
                                getName(),
                                forquillaEsquerra.getNumero());

                        if (forquillaDreta.agafar()) {
                            System.out.format(
                                    "Filòsof: %s agafa la forquilla dreta %d%n",
                                    getName(),
                                    forquillaDreta.getNumero());

                            menjar();
                            menjat = true;
                        } else {
                            forquillaEsquerra.deixar();

                            System.out.format(
                                    "Filòsof: %s deixa l'esquerra (%d) i espera (dreta ocupada)%n",
                                    getName(),
                                    forquillaEsquerra.getNumero());

                            gana++;
                            System.out.format(
                                    "Filòsof: %s gana=%d%n",
                                    getName(),
                                    gana);

                            Thread.sleep(500 + RANDOM.nextInt(500));
                        }
                    } else {
                        Thread.sleep(500 + RANDOM.nextInt(500));
                    }
                }
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

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }
}