package com.lpan.study.fragment;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.contract.MyhttpContract;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.model.ConfigInfo;
import com.lpan.study.presenter.impl.MyHttpPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lpan on 2017/11/16.
 */

public class MyHttpClientFragment extends ButterKnifeFragment implements View.OnClickListener, MyhttpContract.View {

    @BindView(R.id.text1)
    TextView getButton;

    @BindView(R.id.text2)
    TextView postButton;

    @BindView(R.id.text3)
    TextView singleUpButton;

    @BindView(R.id.text4)
    TextView singleDownButton;

    @BindView(R.id.text)
    TextView resultText;

    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;

    private MyhttpContract.Presenter mPresenter;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_myhttpclient;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mPresenter = new MyHttpPresenter(this);
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                mPresenter.get();

                break;
            case R.id.text2:
                mPresenter.post();

                break;
            case R.id.text3:
                mPresenter.singleUp();

                break;
            case R.id.text4:
                mPresenter.singleDown();

                break;
        }
    }

    @Override
    public void setPresenter(MyhttpContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showResult(ConfigInfo configInfo) {
        if (configInfo != null) {
            resultText.setText(configInfo.toString());
        } else {
            resultText.setText("no result");
        }

    }
}
