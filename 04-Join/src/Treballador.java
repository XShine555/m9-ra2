
import java.util.Random;

public class Treballador extends Thread {
    private final Random rnd = new Random();

    private final int edat_inici_treball;
    private final int edat_fi_treball;
    private int edat_actual;

    private final float sou_anual_brut;
    private float cobrat;

    public Treballador(
            String nom,
            int edat_inici_treball,
            int edat_fi_treball,
            float sou_anual_brut) {
        setName(nom);
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
        this.sou_anual_brut = sou_anual_brut;
        this.edat_actual = 0;
        this.cobrat = 0.0f;
    }

    public float getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edat_actual;
    }

    public void cobra() {
        cobrat += sou_anual_brut / 12;
    }

    public void pagaImpostos() {
        cobrat -= (sou_anual_brut / 12) * 0.24f;
    }

    @Override
    public void run() {
        while (edat_actual < edat_fi_treball) {
            if (edat_actual >= edat_inici_treball) {
                for (int mes = 0; mes < 12; mes++) {
                    cobra();
                    pagaImpostos();
                }
            }
            edat_actual++;
        }
    }
}