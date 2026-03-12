import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {
    private static final Random RANDOM = new Random();

    private List<Tabac> tabacs;
    private List<Llumi> llumis;
    private List<Paper> papers;

    private boolean isOpen;

    public Estanc() {
        tabacs = new ArrayList<>();
        llumis = new ArrayList<>();
        papers = new ArrayList<>();

        isOpen = true;
    }

    private void nouSubministrament() {
        int result = RANDOM.nextInt(0, 3);
        if (result == 0) {
            addTabac();
            System.out.println("Afegint tabac");
        } else if (result == 1) {
            addLlumi();
            System.out.println("Afegint llumi");
        } else {
            addPaper();
            System.out.println("Afegint paper");
        }
    }

    private synchronized void addTabac() {
        tabacs.add(new Tabac());
        notifyAll();
    }

    private synchronized void addLlumi() {
        llumis.add(new Llumi());
        notifyAll();
    }

    private synchronized void addPaper() {
        papers.add(new Paper());
        notifyAll();
    }

    public synchronized Tabac venTabac() {
        while (tabacs.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Tabac tabac = tabacs.get(0);
        tabacs.remove(0);
        return tabac;
    }

    public synchronized Llumi venLlumi() {
        while (llumis.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Llumi llumi = llumis.get(0);
        llumis.remove(0);
        return llumi;
    }

    public synchronized Paper venPaper() {
        while (papers.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Paper paper = papers.get(0);
        papers.remove(0);
        return paper;
    }

    public void tancarEstanc() {
        isOpen = false;
        System.out.println("Estanc tancat");
    }

    @Override
    public void run() {
        while (isOpen) {
            nouSubministrament();
            int sleepTime = RANDOM.nextInt(500, 1500);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
