package com.lpan.study.fragment;


import android.support.v7.widget.RecyclerView;
import android.util.TimeUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lpan.R;
import com.lpan.study.adapter.CommonReyclerAdapter;
import com.lpan.study.fragment.base.BaseRecyclerFragment;
import com.lpan.study.model.BaseRecyclerItem;
import com.lpan.study.utils.Log;
import com.lpan.study.view.actionbar.ActionbarConfig;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by lpan on 2018/4/24.
 */

public class HttpStudyFragment extends BaseRecyclerFragment {

    @BindView(R.id.recyclerview) RecyclerView mRecyclerView;

    private CommonReyclerAdapter mAdapter;

    @Override
    protected ActionbarConfig getActionbarConfig() {
        return getDefaultActionbar("android net");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_http_study;
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new CommonReyclerAdapter(R.layout.item_common_recycler_view, mList);
        }
        return mAdapter;
    }

    @Override
    protected void initData() {
        super.initData();
        mList.add(new BaseRecyclerItem(0, "HttpURLConnection", 0, 0));
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemClick(adapter, view, position);
        String url = "https://www.baidu.com";
        httpURLConnection(url);
    }

    private void httpURLConnection(final String urlString) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                HttpURLConnection httpURLConnection = null;

                try {
                    URL url = new URL(urlString);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setConnectTimeout(15000);
                    httpURLConnection.setReadTimeout(15000);
                    httpURLConnection.connect();


                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                    }
                    if (Log.DEBUG) {
                        Log.d("HttpStudyFragment", "subscribe--------" + responseCode);
                    }
                } catch (IOException ee) {
                    ee.printStackTrace();
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }
}
