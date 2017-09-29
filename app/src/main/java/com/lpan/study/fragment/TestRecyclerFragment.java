package com.lpan.study.fragment;


import com.lpan.study.adapter.TestRecyclerAdapter;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.model.CardDataItem;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lpan on 2017/9/29.
 */

public class TestRecyclerFragment extends BaseFragment {

    private TestRecyclerAdapter mAdapter;


    @Override
    protected int getLayoutResource() {
        return super.getLayoutResource();

    }

    private Integer imagePaths[] = {
            R.drawable.wall01, R.drawable.wall02,
            R.drawable.wall03, R.drawable.wall04,
            R.drawable.wall05, R.drawable.wall06,
            R.drawable.wall07, R.drawable.wall08,
            R.drawable.wall09, R.drawable.wall10}; // 20个图片资源

    private List<Integer> mImageList = new ArrayList<>();



    private void getData(int count) {
        int x = count / 10 + 1;
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, imagePaths);
        for (int i = 0; i < x; i++) {
            mImageList.addAll(list);
        }

        for (int i = 0; i < count; i++) {
            CardDataItem cardDataItem = new CardDataItem(mImageList.get(i), "jason" + i, i, i);
//            getAdapter().addItem(cardDataItem);
        }
    }
}
