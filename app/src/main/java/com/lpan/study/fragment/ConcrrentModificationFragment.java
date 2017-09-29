package com.lpan.study.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lpan.study.fragment.base.BaseFragment;
import com.test.lpanstudyrecord.R;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by lpan on 2017/6/8.
 */

public class ConcrrentModificationFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = ConcrrentModificationFragment.class.getSimpleName();

    private TextView mTextView1, mTextView2;

    CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_concurrent_modification;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        mTextView1 = (TextView) view.findViewById(R.id.text1);
        mTextView2 = (TextView) view.findViewById(R.id.text2);

        mTextView1.setOnClickListener(this);
        mTextView2.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        super.initData();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.text1:
                singleThread();
                break;

            case R.id.text2:
                multiThread();
                break;
        }
    }

    private void singleThread() {


        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (TextUtils.equals("aaa", s)) {
                iterator.remove();
//                list.remove(s); //exception
            }
        }

        for (String s : list) {
            Log.d(TAG, "-----singleThread-----list=" + s);
        }

    }

    private void multiThread() {

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                super.run();
//                Iterator<String> iterator = list.iterator();
//                while (iterator.hasNext()) {
//                    String s = iterator.next();
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
                for (String s : list) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (String ss : list) {
                    Log.d(TAG, "-----multiThread1-----list=" + ss);
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                super.run();
//                Iterator<String> iterator = list.iterator();
//                while (iterator.hasNext()) {
//                    String s = iterator.next();
//                    if (TextUtils.equals("aaa", s)) {
//                        iterator.remove();
//                    }
//                }
                for (String s : list) {
                    if (TextUtils.equals("aaa", s)) {
                        list.remove(s);
                    }
                }

                for (String s : list) {
                    Log.d(TAG, "-----multiThread2-----list=" + s);
                }
            }
        };

        thread1.start();
        thread2.start();


    }
}
