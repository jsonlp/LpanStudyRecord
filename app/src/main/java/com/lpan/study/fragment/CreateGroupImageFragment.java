package com.lpan.study.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.utils.ViewUtils;
import com.lpan.study.view.JoinBitmaps;
import com.lpan.R;

import java.util.ArrayList;

/**
 * Created by lpan on 2016/12/30.
 */

public class CreateGroupImageFragment extends BaseFragment {

    private ImageView mImageView;

    private int count;

    ArrayList<Bitmap> mBmps1 = new ArrayList<Bitmap>();
    ArrayList<Bitmap> mBmps2 = new ArrayList<Bitmap>();
    ArrayList<Bitmap> mBmps3 = new ArrayList<Bitmap>();
    ArrayList<Bitmap> mBmps4 = new ArrayList<Bitmap>();
    ArrayList<Bitmap> mBmps5 = new ArrayList<Bitmap>();

    //控制缺口圆弧的位置
    private static final float[][] rotations = { new float[] { 360 }, new float[] { 360, 225 },
            new float[] { 120, -120, 0 }, new float[] { 90, 180, -90, 0 },
            new float[] { 144, -144, -72, 0, 72 }, };

    public static float[] rotation(int count) {
        return count > 0 && count <= rotations.length ? rotations[count - 1] : null;
    }
    //控制相对父控件大小比例
    private static final float[][] sizes = { new float[] { 0.52f, 0.52f },
            new float[] { 0.48f, 0.65f }, new float[] { 0.44f, 0.8f },
            new float[] { 0.4f, 0.91f }, new float[] { 0.36f, 0.80f } };

    public static float[] size(int count) {
        return count > 0 && count <= sizes.length ? sizes[count - 1] : null;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            count = getArguments().getInt("image_count");
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_create_group_image;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mImageView = (ImageView) view.findViewById(R.id.image1);

    }

    @Override
    protected void initData() {
        super.initData();

        Bitmap avatar1 = BitmapFactory.decodeResource(getResources(), R.drawable.wall01);
        Bitmap avatar2 = BitmapFactory.decodeResource(getResources(), R.drawable.wall02);
        Bitmap avatar3 = BitmapFactory.decodeResource(getResources(), R.drawable.wall03);
        Bitmap avatar4 = BitmapFactory.decodeResource(getResources(), R.drawable.wall04);
        Bitmap avatar5 = BitmapFactory.decodeResource(getResources(), R.drawable.wall05);

        mBmps1.add(avatar1);

        mBmps2.add(avatar1);
        mBmps2.add(avatar2);

        mBmps3.add(avatar1);
        mBmps3.add(avatar2);
        mBmps3.add(avatar3);

        mBmps4.add(avatar1);
        mBmps4.add(avatar2);
        mBmps4.add(avatar3);
        mBmps4.add(avatar4);

        mBmps5.add(avatar1);
        mBmps5.add(avatar2);
        mBmps5.add(avatar3);
        mBmps5.add(avatar4);
        mBmps5.add(avatar5);

        mImageView.setImageBitmap(JoinBitmaps.createBitmap(ViewUtils.dp2px(AppContext.getContext(),50),
                ViewUtils.dp2px(AppContext.getContext(),50), mBmps3));

//        int count = Math.min(mBmps3.size(), JoinLayout.max());
//        float[] size2 = size2(count);
//
//        mImageView.setImageBitmap(JoinBitmaps.createBitmap(mImageView.getWidth(),
//                mImageView.getHeight(), mBmps3, count, size2));

//        float[] size3 = size3(count);
//        mImageView3.setImageBitmap(JoinBitmaps.createBitmap(mImageView3.getWidth(),
//                mImageView3.getHeight(), mBmps, count, size3));
//
//        mImageView4.setImageBitmap(JoinBitmaps.createBitmap(mImageView4.getWidth(),
//                mImageView4.getHeight(), mBmps, count, size2, 0.3f));

    }

}
