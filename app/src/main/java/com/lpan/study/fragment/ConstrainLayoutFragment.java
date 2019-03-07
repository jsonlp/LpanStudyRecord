package com.lpan.study.fragment;

import android.annotation.SuppressLint;
import android.util.Log;

import com.lpan.R;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.model.PostInfo;
import com.lpan.study.model.VideoInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lpan on 2019/2/12.
 */
public class ConstrainLayoutFragment extends BaseFragment {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_constraint_layout;
    }

    @Override
    protected void initData() {
        super.initData();
        List<VideoInfo> list = new ArrayList<>();
        VideoInfo videoInfo1 = new VideoInfo();
        VideoInfo videoInfo2 = new VideoInfo();
        VideoInfo videoInfo3 = new VideoInfo();
        VideoInfo videoInfo4 = new VideoInfo();
        VideoInfo videoInfo5 = new VideoInfo();
        VideoInfo videoInfo6 = new VideoInfo();

        videoInfo1.setTitle("111111");
        videoInfo2.setTitle("222222");
        videoInfo3.setTitle("3");
        videoInfo4.setTitle("444444");
        videoInfo5.setTitle("555555");
        videoInfo6.setTitle("666666");

        list.add(videoInfo1);
        list.add(videoInfo2);
        list.add(videoInfo3);
        list.add(videoInfo4);
        list.add(videoInfo5);
        list.add(videoInfo6);

        testData(list);
    }

    @SuppressLint("CheckResult")
    private void testData(List<VideoInfo> list) {
        Observable.just(list)
                .map(new Function<List<VideoInfo>, List<PostInfo>>() {
                    @Override
                    public List<PostInfo> apply(List<VideoInfo> list) throws Exception {
                        int i = 3 / 2;
                        int j = 5 / 2;
                        Log.d("ConstrainLayoutFragment", "map--------i=" + i + " j=" + j);
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PostInfo>>() {
                    @Override
                    public void accept(List<PostInfo> postInfos) throws Exception {
                        Log.d("ConstrainLayoutFragment", "success--------" + Thread.currentThread().getName());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("ConstrainLayoutFragment", "fail--------" + Thread.currentThread().getName());
                    }
                });
    }

}
