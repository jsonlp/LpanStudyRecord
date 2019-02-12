package com.lpan.study.fragment.customviewtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.lpan.R;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.view.RoundedMaskImageView;

import butterknife.BindView;

/**
 * Created by lpan on 2018/10/24.
 */
public class RoundedMaskFragment extends BaseFragment {

    public static final String GIF_URL = "http://p-test.jiemosrc.com/NwMOWlSiatTo7csz1RiiBg.gif";
    public static final String IMAGE_PATH = "http://p-test.jiemosrc.com/9vT0OUTxZZPo7csz1RiiBg.jpeg";


    @BindView(R.id.image1)
    ImageView mImageView1;

    @BindView(R.id.image2)
    RoundedMaskImageView mImageView2;

    private Bitmap mBitmap;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_rounded_mask;
    }

    private Bitmap getBitmap(){
        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.inspire);
        }
        return mBitmap;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mImageView1.setImageBitmap(getBitmap());
        mImageView2.setBitmap(getBitmap());
    }
}
