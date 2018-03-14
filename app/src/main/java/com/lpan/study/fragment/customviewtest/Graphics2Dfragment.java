package com.lpan.study.fragment.customviewtest;

import android.widget.LinearLayout;

import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.model.ActionbarConfig;
import com.lpan.study.view.BallMoveView;
import com.lpan.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by lpan on 2017/10/20.
 */

public class Graphics2Dfragment extends BaseActionbarFragment {

    @BindView(R.id.layout)
    LinearLayout mContainer;


    @BindView(R.id.ball_move_view)
    BallMoveView mBallView;

    @Override
    protected ActionbarConfig getActionbarConfig() {
        return getDefaultActionbar("Graphics 2D");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_graphics_2d;
    }

    @Override
    protected void initData() {
        super.initData();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mBallView.postInvalidate();
            }
        }, 200, 50);
    }
}
