package com.lpan.study.utils;

import android.util.Log;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolUtil {

    private static final String TAG = "ThreadToolUtil";

    private static final int CORE_POOL_SIZE = 5;

    private static final int MAXIMUM_POOL_SIZE = 128;

    private static final int KEEP_ALIVE = 1;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {

        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "PaThreadPool #" + mCount.getAndIncrement());
        }

    };

    private static final BlockingQueue<PriorityRunnable> sPoolWorkQueue = new PriorityBlockingQueue<PriorityRunnable>(
            5, new TaskComparator()) {

        private static final long serialVersionUID = 1L;

        @Override
        public PriorityRunnable take() throws InterruptedException {
            PriorityRunnable task = super.take();
            Log.d(TAG,
                    "take  PriorityRunnable " + task.getInfo() + "  wait time ="
                            + (System.currentTimeMillis() - task.getStartTime()));
            return task;
        }

        @Override
        public PriorityRunnable poll(long timeout, TimeUnit unit) throws InterruptedException {
            Log.d(TAG, "poll  timeout=" + timeout + "   unit=" + unit);
            return super.poll(timeout, unit);
        }

    };

    private static final PaThreadPoolExecutor mPoolExecutor = new PaThreadPoolExecutor(
            CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, sPoolWorkQueue,
            sThreadFactory, new PaRejectedExecutionHandler());

    public static PaThreadPoolExecutor getThreadPoolExecutor() {
        return mPoolExecutor;
    }

    /**
     * 执行自定义优先级的线程
     *
     * @param runnable
     */
    public static void execute(PriorityRunnable runnable) {
        mPoolExecutor.execute(runnable);
    }

    /**
     * 执行默认优先级的线程
     *
     * @param command
     */
    public static void execute(Runnable command) {
        mPoolExecutor.execute(command);
    }

    private static class TaskComparator implements Comparator<PriorityRunnable> {

        public int compare(PriorityRunnable t1, PriorityRunnable t2) {
            return t1.getPriority() > t2.getPriority() ? -1
                    : (t1.getPriority() == t2.getPriority() ? 0 : 1);
        }
    }

    public static class PaRejectedExecutionHandler implements RejectedExecutionHandler {

        /**
         * Creates a {@code DiscardOldestPolicy} for the given executor.
         */
        public PaRejectedExecutionHandler() {
        }

        /**
         * Obtains and ignores the next task that the executor would
         * otherwise execute, if one is immediately available, and then
         * retries execution of task r, unless the executor is shut down,
         * in which case task r is instead discarded.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            //            if (!e.isShutdown()) {
            //                e.getQueue().poll();
            //                e.execute(r);
            //            }
            Log.e(TAG, "rejectedExecution   " + r);
        }
    }

    public static class PaThreadPoolExecutor extends ThreadPoolExecutor {

        public PaThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                    TimeUnit unit, BlockingQueue<PriorityRunnable> workQueue,
                                    ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                    (PriorityBlockingQueue) workQueue, threadFactory, handler);
        }

        public void execute(PriorityRunnable command) {
            super.execute(command);
        }

        @Override
        public void execute(Runnable command) {
            execute(new NormalRunnable(command));
        }

    }

}
