package com.lpan.study.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.view.flowlayout.FlowLayout;
import com.lpan.study.view.flowlayout.TagAdapter;
import com.lpan.study.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lpan on 2018/9/6.
 */
public class FlowLayoutFragment extends BaseFragment {


    @BindView(R.id.flow_layout)
    TagFlowLayout  mTagFlowLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_flow_layout;
    }

    @Override
    protected void initData() {
        super.initData();
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五王五");

        mTagFlowLayout.setAdapter(new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tag_item_with_drawable,mTagFlowLayout,false);
                textView.setText(s);
                return textView;
            }
        });
    }
}
