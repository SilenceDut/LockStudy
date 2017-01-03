


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by SilenceDut on 2016/12/8.
 */
public class Test {

    private static Executor executor = Executors.newCachedThreadPool();


    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
//        Factory factory = new ConditionFactory();
//
//        for(int i=0;i<10;i++) {
//            Consumer consumer = new Consumer(factory);
//            Producer producer = new Producer(factory);
//            executor.execute(consumer);
//            executor.execute(producer);
//        }
        reentrantLock.lock();
        for (int i = 0; i < 10; i++) {
            UnFairLock unFairLock = new UnFairLock(reentrantLock, i);
            executor.execute(unFairLock);
        }
        try {
            //保持锁为了让其他线程阻塞形成node阻塞队列
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantLock.unlock();


    }


}
