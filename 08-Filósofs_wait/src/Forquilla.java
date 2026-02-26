import java.util.Random;

public class Forquilla {
    private static final Random RANDOM = new Random();
    private final int numero;

    private static final int LLIURE = -1;
    private int propietari;

    public Forquilla(int numero) {
        this.numero = numero;
        this.propietari = LLIURE;
    }

    public synchronized boolean agafar(int idFilosof) {
        while (propietari != LLIURE) {
            try {
                wait(RANDOM.nextInt(500, 1000));
            } catch (InterruptedException | RuntimeException e) {
                Thread.currentThread().interrupt();
            }
        }
        propietari = idFilosof;
        return true;
    }

    public synchronized void deixar() {
        propietari = LLIURE;
        notifyAll();
    }

    public static int getLliure() {
        return LLIURE;
    }

    public int getPropietari() {
        return propietari;
    }

    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }

    public int getNumero() {
        return numero;
    }
}