package com.lpan.study.fragment.customviewtest;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.lpan.study.fragment.base.BaseActionbarFragment;
import com.lpan.study.model.ActionbarConfig;
import com.lpan.study.utils.BitmapUtils;
import com.lpan.R;

import butterknife.BindView;

/**
 * Created by lpan on 2017/10/18.
 */

public class DrawingViewFragment extends BaseActionbarFragment {

    @BindView(R.id.image1)
    ImageView mImage1;

    @BindView(R.id.image2)
    ImageView mImage2;

    @BindView(R.id.image3)
    ImageView mImage3;

    private int[] images = {R.drawable.drawing_view_1, R.drawable.drawing_view_2, R.drawable.drawing_view_3};

    private ImageView[] imageViews = null;

    @Override
    protected ActionbarConfig getActionbarConfig() {
        return getDefaultActionbar("The drawing process of the view");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_drawing_view;
    }

    @Override
    protected void initData() {
        super.initData();
        imageViews = new ImageView[]{mImage1, mImage2, mImage3};

        for (int i = 0; i < images.length; i++) {
            Bitmap bitmap1 = BitmapUtils.readBitmapById(getActivity(), images[i]);
            Bitmap bitmap = BitmapUtils.scaleBitmapFullScreenWidth(bitmap1);
            if (bitmap != null) {
                imageViews[i].setImageBitmap(bitmap);
            }
        }

    }
}
