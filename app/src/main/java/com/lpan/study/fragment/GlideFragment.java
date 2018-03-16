package com.lpan.study.fragment;


import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.study.constants.FilePathConstants;
import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.imageloader.ImageLoader;
import com.lpan.R;
import com.lpan.study.imageloader.SaveImageListener;
import com.lpan.study.utils.BitmapUtils;
import com.lpan.study.utils.ViewUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lpan on 2017/9/29.
 */

public class GlideFragment extends ButterKnifeFragment implements View.OnClickListener {

    public static final String URL_PATH = "http://pic6.nipic.com/20091207/3337900_161732052452_2.jpg";
    public static final String GIF_URL = "http://p-test.jiemosrc.com/NwMOWlSiatTo7csz1RiiBg.gif";
    public static final String SAVE_PATH = "http://p-test.jiemosrc.com/9vT0OUTxZZPo7csz1RiiBg.jpeg";


    @BindView(R.id.image1)
    ImageView mImageView;

    @BindView(R.id.text1)
    TextView mImageView1;

    @BindView(R.id.text2)
    TextView mImageView2;

    @BindView(R.id.text3)
    TextView mImageView3;

    @BindView(R.id.text4)
    TextView mImageView4;

    @BindView(R.id.text5)
    TextView mImageView5;

    @BindView(R.id.text6)
    TextView mImageView6;

    private File mFile;

    private Integer imagePaths[] = {
            R.drawable.wall01, R.drawable.wall02,
            R.drawable.wall03, R.drawable.wall04,
            R.drawable.wall05, R.drawable.wall06,
            R.drawable.wall07, R.drawable.wall08,
            R.drawable.wall09, R.drawable.wall10}; // 20个图片资源


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_glide;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        mFile = new File(FilePathConstants.PANDA_TEST_DIR, "image.jpg");
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3,
            R.id.text4, R.id.text5, R.id.text6,
            R.id.text7, R.id.text8, R.id.text9})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                // url
                ImageLoader.with(getActivity())
                        .placeHolder(R.drawable.image_loading)
                        .error(R.drawable.ic_error)
                        .loadUrl(GIF_URL)
                        .into(mImageView);
                break;

            case R.id.text2:
                // res
                ImageLoader.with(getActivity())
                        .loadRes(R.drawable.wall03)
                        .into(mImageView);

                break;

            case R.id.text3:
                // file
                if (mFile == null) {
                    toastShort(FilePathConstants.EXTERNAL_STORAGE_DIR + " image.jpg is not exit");
                    return;
                }
                ImageLoader.with(getActivity())
                        .loadFile(mFile)
                        .into(mImageView);
                break;

            case R.id.text4:
                // file path
                if (mFile == null) {
                    toastShort(FilePathConstants.EXTERNAL_STORAGE_DIR + " image.jpg is not exit");
                    return;
                }
                ImageLoader.with(getActivity())
                        .loadFilePath(mFile.getAbsolutePath())
                        .into(mImageView);
                break;

            case R.id.text5:
                // round corner
                ImageLoader.with(getActivity())
                        .loadUrl(URL_PATH)
                        .roundCorner(ViewUtils.dp2px(AppContext.getContext(), 10))
                        .into(mImageView);
                break;

            case R.id.text6:
                // blur
                ImageLoader.with(getActivity())
                        .loadUrl(URL_PATH)
                        .blur(ViewUtils.dp2px(AppContext.getContext(), 10))
                        .into(mImageView);
                break;

            case R.id.text7:
                // circle
                ImageLoader.with(getActivity())
                        .loadUrl(URL_PATH)
                        .placeHolder(R.drawable.ic_launcher)
                        .error(R.drawable.ic_error)
                        .circle()
                        .into(mImageView);
                break;
            case R.id.text8:
                // download
                ImageLoader.with(getActivity())
                        .loadUrl(SAVE_PATH)
                        .download(new SaveImageListener() {
                            @Override
                            public void getBitmap(Bitmap bitmap) {
                            }
                        });
                break;
        }


    }
}
