package com.lpan.study.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lpan.R;

import java.util.List;

/**
 * Created by lpan on 2018/3/8.
 */

public class SimpleRecyclerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SimpleRecyclerAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text1, item);
    }
}
