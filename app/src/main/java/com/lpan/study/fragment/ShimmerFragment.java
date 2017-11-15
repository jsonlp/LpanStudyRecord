package com.lpan.study.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.view.ShimmerFrameLayout;
import com.lpan.R;

import java.util.List;

/**
 * Created by lpan on 2016/12/21.
 */
public class ShimmerFragment extends BaseFragment {

    private ListView mListView;

    private AllDescShowAdapter mAdapter;

    private ShimmerFrameLayout mShimmerFrameLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_shimmer;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mShimmerFrameLayout= (ShimmerFrameLayout) view.findViewById(R.id.shimmer_view_container);
        mShimmerFrameLayout.setBaseAlpha(0f);
//        mShimmerFrameLayout.setDropoff(0.1f);
        mShimmerFrameLayout.setTilt(0);
        mShimmerFrameLayout.setDuration(1500);
        mShimmerFrameLayout.startShimmerAnimation();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    private AllDescShowAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new AllDescShowAdapter();
        }
        return mAdapter;
    }

    class AllDescShowAdapter extends BaseAdapter {

        private List<String> mList;

        public AllDescShowAdapter(){

        }



        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
