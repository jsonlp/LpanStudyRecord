package com.lpan.study.fragment;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.utils.BitmapUtils;
import com.lpan.study.utils.FileUtils;
import com.lpan.study.utils.Toaster;
import com.lpan.study.utils.Utils;
import com.test.lpanstudyrecord.R;

import java.io.File;

/**
 * Created by lpan on 2017/3/1.
 */

public class ZoomImageFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mImageView1, mImageView2;

    private EditText mEditText;

    private TextView mButton;

    private Bitmap mBitmap;

    private static final int WIDTH = 640;

    private static final int HEIGHT = 640;

    private String imagePath;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_zoom_image;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mImageView1 = (ImageView) view.findViewById(R.id.image1);
        mImageView2 = (ImageView) view.findViewById(R.id.image2);
        mEditText = (EditText) view.findViewById(R.id.edit1);
        mButton = (TextView) view.findViewById(R.id.ok);


        mButton.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        imagePath = FileUtils.getImageFileDir() + File.separator + "image.jpg";
        mBitmap = BitmapUtils.compressPhotoFileToBitmap(imagePath, WIDTH, HEIGHT);
        mImageView1.setImageBitmap(mBitmap);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                int quality = Integer.valueOf(mEditText.getText().toString());
                compress(imagePath, quality);
                break;
        }
    }

    private void compress(String path, final int quality) {

        new AsyncTask<String, Void, File>() {
            @Override
            protected File doInBackground(String... params) {

                File file = BitmapUtils.saveBitmap(BitmapUtils.compressPhotoFileToBitmap(imagePath, WIDTH, HEIGHT), new File(FileUtils.getImageFileDir(), "image_copy_" + quality + ".jpg"), true, 1, quality);

                return file;
            }

            @Override
            protected void onPostExecute(File file) {
                super.onPostExecute(file);

                if (file.exists()) {
                    Toaster.toastShort(AppContext.getContext(), "save successful:  SIZE=" + Utils.formatSizeToKB(file.length()) + "     " + file.getAbsolutePath());

                    mImageView2.setImageBitmap(BitmapUtils.compressPhotoFileToBitmap(file.getAbsolutePath(), WIDTH, HEIGHT));
                }

            }
        }.execute(path);
    }


}
