public class Organitzador {
    private static final int MAX_PLACES = 5;
    
    private static final Esdeveniment ESDEVENIMENT = new Esdeveniment(MAX_PLACES);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Assistent("Assistent-" + i, ESDEVENIMENT).start();
        }
    }
}
