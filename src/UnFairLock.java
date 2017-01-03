import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by SilenceDut on 2017/1/3.
 */
public class UnFairLock implements Runnable {
    private ReentrantLock reentrantLock;
    private int index;

    public UnFairLock(ReentrantLock reentrantLock,int index) {
        this.reentrantLock = reentrantLock;
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println(index+"tryLock");
        reentrantLock.lock();
        System.out.println(index+"locked");
        try {
            Thread.sleep(5000);
            reentrantLock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
