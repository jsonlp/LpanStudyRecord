package com.lpan.study.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.lpan.study.view.CircleImnageView;
import com.lpan.study.view.GroupImageView;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2016/12/27.
 */
public class GroupImageFragment extends BaseFragment {

    private CircleImnageView mCircleImnageView;

    private GroupImageView mGroupImageView;




    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_group_image;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
//        mCircleImnageView = (CircleImnageView) view.findViewById(R.id.circle_image);
        mGroupImageView= (GroupImageView) view.findViewById(R.id.group_image);
//        mCircleImnageView.setVisibility(View.GONE);



    }

    @Override
    protected void initData() {
        super.initData();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image_06);
//        mCircleImnageView.setImageBitmap(bitmap);
        List<Bitmap> bitmaps=new ArrayList<>();
        bitmaps.add(bitmap);
        mGroupImageView.setBitmaps(bitmaps);

    }
}
