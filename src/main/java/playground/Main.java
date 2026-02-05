package playground;

import java.util.Arrays;

class Main {

    public static void main(String[] args) throws InterruptedException {
        // array init
        var arr = new int[1024];
        Arrays.fill(arr, 1);
        // warmup
        System.out.println("Copying finished: " + copyArray(arr));
        System.out.println("Copying finished: " + copyArray(arr));
        System.out.println("Power calculation finished: " + power());
        System.out.println("Power calculation finished: " + power());
        // fully optimized
        System.out.println("Copying finished: " + copyArray(arr));
        System.out.println("Power calculation finished: " + power());
        Thread.sleep(60000);
    }

    private static long copyArray(int[] arr) {
        var result = 0L;
        for (int i = 0; i < 1_000_000; i++) {
            var copy = new int[arr.length];
            System.arraycopy(arr, 0, copy, 0, arr.length);
            result += copy[0];
        }
        return result;
    }

    private static long power() {
        var result = 0L;
        for (int i = 0; i < 1_000_000; i++) {
            result += (long) Math.pow(i, 2.0);
        }
        return result;
    }

}