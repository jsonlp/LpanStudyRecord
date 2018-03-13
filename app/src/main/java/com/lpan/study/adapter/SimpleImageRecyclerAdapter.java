package com.lpan.study.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lpan.R;
import com.lpan.study.utils.ViewUtils;

import java.util.List;

/**
 * Created by lpan on 2018/3/13.
 */

public class SimpleImageRecyclerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SimpleImageRecyclerAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView = helper.getView(R.id.image1);
        imageView.getLayoutParams().width = ViewUtils.getScreenWidth(mContext) / 3;
        imageView.getLayoutParams().height = ViewUtils.getScreenWidth(mContext) / 3;

        Glide.with(mContext)
                .load(item)
                .into(imageView);
    }
}
