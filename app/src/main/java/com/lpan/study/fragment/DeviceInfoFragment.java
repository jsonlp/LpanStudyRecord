package com.lpan.study.fragment;

import android.app.ActivityManager;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.utils.ViewUtils;
import com.test.lpanstudyrecord.R;

import butterknife.BindView;

/**
 * Created by lpan on 2017/10/10.
 */

public class DeviceInfoFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.text1)
    TextView mText1;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_device_info;
    }

    @Override
    protected boolean useButterknife() {
        return true;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("width = ");
        stringBuilder.append(ViewUtils.getScreenWidth(AppContext.getContext()));
        stringBuilder.append("\n");

        stringBuilder.append("height = ");
        stringBuilder.append(ViewUtils.getScreenHeight(AppContext.getContext()));
        stringBuilder.append("\n");

        stringBuilder.append("xdpi = ");
        stringBuilder.append(getResources().getDisplayMetrics().xdpi);
        stringBuilder.append("\n");

        stringBuilder.append("ydpi = ");
        stringBuilder.append(getResources().getDisplayMetrics().ydpi);
        stringBuilder.append("\n");

        stringBuilder.append("Heap size = ");
        ActivityManager systemService = (ActivityManager) (getActivity().getSystemService(Context.ACTIVITY_SERVICE));
        stringBuilder.append(systemService.getMemoryClass()+"M");
        stringBuilder.append("\n");


        mText1.setText(stringBuilder.toString());
    }

    @Override
    public void onClick(View v) {

    }

}
