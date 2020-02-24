import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Transfer implements Callable<Boolean> {

    private static final int LOCK_WAIT_SEC = 3;
    private final Random waitRandom = new Random();

    private final Account from;
    private final Account to;

    public Transfer(Account from, Account to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    private final int amount;

    @Override
    public Boolean call() throws Exception {
        try {
            if (from.getLock().tryLock(LOCK_WAIT_SEC, TimeUnit.SECONDS)) {
                System.out.println("Acquired " + from.getId() + " THREAD" + Thread.currentThread().getId());
                if (from.getBalance() < amount) {
                    throw new InsufficientFundsException("Not enough money on account " + from.getId());
                }
                try {
                    if (to.getLock().tryLock(LOCK_WAIT_SEC, TimeUnit.SECONDS)) {
                        System.out.println("Acquired " + to.getId() + " THREAD" + Thread.currentThread().getId());
                        try {
                            Thread.sleep(waitRandom.nextInt(2000));
                            from.withdraw(amount);
                            to.deposit(amount);
                            System.out.println("TRANSACTION SUCCESSFUL");
                            return true;
                        } finally {
                            to.getLock().unlock();
                        }
                    } else {
                        to.incFailedTransaction();
                        System.out.println("Couldn't send money from account " + from.getId() + " to account " + to.getId());
                    }
                } finally {
                    from.getLock().unlock();
                }
            } else {
                from.incFailedTransaction();
                System.out.println("Couldn't send money from account " + from.getId() + " to account " + to.getId());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
