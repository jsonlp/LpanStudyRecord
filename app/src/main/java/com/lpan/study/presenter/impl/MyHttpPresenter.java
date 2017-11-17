package com.lpan.study.presenter.impl;

import com.bluelinelabs.logansquare.ParameterizedType;
import com.lpan.study.constants.HttpConstants;
import com.lpan.study.contract.MyhttpContract;
import com.lpan.study.http.BaseGetRequest;
import com.lpan.study.http.BaseHttpCallback;
import com.lpan.study.http.BasePostRequest;
import com.lpan.study.jsonparser.LoganResponse;
import com.lpan.study.loganresponse.BaseListResponse;
import com.lpan.study.loganresponse.SuperInterestUpdateResponse;
import com.lpan.study.model.ConfigInfo;
import com.lpan.study.model.Meta;
import com.lpan.study.model.SuperInnteresInfo;
import com.lpan.study.utils.CollectionUtils;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.Toaster;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

/**
 * Created by lpan on 2017/11/17.
 */

public class MyHttpPresenter implements MyhttpContract.Presenter {

    private MyhttpContract.View mView;

    public MyHttpPresenter(MyhttpContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void get() {
        new BaseGetRequest<ConfigInfo>(new BaseHttpCallback<ConfigInfo>() {

            @Override
            protected void onRequestStart() {
                super.onRequestStart();
                if (Log.DEBUG) {
                    Log.d("MyHttpClientFragment", "onRequestStart--------");
                }
                mView.showLoading(true);
            }

            @Override
            protected void onRequestFinished() {
                super.onRequestFinished();
                if (Log.DEBUG) {
                    Log.d("MyHttpClientFragment", "onRequestFinished--------");
                }
                mView.showLoading(false);
            }

            @Override
            public void onRequestSuccess(ConfigInfo configInfo) {
                if (Log.DEBUG) {
                    Log.d("MyHttpClientFragment", "onRequestSuccess--------" + configInfo.toString() + "\n");
                }
                mView.showResult(configInfo);
            }

            @Override
            protected void onRequestFail(Meta meta) {
                super.onRequestFail(meta);
                mView.showResult(null);

            }
        }) {
            @Override
            public String getUrl() {
                return HttpConstants.GET_URL_ERROR;
            }

            @Override
            public ParameterizedType<LoganResponse<ConfigInfo>> getType() {
                return new ParameterizedType<LoganResponse<ConfigInfo>>() {
                };
            }
        }.start();
    }

    @Override
    public void post() {
        new BasePostRequest<SuperInterestUpdateResponse>(new BaseHttpCallback<SuperInterestUpdateResponse>() {
            @Override
            public void onRequestSuccess(SuperInterestUpdateResponse superInterestUpdateResponse) {
                BaseListResponse<SuperInnteresInfo> superInterest = superInterestUpdateResponse.getSuperInterest();
                if (superInterest != null && !CollectionUtils.isEmpty(superInterest.getList())) {
                    String desc = superInterest.getList().get(0).getDesc();
                    Toaster.toastShort(desc);
                }
            }
        }) {
            @Override
            public RequestBody getRequestBody() {
                FormEncodingBuilder builder = new FormEncodingBuilder();
                builder.add("interest", "qa6HqbbeKAlvid1aI1CONw");
                builder.add("desc", "12345");

                return builder.build();
            }

            @Override
            public String getUrl() {
                return HttpConstants.POST_URL;
            }

            @Override
            public ParameterizedType<LoganResponse<SuperInterestUpdateResponse>> getType() {
                return new ParameterizedType<LoganResponse<SuperInterestUpdateResponse>>() {
                };
            }
        }.start();
    }

    @Override
    public void singleUp() {

    }

    @Override
    public void singleDown() {

    }
}
