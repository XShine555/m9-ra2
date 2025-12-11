public class PrincipalIguals {
    public static void main(String[] args) {
        Fil pepe = new Fil("Pepe");
        Fil Juan = new Fil("Juan");

        Thread pepeThread = new Thread(pepe);
        pepeThread.setPriority(Thread.MAX_PRIORITY);

        Thread juanThread = new Thread(Juan);
        juanThread.setPriority(Thread.MAX_PRIORITY);

        pepeThread.start();
        juanThread.start();

        System.out.println("Acaba thread main");
    }
}