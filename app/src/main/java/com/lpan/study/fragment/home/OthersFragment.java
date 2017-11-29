package com.lpan.study.fragment.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.lpan.study.adapter.NavegationAdapter;
import com.lpan.study.constants.Constants;
import com.lpan.study.fragment.PermissionFragment;
import com.lpan.study.fragment.TransAnimationFragment;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.fragment.ConcrrentModificationFragment;
import com.lpan.study.listener.OnRowAdapterClickListener;
import com.lpan.study.utils.FragmentUtils;
import com.lpan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/6/6.
 */

public class OthersFragment extends BaseFragment implements OnRowAdapterClickListener<String> {

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
        mList.add("concurrent modification ");
        mList.add("transform animation ");
        mList.add("android6.0 permission ");

        mToActivityList = new ArrayList<>();
        mToActivityList.add(new ConcrrentModificationFragment());
        mToActivityList.add(new TransAnimationFragment());
        mToActivityList.add(new PermissionFragment());

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
        Bundle bundle = new Bundle();
//        bundle.putBoolean(Constants.NO_ANIMATION,true);
//        bundle.putBoolean(Constants.ANIMATION_UP,true);
//        bundle.putBoolean(Constants.ANIMATION_FADE,true);
//        bundle.putBoolean(Constants.ANIMATION_LONG_FADE,true);

        FragmentUtils.navigateToInNewActivity(getActivity(), mToActivityList.get(position), bundle);
//        }
    }
}
