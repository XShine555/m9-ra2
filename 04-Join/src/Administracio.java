public class Administracio {
    private static final int POBLACIO_OBJECTIU = 50;

    private int num_poblacio_activa = 50;
    private final Treballador[] treballadors = new Treballador[POBLACIO_OBJECTIU];

    public Administracio() {
        for (int i = 0; i < POBLACIO_OBJECTIU; i++) {
            treballadors[i] = new Treballador(
                    String.format("Ciutadà-%02d", i),
                    20,
                    65,
                    25000.0f);
        }
    }

    public void iniciaTreball() {
        for (Treballador t : treballadors) {
            t.start();
        }

        for (Treballador t : treballadors) {
            try {
                t.join();
                System.out.format(
                        "%s -> edat: %d / total: %.2f%n",
                        t.getName(),
                        t.getEdat(),
                        t.getCobrat());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Administracio admin = new Administracio();
        admin.iniciaTreball();
    }
}
