package com.lpan.study.fragment;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.study.contract.AddlogoContract;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.presenter.impl.AddlogoPresenter;
import com.lpan.R;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by lpan on 2017/6/14.
 */

public class AddLogoFragment extends BaseFragment implements View.OnClickListener, AddlogoContract.View {

    @BindView(R.id.text1)
    TextView mButton;

    @BindView(R.id.image1)
    ImageView mImageView;

    private AddlogoContract.Presenter mPresenter;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_add_logo;
    }


    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mPresenter = new AddlogoPresenter(this);
    }

    @OnClick({R.id.text1})
    @Override
    public void onClick(View v) {
        mPresenter.addLogo(R.drawable.wall01, R.drawable.wall02);
    }


    @Override
    public void setPresenter(AddlogoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showImage(Bitmap bitmap) {
        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        }
    }
}
