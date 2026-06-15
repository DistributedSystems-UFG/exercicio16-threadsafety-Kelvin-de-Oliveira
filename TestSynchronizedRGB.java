public class TestSynchronizedRGB {

    public static void main(String[] args) throws InterruptedException {
        SynchronizedRGB color = new SynchronizedRGB(100, 100, 100, "meio-cinza");

        Runnable inverter = () -> {
            for (int i = 0; i < 1000; i++) {
                color.invert();
            }
        };

        Runnable reader = () -> {
            for (int i = 0; i < 1000; i++) {
                int rgb = color.getRGB();
                String name = color.getName();
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

        System.out.println("Final RGB: " + color.getRGB());
        System.out.println("Final Name: " + color.getName());
    }
}