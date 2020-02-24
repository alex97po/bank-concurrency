import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Integer> {

    private final int n;

    public FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) return n;
        System.out.println("number= " + n + " THREAD +" + Thread.currentThread().getId());
        FibonacciTask fcal1 = new FibonacciTask(n-1);
        fcal1.fork();
        FibonacciTask fcal2 = new FibonacciTask(n-2);
        int fcal1Join = fcal1.join();
        int fcal2Compute = fcal2.compute();
        System.out.println("number = " + n + " fcal.join() = " + fcal1Join + " fcal2Compute = " + fcal2Compute);
        return fcal2Compute + fcal1Join;
    }
}
