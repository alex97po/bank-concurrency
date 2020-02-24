import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Operation {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FibonacciTask task = new FibonacciTask(6);
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        forkJoinPool.execute(task);
        int result = task.join();
        System.out.println(result);
//        final Account account1 = new Account(1L, 300000);
//        final Account account2 = new Account(2L, 2000);
//        ScheduledExecutorService scheduled = Executors.newSingleThreadScheduledExecutor();
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        ForkJoinPool forkJoinPool1 = ForkJoinPool.commonPool();
//        scheduled.schedule(Thread.currentThread(), 1, TimeUnit.SECONDS);
//        ExecutorService service = Executors.newFixedThreadPool(3);
//        List<Boolean> statistic = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            statistic.add(service.submit(new Transfer(account1, account2, 400)).get());
//        }
//        service.shutdown();
//
//        System.out.println(statistic);
//
//        new Thread(() -> transfer(account1, account2, 500)).start();
//
//        transfer(account2, account1, 300);
//        System.out.println("Account 1 balance: " + account1.getBalance());
//        System.out.println("Account 2 balance: " + account2.getBalance());
//        System.out.println("Account 1 failed: " + account1.getFailedTransaction());
//        System.out.println("Account 2 failed: " + account2.getFailedTransaction());
    }

    private static void transfer(Account from, Account to, int amount) {

    }
}
