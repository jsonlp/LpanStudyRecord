package com.lpan.study.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

public class MyPopupWindow extends PopupWindow {
    public MyPopupWindow(Context context) {
        super(context);
        init();
    }


    public MyPopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        init();
    }

    public MyPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
        init();
    }

    public MyPopupWindow(int width, int height) {
        super(width, height);
        init();
    }

    public MyPopupWindow(View contentView) {
        super(contentView);
        init();
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public MyPopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public MyPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    private void init() {
//        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new BitmapDrawable());
        setTouchable(true);
        setOutsideTouchable(true);
        setFocusable(true);
    }

    @Override
    public void showAsDropDown(View anchor) {
        if(Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }
}
