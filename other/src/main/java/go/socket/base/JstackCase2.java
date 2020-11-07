package go.socket.base;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by gao on 2018/4/13.
 */
public class JstackCase2 {

    public static Executor executor = Executors.newFixedThreadPool(5);
    public static Object lock = new Object();

    public static void main(String[] args) {

        Task task1 = new Task();
        Task task2 = new Task();
        executor.execute(task1);
        executor.execute(task2);
    }
    static class Task implements Runnable{

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    lock.wait();
                    //TimeUnit.SECONDS.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}