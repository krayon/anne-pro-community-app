package www.robinwatch.squid.task;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseTaskPool {
    private static ExecutorService taskPool;
    private Context context;

    private class TaskThread implements Runnable {
        private TaskThread() {
        }

        public void run() {
        }
    }

    public BaseTaskPool() {
        taskPool = Executors.newCachedThreadPool();
    }

    public void addTask() {
    }
}
