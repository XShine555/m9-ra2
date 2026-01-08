
import java.util.Random;

public class Motor extends Thread {
    private static final Random RANDOM = new Random();

    private boolean hasChanged = false;
    private int potenciaObjectiu = 0;
    private int potencia = 0;

    public Motor(String name) {
        setName(name);
    }

    public void setPotencia(int p) {
        if (p == this.potenciaObjectiu)
            return;

        this.potenciaObjectiu = p;
    }

    public int getPotencia() {
        return potencia;
    }

    @Override
    public void run() {
        while (
            !hasChanged || potencia != 0 || potenciaObjectiu != 0
        ) {
            String estat = "FerRes";
            if (potencia < potenciaObjectiu) {
                potencia++;
                hasChanged = true;
                estat = "Incre.";
            } else if (potencia > potenciaObjectiu) {
                potencia--;
                hasChanged = true;
                estat = "Decre.";
            }
            try {
                Thread.sleep(
                        RANDOM.nextInt(1000, 2000));
            } catch (InterruptedException e) {
            }

            imprimirEstat(estat);
        }
    }

    public void imprimirEstat(String estat) {
        System.out.format(
                "%s: %s Objectiu=%d, Actual=%d%n",
                getName(),
                estat,
                potenciaObjectiu,
                potencia);
    }
}
