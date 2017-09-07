package com.lpan.study.fragment.home;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.lpan.study.adapter.NavegationAdapter;
import com.lpan.study.fragment.BaseFragment;
import com.lpan.study.fragment.ClickableSpanDemoFragment;
import com.lpan.study.fragment.CustomCardViewFragment;
import com.lpan.study.fragment.DoubleListFragment;
import com.lpan.study.fragment.DragListFragment;
import com.lpan.study.fragment.GroupImageFragment;
import com.lpan.study.fragment.LoadingAnimationFragment;
import com.lpan.study.fragment.ShimmerFragment;
import com.lpan.study.fragment.SlidePanelFragment;
import com.lpan.study.fragment.VideoPlayStatuFragment;
import com.lpan.study.fragment.VisibilityUtilsFrament;
import com.lpan.study.listener.OnRowAdapterClickListener;
import com.lpan.study.utils.FragmentUtils;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/6/6.
 */

public class CustomViewFragment extends BaseFragment implements OnRowAdapterClickListener<String> {

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
        mList.add("smile Loading");
        mList.add("Android Shimmer");
        mList.add("ClickableSpan");
        mList.add("group avatar");
        mList.add("drag and sort list");
        mList.add("double list");
        mList.add("view visibility percent");
        mList.add("custom card view");
        mList.add("video play statu");
        mList.add("slide panel");

        mToActivityList = new ArrayList<>();
        mToActivityList.add(new LoadingAnimationFragment());
        mToActivityList.add(new ShimmerFragment());
        mToActivityList.add(new ClickableSpanDemoFragment());
        mToActivityList.add(new GroupImageFragment());
        mToActivityList.add(new DragListFragment());
        mToActivityList.add(new DoubleListFragment());
        mToActivityList.add(new VisibilityUtilsFrament());
        mToActivityList.add(new CustomCardViewFragment());
        mToActivityList.add(new VideoPlayStatuFragment());
        mToActivityList.add(new SlidePanelFragment());


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
