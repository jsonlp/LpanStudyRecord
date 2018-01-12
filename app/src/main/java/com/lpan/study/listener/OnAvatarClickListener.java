package com.lpan.study.listener;

import android.view.View;

/**
 * Created by liaopan on 2018/1/12 13:53.
 */

public interface OnAvatarClickListener<T> {

    void OnAvatarClick(T t, View view, int position);
}
