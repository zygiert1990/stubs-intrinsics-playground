package playground;

class Main {

    public static void main(String[] args) {
        System.out.println("Power calculation finished: " + power());
    }

    private static long power() {
        var result = 0L;
        for (int i = 0; i < 1_000_000; i++) {
            result += (long) Math.pow(i, 2.0);
        }
        return result;
    }

}
