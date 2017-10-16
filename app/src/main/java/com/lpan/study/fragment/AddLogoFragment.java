package com.lpan.study.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.utils.BitmapUtils;
import com.lpan.study.utils.Toaster;
import com.lpan.study.utils.ViewUtils;
import com.test.lpanstudyrecord.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lpan on 2017/6/14.
 */

public class AddLogoFragment extends BaseFragment implements View.OnClickListener {

    private TextView mButton;

    private ImageView mImageView;

    private Bitmap mBitmap1, mBitmap2;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_add_logo;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mButton = (TextView) view.findViewById(R.id.text1);
        mImageView = (ImageView) view.findViewById(R.id.image1);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bitmap bitmap = drawBitmap(mBitmap1, mBitmap2);
        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        mBitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.wall01);
        mBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.wall02);

    }


    private Bitmap drawBitmap(Bitmap bitmap1, Bitmap bitmap2) {
        bitmap1 = BitmapUtils.centerSquareScaleBitmap(bitmap1,ViewUtils.dp2px(AppContext.getContext(),40));
        bitmap2 = BitmapUtils.resizeImage(bitmap2,ViewUtils.dp2px(AppContext.getContext(),14),ViewUtils.dp2px(AppContext.getContext(),10));

        Bitmap bitmap3 = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), bitmap1.getConfig());
        Canvas canvas = new Canvas(bitmap3);
        canvas.drawBitmap(bitmap1, new Matrix(),null);
        canvas.drawBitmap(bitmap2, ViewUtils.dp2px(AppContext.getContext(),26),ViewUtils.dp2px(AppContext.getContext(),30), null);  //120、350为bitmap2写入点的x、y坐标
        //将合并后的bitmap3保存为png图片到本地
        FileOutputStream out = null;
        String path = "";
        try {
            path = getVideoPath() + File.separator + "image3.png";
            out = new FileOutputStream(path);
            bitmap3.compress(Bitmap.CompressFormat.PNG, 90, out);
            Toaster.toastShort( path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap3;

    }

    public String getVideoPath() {
        return getVideoFileDir().getAbsolutePath();
    }

    public File getVideoFileDir() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/" + "Jiemoapp" + "/");
        if (dir != null && dir.exists()) {
            return dir;
        }
        return null;
    }
}
