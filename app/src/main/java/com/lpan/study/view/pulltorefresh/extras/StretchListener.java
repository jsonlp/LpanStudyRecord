package com.lpan.study.view.pulltorefresh.extras;

import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;

public interface StretchListener {

    void initStretch(Matrix matrix);

    boolean needStretch(MotionEvent event);

    void onStretch(float ratio, LayoutParams params, Matrix matrix);

    boolean canStretch();
}
