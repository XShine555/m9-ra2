import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    public static final String BANY_BUIT = "Bany buit";
    public static final String BANY_AMB_HOMES = "Bany amb homes";
    public static final String BANY_AMB_DONES = "Bany amb dones";
    public static final int CAPACITAT_MAXIMA = 3;

    private String estatActual = BANY_BUIT;
    private int ocupants = 0;

    private Semaphore capacitat = new Semaphore(CAPACITAT_MAXIMA, true);
    private ReentrantLock lockEstat = new ReentrantLock(true);

    public void entraHome() throws InterruptedException {
        capacitat.acquire();

        lockEstat.lock();
        try {
            while (estatActual.equals(BANY_AMB_DONES)) {
                lockEstat.unlock();
                Thread.sleep(50);
                lockEstat.lock();
            }

            ocupants++;
            estatActual = BANY_AMB_HOMES;
            System.out.format("Home entra al bany. Ocupants: %s%n", ocupants);

        } finally {
            lockEstat.unlock();
        }
    }

    public void entraDona() throws InterruptedException {
        capacitat.acquire();

        lockEstat.lock();
        try {
            while (estatActual.equals(BANY_AMB_HOMES)) {
                lockEstat.unlock();
                Thread.sleep(50);
                lockEstat.lock();
            }

            ocupants++;
            estatActual = BANY_AMB_DONES;
            System.out.format("Dona entra al bany. Ocupants: %s%n", ocupants);

        } finally {
            lockEstat.unlock();
        }
    }

    public void surtHome() {
        lockEstat.lock();
        try {
            ocupants--;
            System.out.format("Home surt del bany. Ocupants: %s%n", ocupants);

            if (ocupants == 0)
                estatActual = BANY_BUIT;

        } finally {
            lockEstat.unlock();
        }

        capacitat.release();
    }

    public void surtDona() {
        lockEstat.lock();
        try {
            ocupants--;
            System.out.format("Dona surt del bany. Ocupants: %s%n", ocupants);

            if (ocupants == 0)
                estatActual = BANY_BUIT;

        } finally {
            lockEstat.unlock();
        }

        capacitat.release();
    }

    public static void main(String[] args) {
        BanyUnisex bany = new BanyUnisex();
        for (int i = 0; i < 5; i++) {
            new Home("Home" + i, bany).start();
            new Dona("Dona" + i, bany).start();
        }
    }
}