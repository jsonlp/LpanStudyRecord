package com.lpan.study.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.model.VideoInfo;
import com.lpan.study.utils.FragmentUtils;
import com.lpan.R;

/**
 * Created by lpan on 2017/2/23.
 */

public class VideoFullScreenFragment extends BaseFragment {

    public static final String EXTRA_VIDEO_INFO = "extra_video_info";

    public static final String EXTRA_IMAGE_PATH = "extra_extra_path";

    private ImageView mImageView;

    private VideoInfo mVideoInfo;

    private String mImagePath;

    public static void show(Context context, VideoInfo videoInfo,View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_VIDEO_INFO, videoInfo);
        FragmentUtils.navigateToInNewActivityWithTranstion(context,view, new VideoFullScreenFragment(), bundle);

    }

    public static void show(Context context, String imagePath ,View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_IMAGE_PATH, imagePath);
        FragmentUtils.navigateToInNewActivityWithTranstion(context,view, new VideoFullScreenFragment(), bundle);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImagePath = getArguments().getString(EXTRA_IMAGE_PATH);
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
        ViewCompat.setTransitionName(mImageView,"image");
    }

    @Override
    protected void initData() {
        super.initData();
        if (!TextUtils.isEmpty(mImagePath)) {
            mImageView.setImageURI(Uri.parse(mImagePath));
            return;
        }

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
