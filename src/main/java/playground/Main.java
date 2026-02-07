package playground;

class Main {

    public static void main(String[] args) throws InterruptedException {
        // warmup
        System.out.println("Power calculation finished: " + power());
        System.out.println("Power calculation finished: " + power());
        // fully optimized
        System.out.println("Power calculation finished: " + power());
        Thread.sleep(60000);
    }

    private static long power() {
        var result = 0L;
        for (int i = 0; i < 1_000_000; i++) {
            result += (long) Math.pow(i, 2.0);
        }
        return result;
    }

}