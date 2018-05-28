package com.lpan.study.fragment.home;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.lpan.study.adapter.NavegationAdapter;
import com.lpan.study.fragment.AddLogoFragment;
import com.lpan.study.fragment.DeviceInfoFragment;
import com.lpan.study.fragment.HttpStudyFragment;
import com.lpan.study.fragment.MyDialogFragment;
import com.lpan.study.fragment.JavaTestFragment;
import com.lpan.study.fragment.MyHttpClientFragment;
import com.lpan.study.fragment.ThreadsTestFragment;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.fragment.CalculateAlphaFragment;
import com.lpan.study.listener.OnRowAdapterClickListener;
import com.lpan.study.utils.FragmentUtils;
import com.lpan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/6/6.
 */

public class ToolsFragment extends BaseFragment implements OnRowAdapterClickListener<String> {

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
        mList.add("calculate alpha");
        mList.add("add logo");
        mList.add("java test");
        mList.add("device info");
        mList.add("remind");
//        mList.add("ok http");
        mList.add("android net");
        mList.add("android threads");


        mToActivityList = new ArrayList<>();
        mToActivityList.add(new CalculateAlphaFragment());
        mToActivityList.add(new AddLogoFragment());
        mToActivityList.add(new JavaTestFragment());
        mToActivityList.add(new DeviceInfoFragment());
        mToActivityList.add(new MyDialogFragment());
//        mToActivityList.add(new MyHttpClientFragment());
        mToActivityList.add(new HttpStudyFragment());
        mToActivityList.add(new ThreadsTestFragment());


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
