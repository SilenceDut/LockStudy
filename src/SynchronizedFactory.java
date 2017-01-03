
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by SilenceDut on 2016/12/8.
 */
public class SynchronizedFactory implements Factory{

    private Queue<String> allProducts = new LinkedList<>();
    private int MAX_SIZE = 4;


    public synchronized void produce() {
        while (allProducts.size() >= MAX_SIZE) {
            System.out.println("仓库已满,暂时无法生产");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        allProducts.add("new Product");
        notifyAll();
        System.out.println("当前仓库产品数量为" + allProducts.size());

    }

    public synchronized void consume() {
        while (allProducts.size() <= 0){
            System.out.println("现在无任何产品,无法消费");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        allProducts.remove();
        System.out.println("消费过后当前仓库产品数量为" + allProducts.size());

    }
}
