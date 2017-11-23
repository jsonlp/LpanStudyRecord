package com.lpan.study.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lpan.study.adapter.GalleryAdapter;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.listener.OnRowAdapterClickListener;
import com.lpan.study.model.ImageInfo;
import com.lpan.study.task.ScanGalleryTask;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.ScanUtils;
import com.lpan.study.view.DividerGridItemDecoration;
import com.lpan.R;
import com.lpan.study.view.smilefaceview.SmileLoadingView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lpan on 2017/9/1.
 */

public class GalleryFragment extends BaseFragment implements OnRowAdapterClickListener<ImageInfo> {

    private RecyclerView mRecyclerView;

    private GalleryAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private SmileLoadingView mSmileLoadingView;

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mSmileLoadingView = (SmileLoadingView) view.findViewById(R.id.smileloadinbg);
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


        mSmileLoadingView.setVisibility(View.VISIBLE);
        Observable.create(new ObservableOnSubscribe<List<ImageInfo>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<ImageInfo>> e) throws Exception {
                List<ImageInfo> imageInfos = ScanUtils.scanImages();
//                List<ImageInfo> imageInfos = new ArrayList<ImageInfo>();

                e.onNext(imageInfos);
                if (Log.DEBUG) {
                    Log.d("GalleryFragment", "subscribe--------" + Thread.currentThread().getName());
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ImageInfo>>() {
                    @Override
                    public void accept(List<ImageInfo> list) throws Exception {
                        mSmileLoadingView.setVisibility(View.GONE);
                        getAdapter().addItem(list);
                        getAdapter().notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public GalleryAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new GalleryAdapter(getActivity(), this);
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
        for (ImageInfo imageInfo1 : list) {
            arrayList.add(imageInfo1.getUrl());
        }
        ImageDetailFragment.show(getActivity(), arrayList, view, position);
    }
}
