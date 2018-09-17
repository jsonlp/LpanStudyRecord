package com.lpan.study.fragment;

import android.view.View;
import android.widget.Button;

import com.lpan.R;
import com.lpan.study.fragment.base.ButterKnifeFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lpan on 2018/9/17.
 */
public class ShareLoginFragment extends ButterKnifeFragment {

    @BindView(R.id.share_wechat) Button mShareWechat;
    @BindView(R.id.share_qq) Button mShareQq;
    @BindView(R.id.share_facebook) Button mShareFacebook;
    @BindView(R.id.share_twitter) Button mShareTwitter;
    @BindView(R.id.login_wechat) Button mLoginWechat;
    @BindView(R.id.login_qq) Button mLoginQq;
    @BindView(R.id.login_facebook) Button mLoginFacebook;
    @BindView(R.id.login_twitter) Button mLoginTwitter;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_share_login;
    }


    @OnClick({R.id.share_wechat, R.id.share_qq, R.id.share_facebook, R.id.share_twitter, R.id.login_wechat, R.id.login_qq, R.id.login_facebook, R.id.login_twitter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_wechat:
                toastShort("share_wechat");

                break;
            case R.id.share_qq:
                toastShort("share_qq");

                break;
            case R.id.share_facebook:
                toastShort("share_facebook");

                break;
            case R.id.share_twitter:
                toastShort("share_twitter");

                break;
            case R.id.login_wechat:
                toastShort("login_wechat");

                break;
            case R.id.login_qq:
                toastShort("login_qq");

                break;
            case R.id.login_facebook:
                toastShort("login_facebook");

                break;
            case R.id.login_twitter:
                toastShort("login_twitter");

                break;
        }
    }
}
