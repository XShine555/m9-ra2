import java.util.ArrayList;
import java.util.List;

public class Taula {
    private List<Filosof> comensals = new ArrayList<>();
    private List<Forquilla> forquilles = new ArrayList<>();

    public Taula(int numFilosofs) {
        for (int i = 0; i < numFilosofs; i++) {
            forquilles.add(new Forquilla(i));
        }

        for (int i = 0; i < numFilosofs; i++) {
            Filosof f = new Filosof(i);

            f.setForquillaEsquerra(forquilles.get(i));
            f.setForquillaDreta(forquilles.get((i + 1) % numFilosofs));

            comensals.add(f);
        }
    }

    public void showTaula() {
        for (Filosof f : comensals) {
            System.out.println(
                    String.format(
                            "Comensal: %s esq:%d dret:%d",
                            f.getName(),
                            f.getForquillaEsquerra().getNumero(),
                            f.getForquillaDreta().getNumero()));
        }
    }

    public void cridarATaula() {
        for (Filosof filosof : comensals) {
            filosof.start();
        }
    }

    public Filosof getComensal(int index) {
        return comensals.get(index);
    }

    public static void main(String[] args) {
        Taula taula = new Taula(5);
        taula.cridarATaula();
    }
}