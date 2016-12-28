package com.lpan.study.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;

import com.lpan.study.view.CircularImageView;
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

    CircularImageView mCircularImageView1;
    CircularImageView mCircularImageView2;
    CircularImageView mCircularImageView3;
    CircularImageView mCircularImageView4;
    CircularImageView mCircularImageView5;

    Button mButton1, mButton2, mButton3, mButton4, mButton5;


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


        mCircularImageView1 = (CircularImageView) view.findViewById(R.id.circularImageView1);
        mCircularImageView2 = (CircularImageView) view.findViewById(R.id.circularImageView2);
        mCircularImageView3 = (CircularImageView) view.findViewById(R.id.circularImageView3);
        mCircularImageView4 = (CircularImageView) view.findViewById(R.id.circularImageView4);
        mCircularImageView5 = (CircularImageView) view.findViewById(R.id.circularImageView5);

        mCircularImageView1.setImageBitmaps(mBmps1);
        mCircularImageView2.setImageBitmaps(mBmps2);
        mCircularImageView3.setImageBitmaps(mBmps3);
        mCircularImageView4.setImageBitmaps(mBmps4);
        mCircularImageView5.setImageBitmaps(mBmps5);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                mCircularImageView1.setImageBitmaps(mBmps1);

                break;
            case R.id.button2:
                mCircularImageView2.setImageBitmaps(mBmps2);

                break;
            case R.id.button3:
                mCircularImageView3.setImageBitmaps(mBmps3);

                break;
            case R.id.button4:
                mCircularImageView4.setImageBitmaps(mBmps4);

                break;
            case R.id.button5:
                mCircularImageView5.setImageBitmaps(mBmps5);

                break;
        }
    }
}
