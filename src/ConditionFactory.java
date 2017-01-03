import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by SilenceDut on 2016/12/8.
 */



public class ConditionFactory implements Factory{
    private Queue<String> allProducts = new LinkedList<>();
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition notFull = reentrantLock.newCondition();
    private Condition notEmpty = reentrantLock.newCondition();
    private int MAX_SIZE = 4;


    public void produce() {

        reentrantLock.lock();

        while (allProducts.size() >= MAX_SIZE) {
            System.out.println("仓库已满,暂时无法生产");
            try {
                notFull.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        allProducts.add("new Product");
        notEmpty.signalAll();

        System.out.println("当前仓库产品数量为" + allProducts.size());
        reentrantLock.unlock();

    }

    public synchronized void consume() {

        reentrantLock.lock();

        while (allProducts.size() <= 0) {
            System.out.println("现在无任何产品,无法消费");
            try {
               notEmpty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        notFull.signalAll();

        allProducts.remove();

        System.out.println("消费过后当前仓库产品数量为" + allProducts.size());

        reentrantLock.unlock();

    }

}
