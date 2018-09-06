package com.lpan.study.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by lpan on 2018/9/2.
 */
public class CardViewAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public CardViewAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
