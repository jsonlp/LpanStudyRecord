package com.lpan.study.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lpan.R;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

/**
 * Created by lpan on 2018/3/8.
 */

public class SimpleRecyclerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SimpleRecyclerAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public SimpleRecyclerAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text1, item);
        int position = helper.getAdapterPosition();

        helper.getView(R.id.close_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SimpleRecyclerAdapter","onClick--------remove "+item);
                ((SwipeMenuLayout)helper.itemView).quickClose();
                if (position >= 0 && position < getItemCount()) {
                }
            }
        });
    }
}
