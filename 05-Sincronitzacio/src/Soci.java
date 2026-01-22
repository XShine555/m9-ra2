
import java.util.Random;

public class Soci extends Thread {
    private final Random random;
    private final float aportacio = 10f;
    private final int esperaMax = 100;
    private final int maxAnys = 10;
    private final Compte compte;

    public Compte getCompte() {
        return compte;
    }

    public Soci() {
        random = new Random();
        compte = Compte.getInstance();
    }

    @Override
    public void run() {
        for (int any = 0; any < 12; any++) {
            for (int mes = 0; mes < 12; mes++) {
                if (mes % 2 == 0) {
                    synchronized (compte) {
                        float saldoActual = compte.getSaldo();
                        compte.setSaldo(saldoActual + aportacio);
                    }                    
                } else {
                    synchronized (compte) {
                        float saldoActual = compte.getSaldo();
                        compte.setSaldo(saldoActual - aportacio);
                    }
                }

                try {
                    Thread.sleep(random.nextInt(1, esperaMax));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
