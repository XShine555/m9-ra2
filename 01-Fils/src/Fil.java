public class Fil implements Runnable {
    private final String text;
    private final boolean strict;

    public Fil(String text) {
        this(text, false);
    }

    public Fil(String text, boolean strict) {
        this.text = text;
        this.strict = strict;
    }

    @Override
    public void run() {
        for (int i = 1; i < 10; i++) {
            System.out.println(
                String.format("%s %s", text, i)
            );

            if (!strict)
                for (int y = 0; y < 10_000; y++) { }
            else
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) { }
        }

        System.out.println(
            String.format("Acaba el fil %s", text)
        );
    }
}