package com.lpan.study.listener;

import android.view.View;

/**
 * Created by lpan on 2016/8/10.
 */
public interface OnRowAdapterClickListener<T> {
    public void onLongClick(View view, T t, int position);

    public void onClick(View view, T t, int position);
}
