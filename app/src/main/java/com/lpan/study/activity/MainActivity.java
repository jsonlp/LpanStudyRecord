package com.lpan.study.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.adapter.MainViewpagerAdapter;
import com.lpan.study.fragment.home.CustomViewFragment;
import com.lpan.study.fragment.home.MediaFragment;
import com.lpan.study.fragment.home.OthersFragment;
import com.lpan.study.fragment.home.ToolsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2016/12/19.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager mViewPager;

    private TextView mNavegation1, mNavegation2, mNavegation3, mNavegation4;

    private MainViewpagerAdapter mAdapter;

    private List<TextView> mTextViews = new ArrayList<>();

    private ImageView mBackground;

    private int backgrounds[] = {
            R.drawable.wall01, R.drawable.wall09,
            R.drawable.wall03, R.drawable.wall04,
            R.drawable.wall05, R.drawable.wall06,
            R.drawable.wall07, R.drawable.wall08};

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mBackground = (ImageView) findViewById(R.id.background);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mNavegation1 = (TextView) findViewById(R.id.navegation1);
        mNavegation2 = (TextView) findViewById(R.id.navegation2);
        mNavegation3 = (TextView) findViewById(R.id.navegation3);
        mNavegation4 = (TextView) findViewById(R.id.navegation4);

        mNavegation1.setOnClickListener(this);
        mNavegation2.setOnClickListener(this);
        mNavegation3.setOnClickListener(this);
        mNavegation4.setOnClickListener(this);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
//        mBackground.setImageResource(backgrounds[(int) (Math.random() * backgrounds.length)]);

        List<Fragment> list = new ArrayList<>();
        list.add(new CustomViewFragment());
        list.add(new MediaFragment());
        list.add(new ToolsFragment());
        list.add(new OthersFragment());

        mAdapter = new MainViewpagerAdapter(getSupportFragmentManager(), list);

        mViewPager.setAdapter(mAdapter);

        mTextViews.add(mNavegation1);
        mTextViews.add(mNavegation2);
        mTextViews.add(mNavegation3);
        mTextViews.add(mNavegation4);

        selectTab(mNavegation1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navegation1:
                mViewPager.setCurrentItem(0);
                selectTab(mNavegation1);

                break;

            case R.id.navegation2:
                mViewPager.setCurrentItem(1);
                selectTab(mNavegation2);

                break;

            case R.id.navegation3:
                mViewPager.setCurrentItem(2);
                selectTab(mNavegation3);

                break;

            case R.id.navegation4:
                mViewPager.setCurrentItem(3);
                selectTab(mNavegation4);

                break;
        }

    }

    private void selectTab(TextView tv) {
        for (TextView textView : mTextViews) {
            if (textView == tv) {
                textView.setTextColor(MainActivity.this.getResources().getColor(R.color.red));
            } else {
                textView.setTextColor(MainActivity.this.getResources().getColor(R.color.black));
            }
        }
    }

    private void selectTab(int position) {

        switch (position) {
            case 0:
                selectTab(mNavegation1);
                break;

            case 1:
                selectTab(mNavegation2);
                break;

            case 2:
                selectTab(mNavegation3);
                break;

            case 3:
                selectTab(mNavegation4);
                break;
        }
    }
}
