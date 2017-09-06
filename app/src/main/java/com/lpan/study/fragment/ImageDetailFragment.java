package com.lpan.study.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lpan.study.utils.BitmapUtils;
import com.lpan.study.utils.FileUtils;
import com.lpan.study.utils.FragmentUtils;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/9/1.
 */

public class ImageDetailFragment extends BaseFragment {

    public static final String EXTRA_IMAGE_IAMGES = "extra_extra_images";

    private ViewPager mViewPager;

    private ImageDetailAdapter mAdapter;

    private ArrayList<String> mImages;

    public static void show(Context context, ArrayList<String> list, View view) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(EXTRA_IMAGE_IAMGES, list);
        FragmentUtils.navigateToInNewActivityWithTranstion(context, view, new ImageDetailFragment(), bundle);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImages = getArguments().getStringArrayList(EXTRA_IMAGE_IAMGES);
        }
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        getAdapter().setInfoList(mImages);
        mViewPager.setAdapter(getAdapter());
    }

    public ImageDetailAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new ImageDetailAdapter(getActivity());
        }
        return mAdapter;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_image_detail;
    }


    class ImageDetailAdapter extends PagerAdapter {

        private Context mContext;

        private List<String> mInfoList;

        public ImageDetailAdapter(Context context) {
            mContext = context;
        }

        public List<String> getInfoList() {
            return mInfoList;
        }

        public void setInfoList(List<String> infoList) {
            mInfoList = infoList;
        }

        @Override
        public int getCount() {
            return mInfoList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_pager_image_detail, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image1);

            String path = FileUtils.getImagePath(Uri.parse(mInfoList.get(position)));
            Bitmap bitmap = BitmapUtils.compressPhotoFileToBitmap(path, 1280,720);
            imageView.setImageBitmap(bitmap);
//            imageView.setImageURI(Uri.parse(mInfoList.get(position)));

            ViewCompat.setTransitionName(imageView,"image");

            container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }
    }
}
