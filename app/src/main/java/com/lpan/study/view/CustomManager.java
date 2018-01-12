package com.lpan.study.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by liaopan on 2018/1/11 17:21.
 */

public class CustomManager extends RecyclerView.LayoutManager {


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
