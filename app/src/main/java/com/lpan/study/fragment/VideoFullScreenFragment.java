package com.lpan.study.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lpan.study.context.AppContext;
import com.lpan.study.model.VideoInfo;
import com.lpan.study.utils.FileUtils;
import com.lpan.study.utils.FragmentUtils;
import com.test.lpanstudyrecord.R;

import java.io.File;

/**
 * Created by lpan on 2017/2/23.
 */

public class VideoFullScreenFragment extends BaseFragment {

    public static final String EXTRA_VIDEO_INFO = "extra_video_info";

    private ImageView mImageView;

    private VideoInfo mVideoInfo;

    public static void show(Context context, VideoInfo videoInfo) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_VIDEO_INFO, videoInfo);
        FragmentUtils.navigateToInNewActivity(context, new VideoFullScreenFragment(), bundle);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mVideoInfo = (VideoInfo) getArguments().getSerializable(EXTRA_VIDEO_INFO);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video_full_screen;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mImageView = (ImageView) view.findViewById(R.id.image1);

    }

    @Override
    protected void initData() {
        super.initData();


        if (!TextUtils.isEmpty(mVideoInfo.getThumb())) {

            int[] imageWidthHeight = getImageWidthHeight(mVideoInfo.getThumb());
            Toast.makeText(AppContext.getContext(), "width=" + imageWidthHeight[0] + "   height=" + imageWidthHeight[1], Toast.LENGTH_SHORT).show();
            if (imageWidthHeight[0] >= imageWidthHeight[1]) { //横着的
                mImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            } else {
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            mImageView.setImageURI(Uri.parse(mVideoInfo.getThumb()));

        } else {
//            Bitmap videoThumbnail = FileUtils.getVideoThumbnail(mVideoInfo.getPath(), mVideoInfo.getWidth(), mVideoInfo.getHeight(), MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
//            FileUtils.saveBitmap(videoThumbnail, new File(FileUtils.getVideoFileDir(), System.currentTimeMillis() + ".png"), false, 0);
//            mImageView.setImageBitmap(videoThumbnail);
        }

    }

    public int[] getImageWidthHeight(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null
        return new int[]{options.outWidth, options.outHeight};
    }
}
