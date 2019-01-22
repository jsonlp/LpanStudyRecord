package com.lpan.study.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.lpan.R;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.view.DanceTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lpan on 2018/10/31.
 */
public class ViewFlipperFragment extends ButterKnifeFragment {

    @BindView(R.id.view_flipper1)
    ViewFlipper mViewFlipper1;

    @BindView(R.id.view_flipper2)
    ViewFlipper mViewFlipper2;

    @BindView(R.id.dance_text_view)
    DanceTextView mDanceTextView;

    List<String> data = new ArrayList<>();   //文字数据集合

    @Override
    protected int getLayoutResource() {
        return R.layout.frament_view_flipper;
    }


    @Override
    protected void initViews(View view) {
        super.initViews(view);
        data = new ArrayList<>();
        data.add("0 心疼S7 edge 三星官方‘虐机’视频上线");
        data.add("1 美剧《行尸走肉》上线Steam 每一集售价2.99...");
        data.add("2 2017四月新番动画全预览！你期待那部");
//        data.add("3 生娃后，老公有过这些举动，你却没加错人！");
//        data.add("4 汽车开空调耗油？只因为按错了一个键");
//        data.add("5 dsss？rklkls咳咳咳");


//        for (int i = 0; i < data.size(); i = i + 2) {
//            View child = LayoutInflater.from(getActivity()).inflate(R.layout.view_flipper_child, null);
//            TextView textView1 = child.findViewById(R.id.text1);
//            TextView textView2 = child.findViewById(R.id.text2);
//            textView1.setText(data.get(i));
//            if (data.size() > i + 1) {
//                textView2.setText(data.get(i + 1));
//            }
//
//            mViewFlipper.addView(child);
//        }
        if(data.size()>2){
            mViewFlipper1.setAutoStart(true);
            mViewFlipper2.setAutoStart(true);
            mViewFlipper2.setVisibility(View.VISIBLE);
        }else{
            mViewFlipper1.setAutoStart(false);
            mViewFlipper2.setAutoStart(false);
            if(data.size()==1){
                mViewFlipper2.setVisibility(View.GONE);
            }
        }

        List<String> list0 = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            list0.add(data.get(i));
            if (i!= 0) {
                list1.add(data.get(i));
            }
        }
        list1.add(data.get(0));

        for (int i = 0; i < list0.size(); i++) {
            View child = LayoutInflater.from(getActivity()).inflate(R.layout.view_flipper_child, null);
            TextView textView1 = child.findViewById(R.id.text1);
            textView1.setText(list0.get(i));
            mViewFlipper1.addView(child);
        }

        for (int i = 0; i < list1.size(); i++) {
            View child = LayoutInflater.from(getActivity()).inflate(R.layout.view_flipper_child, null);
            TextView textView1 = child.findViewById(R.id.text1);
            textView1.setText(list1.get(i));
            mViewFlipper2.addView(child);
        }

        mDanceTextView.dance(0,834262462);
    }

    @Override
    protected void initData() {
        super.initData();

    }

}

