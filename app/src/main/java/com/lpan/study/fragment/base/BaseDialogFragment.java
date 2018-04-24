package com.lpan.study.fragment.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.lpan.R;

/**
 * Created by lpan on 2018/3/26.
 */

public class BaseDialogFragment extends DialogFragment {

    private OnSelectPhotoListener mListener;

    private Dialog mDialog;

    public static BaseDialogFragment newInstance(OnSelectPhotoListener listener) {
        BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
        baseDialogFragment.setListener(listener);
        return baseDialogFragment;
    }


    public void setListener(OnSelectPhotoListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return getBottomDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private Dialog getBottomDialog() {
        if (mDialog == null) {
            mDialog = new Dialog(getActivity(), R.style.BottomDialog);
            LinearLayout root = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
                    R.layout.dialog_bottom, null);
            //初始化视图
            root.findViewById(R.id.btn_choose_img).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.takePhoto();
                    }
                    mDialog.dismiss();
                }
            });
            root.findViewById(R.id.btn_open_camera).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.gotoGallery();
                    }
                    mDialog.dismiss();
                }
            });
            root.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });
            mDialog.setContentView(root);
            Window dialogWindow = mDialog.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
            lp.x = 0; // 新位置X坐标
            lp.y = 0; // 新位置Y坐标
            lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
            root.measure(0, 0);
            lp.height = root.getMeasuredHeight();
            lp.alpha = 9f; // 透明度
            dialogWindow.setAttributes(lp);
//            mDialog.show();
        }

        return mDialog;
    }

    public interface OnSelectPhotoListener {
        void takePhoto();

        void gotoGallery();
    }

}
