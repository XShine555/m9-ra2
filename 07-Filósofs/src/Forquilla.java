public class Forquilla {
    private int numero;
    private boolean enUs = false;

    public Forquilla(int numero) {
        this.numero = numero;
    }

    public synchronized boolean agafar() {
        if (!enUs) {
            enUs = true;
            return true;
        }
        return false;
    }

    public synchronized void deixar() {
        enUs = false;
    }

    public boolean getEnUs() {
        return enUs;
    }    

    public int getNumero() {
        return numero;
    }
}
