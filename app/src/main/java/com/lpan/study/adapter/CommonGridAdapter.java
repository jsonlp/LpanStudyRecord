package com.lpan.study.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lpan.R;
import com.lpan.study.context.AppContext;
import com.lpan.study.imageloader.ImageLoader;
import com.lpan.study.model.BaseRecyclerItem;
import com.lpan.study.utils.ViewUtils;

import java.util.List;

/**
 * Created by lpan on 2018/4/27.
 */

public class CommonGridAdapter extends BaseQuickAdapter<BaseRecyclerItem, BaseViewHolder> {

    public CommonGridAdapter(int layoutResId, @Nullable List<BaseRecyclerItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseRecyclerItem item) {
        ImageView imageView = helper.getView(R.id.image);

        int width = (ViewUtils.getScreenWidth(AppContext.context) - ViewUtils.dp2px(AppContext.context, 16 + 16 + 8 + 8)) / 3;
        int height = (int) (width * 1.4f);
        imageView.getLayoutParams().width = width;
        imageView.getLayoutParams().height = height;


        ImageLoader.with(mContext)
                .loadUrl(item.getUrl())
                .blur(10)
                .into(imageView);


    }
}
