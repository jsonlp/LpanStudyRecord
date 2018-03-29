package com.lpan.study.fragment.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lpan on 2018/3/26.
 */

public class BaseDialogFragment extends DialogFragment {

    public static BaseDialogFragment newInstance(){
        BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
        return baseDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        //为了样式统一和兼容性，可以使用 V7 包下的 AlertDialog.Builder
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        // 设置主题的构造方法
//        // AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
//        builder.setTitle("注意：")
//                .setMessage("是否退出应用？")
//                .setPositiveButton("确定", null)
//                .setNegativeButton("取消", null)
//                .setCancelable(false);
//        //builder.show(); // 不能在这里使用 show() 方法
//        return builder.create();
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
