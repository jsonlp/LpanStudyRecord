package com.lpan.study.listener;

import android.view.View;

import com.lpan.study.model.ProvinceTabInfo;

/**
 * Created by lpan on 2017/4/1.
 */

public interface OnProvinceTabClickListener {

    void onProvinceClick(int position, ProvinceTabInfo provinceTabInfo, View divider);

}
