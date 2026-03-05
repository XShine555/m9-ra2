import java.util.Random;

public class Filosof extends Thread {
    private static final Random RANDOM = new Random();

    private int iniciGana;
    private int fiGana;
    private int gana;

    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;

    private int numComensal;

    public Filosof(int numComensal) {
        this.numComensal = numComensal;
        setName("Fil " + numComensal);
    }

    private void menjar() throws InterruptedException {
        fiGana = (int)(System.currentTimeMillis() / 1000);
        gana = calcularGana();
        System.out.format("%s menja amb gana %d%n", getName(), gana);
        Thread.sleep(RANDOM.nextInt(1000, 2000));
        System.out.format("%s ha acabat de menjar%n", getName());

        forquillaEsquerra.deixar();
        forquillaDreta.deixar();
        gana = 0;
    }

    private void pensar() throws InterruptedException {
        System.out.format("%s pensant%n", getName());
        Thread.sleep(1000 + RANDOM.nextInt(1000));
    }

    public int calcularGana() {
        return fiGana - iniciGana;
    }

    public void resetGana() {
        iniciGana = 0;
        gana = 0;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                iniciGana = (int)(System.currentTimeMillis() / 1000);
                boolean menjat = false;
                while (!menjat) {
                    if (!agafarForquillaEsquerra()) {
                        Thread.sleep(RANDOM.nextInt(500, 1000));
                        continue;
                    }
                    if (!agafarForquillaDreta()) {
                        forquillaEsquerra.deixar();
                        Thread.sleep(RANDOM.nextInt(500, 1000));
                        continue;
                    }
                    System.out.format(
                        "%s té forquilles esq(%d) i dreta(%d)%n",
                        getName(),
                        forquillaEsquerra.getNum(),
                        forquillaDreta.getNum()
                    );
                    menjar();
                    menjat = true;
                }

                Thread.sleep(RANDOM.nextInt(1000, 2000));
                pensar();
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
        return forquillaDreta.agafar();
    }

    public boolean agafarForquillaEsquerra() {
        return forquillaEsquerra.agafar();
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
                forquillaEsquerra.getNum(),
                forquillaDreta.getNum());
    }
}