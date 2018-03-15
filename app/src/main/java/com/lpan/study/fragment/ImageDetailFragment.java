package com.lpan.study.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.utils.FragmentUtils;
import com.lpan.R;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.Variables;
import com.lpan.study.view.BaseImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/9/1.
 */

public class ImageDetailFragment extends BaseFragment {

    public static final String EXTRA_IMAGE_IAMGES = "extra_extra_images";

    public static final String EXTRA_IMAGE_POSITION = "extra_image_position";


    private ViewPager mViewPager;

    private ImageDetailAdapter mAdapter;

    private ArrayList<String> mImages;

    private int mPosition;


    public static void show(Context context, ArrayList<String> list, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(EXTRA_IMAGE_IAMGES, list);
        bundle.putInt(EXTRA_IMAGE_POSITION, position);
        FragmentUtils.navigateToInNewActivityWithTranstion(context, view, new ImageDetailFragment(), bundle);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImages = getArguments().getStringArrayList(EXTRA_IMAGE_IAMGES);
            mPosition = getArguments().getInt(EXTRA_IMAGE_POSITION);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    // handle back button
                    toastLong("back");
                    Variables.setExitIndex(mPosition);

                    return false;
                }
                return false;
            }
        });
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        getAdapter().setInfoList(mImages);
        mViewPager.setAdapter(getAdapter());

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPosition = position;
                if (Log.DEBUG) {
                    Log.d("ImageDetailFragment", "onPageSelected--------position=" + position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //这句代码很关键,不然顺序会乱掉
        mViewPager.setCurrentItem(mPosition);
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
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_pager_image_detail, null);
            BaseImageView imageView = (BaseImageView) view.findViewById(R.id.image1);
            imageView.setUrl(mContext, mInfoList.get(position));
            if (Log.DEBUG) {
                Log.d("ImageDetailAdapter", "instantiateItem--------position=" + position);
            }
            if (mPosition == position) {
                ViewCompat.setTransitionName(imageView, "image");
            }

            container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }
    }
}
