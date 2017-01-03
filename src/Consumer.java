/**
 * Created by SilenceDut on 2016/12/8.
 */
public class Consumer implements Runnable {
    private Factory factory;

    Consumer(Factory factory) {
        this.factory = factory;
    }

    @Override
    public void run() {
        while (true) {
            factory.consume();
        }

    }
}
