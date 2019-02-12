package com.lpan.study.fragment;

import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lpan on 2019/1/30.
 */
public class ViewStubFragment extends BaseFragment {

    @BindView(R.id.view_stub) ViewStub mViewStub;
    private TextView mStubChild;

    private View mLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_view_stub;
    }


    @OnClick({R.id.show_bt, R.id.gone_bt})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.show_bt:
                if (mLayout == null) {
                    mLayout = mViewStub.inflate();
                    mStubChild= mLayout.findViewById(R.id.stub_child_text);
                }
                mStubChild.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mStubChild.setText("changed");
                    }
                },2000);
                mViewStub.setVisibility(View.VISIBLE);
                break;

            case R.id.gone_bt:
                mViewStub.setVisibility(View.GONE);
                break;
        }
    }

}
