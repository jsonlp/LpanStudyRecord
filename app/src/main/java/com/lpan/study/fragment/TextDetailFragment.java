package com.lpan.study.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.R;

/**
 * Created by lpan on 2017/4/11.
 */

public class TextDetailFragment extends BaseFragment {

    public static final String EXTRA_CONTENT = "content";

    private TextView mTextView;

    private String mContent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContent = getArguments().getString(EXTRA_CONTENT);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_text_detail;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mTextView = (TextView) view.findViewById(R.id.text);
        mTextView.setText(mContent);
    }
}
