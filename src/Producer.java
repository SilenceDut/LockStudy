/**
 * Created by SilenceDut on 2016/12/8.
 */
public class Producer implements Runnable {
    private Factory factory;

    Producer(Factory factory) {
        this.factory = factory;
    }

    @Override
    public void run() {
        while (true) {
            factory.produce();
        }

    }
}