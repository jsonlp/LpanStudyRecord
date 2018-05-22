package com.lpan.study.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lpan.R;
import com.lpan.study.adapter.CommonGridAdapter;
import com.lpan.study.fragment.base.BaseRecyclerFragment;
import com.lpan.study.model.BaseRecyclerItem;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.ViewUtils;
import com.lpan.study.view.actionbar.ActionbarConfig;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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

    private CommonGridAdapter mAdapter;

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
            mAdapter = new CommonGridAdapter(R.layout.item_grid_recycler, mList);
        }
        return mAdapter;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        getRecyclerView().setPadding(ViewUtils.dp2px(16),0,ViewUtils.dp2px(16),0);
    }

    @Override
    protected void initData() {
        super.initData();
        for (int i = 0; i < 30; i++) {
            BaseRecyclerItem item = new BaseRecyclerItem(0, "HttpURLConnection", R.drawable.wall01, 0);
            item.setUrl("http://pic6.nipic.com/20091207/3337900_161732052452_2.jpg");
            item.setTitle("no." + i);
            mList.add(item);
        }
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getActivity(), 3);
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
