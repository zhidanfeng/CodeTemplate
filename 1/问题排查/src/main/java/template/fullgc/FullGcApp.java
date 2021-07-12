package template.fullgc;

public class FullGcApp {
    public static double slowpoke(int iterations) {
        double d = 0;
        for (int j = 1; j < iterations; j++) {
            d += Math.log(Math.E * j);
        }
        return d;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                double sideEffect = 0;
                for (int i = 0; i < 10000; i++) {
                    sideEffect = slowpoke(999999999);
                }
                System.out.println("result = " + sideEffect);
            }
        };
        thread.start();

        new Thread(){
            @Override
            public void run() {
                long timestamp = System.currentTimeMillis();
                while (true){
                    System.out.println("Delay " + (System.currentTimeMillis() - timestamp));
                    timestamp = System.currentTimeMillis();
                    //trigger stop-the-world
                    System.gc();
                }
            }
        }.start();
        thread.join();
    }
}
