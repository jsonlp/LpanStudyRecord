package com.lpan.study.fragment.customviewtest;

import android.graphics.Outline;
import android.graphics.Path;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.fragment.base.BaseActionbarFragment;
import com.lpan.study.utils.Log;
import com.lpan.study.view.actionbar.ActionbarConfig;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lpan on 2018/5/21.
 */

public class OutlineFragment extends BaseActionbarFragment {


    @BindView(R.id.button1) Button mButton1;
    @BindView(R.id.image1) TextView mImage1;

    private ViewOutlineProvider mViewOutlineProvider;
    private  Path mPath;
    @Override
    protected ActionbarConfig getActionbarConfig() {
        return getDefaultActionbar("outline");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_outline;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mImage1.setElevation(5);
        mViewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //1 圆角矩形
//                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20);
                //2 圆
//                outline.setOval(0, 0, view.getWidth(), view.getHeight());
                //3 矩形
//                outline.setRect(0, 0, view.getWidth() / 2, view.getHeight() / 2);
                //4 path
                Path path = new Path();
                path.moveTo(view.getWidth(), view.getHeight());
                path.lineTo(view.getWidth(), view.getHeight() * 2);
                path.lineTo(view.getWidth() * 2, view.getHeight() * 2);
                path.lineTo(view.getWidth() * 2, view.getHeight());
                path.close();
                outline.setConvexPath(path);
            }
        };
        mImage1.setOutlineProvider(mViewOutlineProvider);
    }

    @OnClick(R.id.button1)
    public void onViewClicked() {
        if (mImage1.getClipToOutline()) {
            mImage1.setClipToOutline(false);
        } else {
            mImage1.setClipToOutline(true);
        }
    }
}
