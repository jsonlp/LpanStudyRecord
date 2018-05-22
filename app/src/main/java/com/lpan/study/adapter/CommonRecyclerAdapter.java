package com.lpan.study.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lpan.R;
import com.lpan.study.model.BaseRecyclerItem;

import java.util.List;

/**
 * Created by lpan on 2018/4/24.
 */

public class CommonRecyclerAdapter extends BaseQuickAdapter<BaseRecyclerItem, BaseViewHolder> {

    public CommonRecyclerAdapter(int layoutResId, @Nullable List<BaseRecyclerItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseRecyclerItem item) {
        helper.setText(R.id.title, item.getTitle());
    }
}
