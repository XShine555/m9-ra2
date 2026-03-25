public class Client {
    private final String nom;

    public Client(int id) {
        this.nom = String.format("Client-%d", id);
    }

    public void tallarseElCabell() {
        System.out.format("Tallan cabell a %s%n",
                nom);
    }

    public String getNom() {
        return nom;
    }
}