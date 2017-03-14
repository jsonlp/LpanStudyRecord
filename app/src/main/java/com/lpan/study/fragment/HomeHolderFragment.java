package com.lpan.study.fragment;

import android.support.v4.app.Fragment;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ListView;

import com.lpan.study.adapter.HomeHolderAdapter;
import com.lpan.study.listener.OnRowAdapterClickListener;
import com.lpan.study.utils.FragmentUtils;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2016/12/19.
 */

public class HomeHolderFragment extends BaseFragment implements OnRowAdapterClickListener<String> {

    private ListView mListView;

    private HomeHolderAdapter mAdapter;

    private List<String> mList;

    private List<Fragment> mToActivityList;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home_holder;
    }

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        mList.add("smile Loading");
        mList.add("AudioFocus");
        mList.add("Android Shimmer");
        mList.add("ClickableSpan");
        mList.add("group avatar");
        mList.add("IO");
        mList.add("video transform");
        mList.add("scan gallery");
        mList.add("compress image");
        mList.add("draw image");


        mToActivityList = new ArrayList<>();
        mToActivityList.add(new LoadingAnimationFragment());
        mToActivityList.add(new AudioFocusTestFragment());
        mToActivityList.add(new ShimmerFragment());
        mToActivityList.add(new ClickableSpanDemoFragment());
        mToActivityList.add(new GroupImageFragment());
        mToActivityList.add(new IOTestFragment());
        mToActivityList.add(new VideoPlayFragment());
        mToActivityList.add(new ScanGalleryFragment());
        mToActivityList.add(new ZoomImageFragment());
        mToActivityList.add(new DrawImageFragment());

        getAdapter().addItems(mList);
        mListView.setAdapter(getAdapter());

    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mListView = (ListView) view.findViewById(R.id.listview);
    }

    private HomeHolderAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new HomeHolderAdapter(this, getActivity());
        }
        return mAdapter;
    }

    @Override
    public void onLongClick(View view, String s, int position) {

    }

    @Override
    public void onClick(View view, String s, int position) {
        if(s.equals("zoom image")){
            FragmentUtils.navigateToInFullScreenActivity(getActivity(), mToActivityList.get(position), null);
        }else{
            FragmentUtils.navigateToInNewActivity(getActivity(), mToActivityList.get(position), null);
        }
    }
}
