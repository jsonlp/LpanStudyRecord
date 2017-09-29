package com.lpan.study.utils;

/**
 * 线程优先级,如果需要设置优先级，请使用该类代替 Runnable
 */
public abstract class PriorityRunnable implements Runnable {

    private int mPriority;

    private long startTime;

    /**
     * 设置优先级， 优先级值为 1000+extraAddPriority,注意：该任务的优先级是会变化的
     *
     * @param extraAddPriority
     */
    public PriorityRunnable(int extraAddPriority) {
        this.mPriority = 1000 + extraAddPriority;
        this.startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

    /**
     * 获取当前线程的优先级，优先级是随时间的增大而增大
     *
     * @return
     */

    public int getPriority() {
        return mPriority + getTimePriority();
    }

    public int getTimePriority() {
        return (int) ((System.currentTimeMillis() - startTime) / 100);
    }

    public String getInfo() {
        return "  mPriority=" + mPriority;
    }
}
