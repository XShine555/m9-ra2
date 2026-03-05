import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private int num;
    private ReentrantLock bloqueig = new ReentrantLock();

    public Forquilla(int num) {
        this.num = num;
    }

    public boolean agafar() {
        return bloqueig.tryLock();
    }

    public boolean deixar() {
        if (bloqueig.isHeldByCurrentThread()) {
            bloqueig.unlock();
            return true;
        }
        return false;
    }

    public int getNum() {
        return num;
    }
}