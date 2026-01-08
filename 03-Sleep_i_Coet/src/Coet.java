
import java.util.Scanner;

public class Coet {

    private Motor[] motors = new Motor[4];

    public void arranca() {
        for (int i = 0; i < 4; i++) {
            motors[i] = new Motor("Motor " + i);
            motors[i].start();
        }
    }

    public void passaAPotencia(int p) {
        if (p < 0 || p > 10) {
            System.out.println("Potència incorrecta");
            return;
        }

        for (Motor m : motors) {
            m.setPotencia(p);
        }
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();

        Scanner sc = new Scanner(System.in);
        while (true) {
            int p = sc.nextInt();
            System.out.println("Passant a potència " + p);
            coet.passaAPotencia(p);

            if (p == 0) {
                break;
            }
        }
        sc.close();
    }
}