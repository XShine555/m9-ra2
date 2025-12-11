public class PrincipalEstricte {
    public static void main(String[] args) throws InterruptedException {
        Fil pepe = new Fil("Pepe", true);
        Fil Juan = new Fil("Juan", true);

        Thread pepeThread = new Thread(pepe);
        Thread juanThread = new Thread(Juan);

        pepeThread.start();
        juanThread.start();

        System.out.println("Acaba thread main");
    }
}
