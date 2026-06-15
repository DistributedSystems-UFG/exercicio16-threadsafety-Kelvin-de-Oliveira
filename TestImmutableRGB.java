public class TestImmutableRGB {

    private static volatile ImmutableRGB color;

    public static void main(String[] args) throws InterruptedException {
        color = new ImmutableRGB(100, 100, 100, "meio-cinza");

        Runnable inverter = () -> {
            for (int i = 0; i < 1000; i++) {
                ImmutableRGB current = color;
                color = current.invert();
            }
        };

        Runnable reader = () -> {
            for (int i = 0; i < 1000; i++) {
                ImmutableRGB current = color; // captura referência local
                int rgb = current.getRGB();
                String name = current.getName();
                // rgb e name SEMPRE correspondem ao mesmo objeto
            }
        };

        Thread t1 = new Thread(inverter);
        Thread t2 = new Thread(reader);
        Thread t3 = new Thread(inverter);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        ImmutableRGB finalColor = color;
        System.out.println("Final RGB: " + finalColor.getRGB());
        System.out.println("Final Name: " + finalColor.getName());
    }
}