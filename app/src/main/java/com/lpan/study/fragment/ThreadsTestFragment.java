package com.lpan.study.fragment;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.fragment.base.BaseActionbarFragment;
import com.lpan.study.utils.Log;
import com.lpan.study.view.actionbar.ActionbarConfig;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lpan on 2018/4/25.
 */

public class ThreadsTestFragment extends BaseActionbarFragment implements View.OnClickListener {

    @BindView(R.id.startThread)
    Button mStartThread;

    @BindView(R.id.text)
    TextView mTextView;

    private Handler mHandler = new Handler(); //主线程handler

    private Handler mOtherHandler = null;
    private HandlerThread mHandlerThread;
    private Handler mWorkHandler;

    private int mCount;

    @Override
    protected ActionbarConfig getActionbarConfig() {
        return getDefaultActionbar("android 多线程");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_threads_test;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mOtherHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (Log.DEBUG) {
                            Log.d("ThreadsTestFragment", "handleMessage--------" + Thread.currentThread().getName());
                        }
                        if (msg.what == 111) {

                        }
                    }
                };
                Looper.loop();
            }
        }).start();

        mHandlerThread = new HandlerThread("my handler thread");
        mHandlerThread.start();

        mWorkHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(final Message msg) {
                super.handleMessage(msg);
                Log.d("ThreadsTestFragment", "1handleMessage--------" + Thread.currentThread().getName());
                if (msg.what == 123) {
                    try {
                        Thread.sleep(3000);
                        final int a1 = msg.arg1;
                        Log.d("ThreadsTestFragment", "2handleMessage--------" + Thread.currentThread().getName());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextView.setText("task" + a1);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

    }

    @OnClick({R.id.startThread, R.id.test_handler, R.id.handler_thread})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startThread:
                //(1)
                new MyThread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d("ThreadsTestFragment", "1run--------" + Thread.currentThread().getName());
                    }
                }.start();


                //(2)
                new Thread(new MyRun() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d("ThreadsTestFragment", "2run--------" + Thread.currentThread().getName());
                    }
                }).start();

                //(3)
                MyCallback callback = new MyCallback() {
                    @Override
                    public String call() throws Exception {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d("ThreadsTestFragment", "3run--------" + Thread.currentThread().getName());

                        return super.call();
                    }
                };
                FutureTask<String> futureTask = new FutureTask<String>(callback);
                new Thread(futureTask, "xxx").start();

                mTextView.setText("1 MyThread extent Thread" + "\n" + "2 implements Runnable" + "\n" + "3 implements Callback");
                break;


            case R.id.test_handler:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d("ThreadsTestFragment", "1run--------" + Thread.currentThread().getName());


                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("ThreadsTestFragment", "2run--------" + Thread.currentThread().getName());
                                mTextView.setTextColor(getResources().getColor(R.color.red));
                            }
                        });
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("ThreadsTestFragment", "3run--------" + Thread.currentThread().getName());
                                mTextView.setTextColor(getResources().getColor(R.color.yellow));
                            }
                        });
                        mTextView.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("ThreadsTestFragment", "4run--------" + Thread.currentThread().getName());
                                mTextView.setTextColor(getResources().getColor(R.color.blue));

                            }
                        });

                        mOtherHandler.sendEmptyMessage(111);

                        Looper.prepare();
                        MyHandler myHandler = new MyHandler(getActivity());
                        try {
                            Thread.sleep(2000);
                            myHandler.sendEmptyMessage(11111);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();


                break;

            case R.id.handler_thread:
                mCount++;
                Message obtain = Message.obtain();
                obtain.what = 123;
                obtain.arg1 = mCount;
                mWorkHandler.sendMessage(obtain);
                break;
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();

        }
    }

    class MyRun implements Runnable {

        @Override
        public void run() {

        }
    }

    class MyCallback implements Callable<String> {


        @Override
        public String call() throws Exception {
            return null;
        }
    }


    private static class MyHandler extends Handler{
        private WeakReference<Activity> mActivityWeakReference;

        public MyHandler(Activity activity){
            mActivityWeakReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }
}
