public class MainDemoFil {
    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        System.out.format("Prioritat -> %s, Nom -> %s%n", currentThread.getPriority(), currentThread.getName());
        System.out.format("toString() -> %s%n", currentThread.toString());
    }
}
