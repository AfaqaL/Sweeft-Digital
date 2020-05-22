public class Fibonacci {
    public static long getFibonacci(long n){
        return fibonacciHelper(n, 1, 1);
    }

    private static long fibonacciHelper(long n, int first, int second) {
        if(n <= 1) return second;

        return fibonacciHelper(n - 1, second, first + second);
    }
}
