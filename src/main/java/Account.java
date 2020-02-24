import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private Long id;

    private int balance;

    public AtomicInteger getFailedTransaction() {
        return failedTransaction;
    }

    private AtomicInteger failedTransaction = new AtomicInteger(0);

    public void incFailedTransaction() {
        int old = failedTransaction.get();
        int newValue = old + 1;
        failedTransaction.compareAndSet(old, newValue);
    }

    public Lock getLock() {
        return lock;
    }

    private Lock lock = new ReentrantLock();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Account(Long id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }



    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

}
