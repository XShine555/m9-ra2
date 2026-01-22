public class Compte {
    private float saldo;

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    private Compte() {
    }

    private static Compte staticInstance;

    public static Compte getInstance() {
        if (staticInstance == null)
            staticInstance = new Compte();
        return staticInstance;
    }
}
