package com.lpan.study.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lpan.study.adapter.GalleryAdapter;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.listener.OnRowAdapterClickListener;
import com.lpan.study.model.ImageInfo;
import com.lpan.study.task.ScanGalleryTask;
import com.lpan.study.view.DividerGridItemDecoration;
import com.lpan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/9/1.
 */

public class GalleryFragment extends BaseFragment implements OnRowAdapterClickListener<ImageInfo>{

    private RecyclerView mRecyclerView;

    private GalleryAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity(), R.drawable.f2_rect));
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(getAdapter());

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_gallery;
    }

    @Override
    protected void initData() {
        super.initData();

        new ScanGalleryTask() {

            @Override
            protected List<ImageInfo> doInBackground(Void... params) {
                return super.doInBackground(params);
            }

            @Override
            protected void onPostExecute(List<ImageInfo> imageInfos) {
                getAdapter().addItem(imageInfos);
                getAdapter().notifyDataSetChanged();
            }
        }.execute();
    }

    public GalleryAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new GalleryAdapter(getActivity(),this);
        }
        return mAdapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        if (mLayoutManager == null) {
            mLayoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        }
        return mLayoutManager;
    }

    @Override
    public void onLongClick(View view, ImageInfo imageInfo, int position) {

    }

    @Override
    public void onClick(View view, ImageInfo imageInfo, int position) {
//        VideoFullScreenFragment.show(getActivity(),imageInfo.getUrl(),view);
        List<ImageInfo> list = getAdapter().getList();
        ArrayList<String> arrayList = new ArrayList<>();
        for (ImageInfo imageInfo1 : list){
            arrayList.add(imageInfo1.getUrl());
        }
        ImageDetailFragment.show(getActivity(),arrayList,view,position);
    }
}
