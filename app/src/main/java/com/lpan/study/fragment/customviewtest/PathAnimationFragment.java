package com.lpan.study.fragment.customviewtest;

import android.view.View;
import android.widget.ImageView;

import com.lpan.R;
import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.ViewUtils;
import com.lpan.study.view.BrokenLineView;
import com.lpan.study.view.pathanimation.PaperPlaneView;

/**
 * Created by liaopan on 2018/1/22 14:18.
 */

public class PathAnimationFragment extends BaseFragment implements View.OnClickListener {

    private ImageView fab;

    private PaperPlaneView mPaperPlaneView1, mPaperPlaneView2;

    private static final int SCREEN_WIDTH = ViewUtils.getScreenWidth(AppContext.getContext());
    private static final int SCREEN_HEIGHT = ViewUtils.getScreenHeight(AppContext.getContext());

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_path_animation;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        this.fab = (ImageView) view.findViewById(R.id.fab);
        mPaperPlaneView1 = (PaperPlaneView) view.findViewById(R.id.paper_plane1);
        mPaperPlaneView2 = (PaperPlaneView) view.findViewById(R.id.paper_plane2);

        BrokenLineView brokenLineView = (BrokenLineView) view.findViewById(R.id.broken_line_view);
        brokenLineView.setRight(true);
        fab.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
//                PointF start = new PointF(200, SCREEN_HEIGHT / 2);
//                PointF control = new PointF(SCREEN_WIDTH / 2, 300);
//                PointF control2 = new PointF(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 + 300);
//
//                PointF end = new PointF(SCREEN_WIDTH - 200, SCREEN_HEIGHT / 2);
//                mPaperPlaneView1.fly(-45, start, control, end);
//                mPaperPlaneView2.fly(-45, start, control2, end);

                int[] location = new int[2];
                mPaperPlaneView1.getLocationOnScreen(location);
                if (Log.DEBUG) {
                    Log.d("PathAnimationFragment", "onClick--------x=" + location[0] + "   y=" + location[1]);
                }
                break;
        }
    }
}
