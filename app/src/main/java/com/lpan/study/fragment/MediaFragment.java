package com.lpan.study.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.lpan.study.adapter.NavegationAdapter;
import com.lpan.study.listener.OnRowAdapterClickListener;
import com.lpan.study.utils.FragmentUtils;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/6/6.
 */

public class MediaFragment extends BaseFragment implements OnRowAdapterClickListener<String> {

    private ListView mListView;

    private NavegationAdapter mAdapter;

    private List<String> mList;

    private List<Fragment> mToActivityList;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_tools;
    }

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        mList.add("AudioFocus");
        mList.add("video transform");
        mList.add("scan gallery");
        mList.add("compress image");
        mList.add("gif image");
        mList.add("video list player");
        mList.add("video recyclerview player");


        mToActivityList = new ArrayList<>();
        mToActivityList.add(new AudioFocusTestFragment());
        mToActivityList.add(new VideoPlayFragment());
        mToActivityList.add(new ScanGalleryFragment());
        mToActivityList.add(new ZoomImageFragment());
        mToActivityList.add(new GifImageFragment());
        mToActivityList.add(new VideoListPlayerFragment());
        mToActivityList.add(new VideoRecyclerFragment());


        getAdapter().addItems(mList);
        mListView.setAdapter(getAdapter());

    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mListView = (ListView) view.findViewById(R.id.listview);
    }

    private NavegationAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new NavegationAdapter(this, getActivity());
        }
        return mAdapter;
    }

    @Override
    public void onLongClick(View view, String s, int position) {

    }

    @Override
    public void onClick(View view, String s, int position) {
//        if (s.equals("zoom image")) {
//            FragmentUtils.navigateToInFullScreenActivity(getActivity(), mToActivityList.get(position), null);
//        } else {
        FragmentUtils.navigateToInNewActivity(getActivity(), mToActivityList.get(position), null);
//        }
    }
}
