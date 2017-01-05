package com.lpan.study.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lpan.study.utils.FragmentUtils;
import com.lpan.study.utils.ViewUtils;
import com.lpan.study.view.CircleImnageView;
import com.lpan.study.view.GroupAvatarView;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;

/**
 * Created by lpan on 2016/12/27.
 */
public class GroupImageFragment extends BaseFragment implements View.OnClickListener {


    ArrayList<Bitmap> mBmps1 = new ArrayList<Bitmap>();
    ArrayList<Bitmap> mBmps2 = new ArrayList<Bitmap>();
    ArrayList<Bitmap> mBmps3 = new ArrayList<Bitmap>();
    ArrayList<Bitmap> mBmps4 = new ArrayList<Bitmap>();
    ArrayList<Bitmap> mBmps5 = new ArrayList<Bitmap>();

    GroupAvatarView mGroupAvatarView1;
    GroupAvatarView mGroupAvatarView2;
    GroupAvatarView mGroupAvatarView3;
    GroupAvatarView mGroupAvatarView4;
    GroupAvatarView mGroupAvatarView5;

    Button mButton1, mButton2, mButton3, mButton4, mButton5;

    private CircleImnageView mCircleImnageView;

    private GroupAvatarView mGroupAvatarView;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_group_image;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mButton1 = (Button) view.findViewById(R.id.button1);
        mButton2 = (Button) view.findViewById(R.id.button2);
        mButton3 = (Button) view.findViewById(R.id.button3);
        mButton4 = (Button) view.findViewById(R.id.button4);
        mButton5 = (Button) view.findViewById(R.id.button5);

        mCircleImnageView = (CircleImnageView) view.findViewById(R.id.circle_image);
        mGroupAvatarView = (GroupAvatarView) view.findViewById(R.id.group_image);

        mCircleImnageView.setStrokeStyle(2 * ViewUtils.ONE_DP, Color.RED);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mn);
        mCircleImnageView.setImageBitmap(bitmap);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);




        Bitmap avatar1 = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        Bitmap avatar2 = BitmapFactory.decodeResource(getResources(), R.mipmap.b);
        Bitmap avatar3 = BitmapFactory.decodeResource(getResources(), R.mipmap.c);
        Bitmap avatar4 = BitmapFactory.decodeResource(getResources(), R.mipmap.d);
        Bitmap avatar5 = BitmapFactory.decodeResource(getResources(), R.mipmap.e);

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


        mGroupAvatarView1 = (GroupAvatarView) view.findViewById(R.id.circularImageView1);
        mGroupAvatarView2 = (GroupAvatarView) view.findViewById(R.id.circularImageView2);
        mGroupAvatarView3 = (GroupAvatarView) view.findViewById(R.id.circularImageView3);
        mGroupAvatarView4 = (GroupAvatarView) view.findViewById(R.id.circularImageView4);
        mGroupAvatarView5 = (GroupAvatarView) view.findViewById(R.id.circularImageView5);


        mGroupAvatarView1.setOnClickListener(this);
        mGroupAvatarView2.setOnClickListener(this);
        mGroupAvatarView3.setOnClickListener(this);
        mGroupAvatarView4.setOnClickListener(this);
        mGroupAvatarView5.setOnClickListener(this);

        mGroupAvatarView1.setImageBitmaps(mBmps1);
        mGroupAvatarView2.setImageBitmaps(mBmps2);
        mGroupAvatarView3.setImageBitmaps(mBmps3);
        mGroupAvatarView4.setImageBitmaps(mBmps4);
        mGroupAvatarView5.setImageBitmaps(mBmps5);


        mGroupAvatarView.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                mGroupAvatarView1.setImageBitmaps(mBmps1);

                break;
            case R.id.button2:
                mGroupAvatarView2.setImageBitmaps(mBmps2);

                break;
            case R.id.button3:
                mGroupAvatarView3.setImageBitmaps(mBmps3);

                break;
            case R.id.button4:
                mGroupAvatarView4.setImageBitmaps(mBmps4);

                break;
            case R.id.button5:
                mGroupAvatarView5.setImageBitmaps(mBmps5);

                break;


            case R.id.circularImageView1:
                gotoCreateImage(1);
                break;
            case R.id.circularImageView2:
                gotoCreateImage(2);
                break;
            case R.id.circularImageView3:
                gotoCreateImage(3);
                break;
            case R.id.circularImageView4:
                gotoCreateImage(4);
                break;
            case R.id.circularImageView5:
                gotoCreateImage(5);
                break;
        }
    }

    private void gotoCreateImage(int type) {
        Bundle bundle1 = new Bundle();
        bundle1.putInt("image_count", type);
        FragmentUtils.navigateToInNewActivity(getActivity(), new CreateGroupImageFragment(), bundle1);
    }
}
