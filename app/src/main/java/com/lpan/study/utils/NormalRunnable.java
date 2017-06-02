package com.lpan.study.utils;

public class NormalRunnable extends PriorityRunnable {

    private Runnable mRunnable;

    public NormalRunnable(Runnable mRunnable) {
        super(0);
        this.mRunnable = mRunnable;
    }

    @Override
    public void run() {
        if (mRunnable != null) {
            mRunnable.run();
        }
    }

    @Override
    public String getInfo() {

        return "NormalRunnable " + super.getInfo();
    }
}
