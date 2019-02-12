package com.lpan.study.fragment.base;

import android.view.View;

import com.lpan.R;
import com.lpan.study.view.actionbar.ActionbarConfig;
import com.lpan.study.view.actionbar.MyActionbarView;

import butterknife.BindView;

/**
 * Created by lpan on 2018/3/14.
 */

public abstract class BaseActionbarFragment extends BaseFragment {

    @BindView(R.id.my_actionbar)
    MyActionbarView mMyActionbarView;

    private View.OnClickListener mLeftClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        }
    };

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mMyActionbarView.configActionbar(getActionbarConfig());
    }

    protected abstract ActionbarConfig getActionbarConfig();

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_base_actionbar;
    }


    protected ActionbarConfig getDefaultActionbar(String title) {
        return new ActionbarConfig.Build()
                .setLeftImageId(R.drawable.actionbar_back_gray)
                .setOnLeftClickListener(mLeftClickListener)
                .setTitle(title)
                .build();
    }

}
