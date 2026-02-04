import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    private List<Assistent> assistents = new ArrayList<>();
    private int availablePlaces;

    public Esdeveniment(int maxPlaces) {
        this.availablePlaces = maxPlaces;
    }

    public synchronized void ferReserva(Assistent assistent) {
        while (availablePlaces < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }

        assistents.add(assistent);
        availablePlaces--;
        System.out.format("%s ha fet una reserva. Places disponibles: %d%n", assistent.getName(), availablePlaces);
    }

    public synchronized void cancelaReserva(Assistent assistent) {
        if (!assistents.remove(assistent)) {
            System.out.format("%s no ha pogut cancel·lar una reserva inexistent. Places disponibles: %d%n", assistent.getName(), availablePlaces);
            return;
        }
        availablePlaces++;
        notifyAll();
    }
}