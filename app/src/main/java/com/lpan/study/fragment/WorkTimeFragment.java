package com.lpan.study.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.fragment.base.BaseActionbarFragment;
import com.lpan.study.model.TimeInfo;
import com.lpan.study.utils.CollectionUtils;
import com.lpan.study.utils.PermissionUtils;
import com.lpan.study.utils.WorkTimer;
import com.lpan.study.view.actionbar.ActionbarConfig;

import java.io.File;
import java.util.List;

import butterknife.BindView;


/**
 * Created by lpan on 2018/9/17.
 */
public class WorkTimeFragment extends BaseActionbarFragment {

    @BindView(R.id.switch_button) SwitchCompat mSwitchCompat;
    @BindView(R.id.edit1) EditText mEditText;
    @BindView(R.id.button1) Button mButton;
    @BindView(R.id.day_tv) TextView mDayTv;
    @BindView(R.id.hours_tv) TextView mHoursTv;
    @BindView(R.id.date_tv) TextView mDateTv;
    @BindView(R.id.rest_tv) TextView mRestTv;
    @BindView(R.id.early_tv) TextView mEarlyTv;
    @BindView(R.id.last_tv) TextView mLastTv;

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mSwitchCompat.setChecked(true);
        mSwitchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> handleData(isChecked));
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleData(mSwitchCompat.isChecked());
            }
        });
    }

    @Override
    protected ActionbarConfig getActionbarConfig() {
        return getDefaultActionbar("");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_work_time;
    }

    @Override
    protected void initData() {
        super.initData();
        if (PermissionUtils.hasPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            handleData(mSwitchCompat.isChecked());
        } else {
            PermissionUtils.requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, this, PermissionUtils.PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionUtils.PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                handleData(mSwitchCompat.isChecked());
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void handleData(boolean filterDay) {
        String text = WorkTimer.readText(getFilePath());
        TimeInfo timeInfo = WorkTimer.handleData(text, filterDay);
        bindData(timeInfo);
    }

    private void bindData(TimeInfo timeInfo) {
        mDayTv.setText(timeInfo.getDays() + "d");
        mDateTv.setText("Date: " + timeInfo.getFromDate() + " to " + timeInfo.getEndDate());
        mHoursTv.setText("Hours: " + WorkTimer.formatDouble1(timeInfo.getHours()));
        mRestTv.setText(getRest(timeInfo.getRest()));
        mEarlyTv.setText("Early: " + timeInfo.getMostEarly());
        mLastTv.setText("Last: " + timeInfo.getMostLast());
    }

    private String getRest(List<String> rest) {
        if (CollectionUtils.isEmpty(rest)) {
            return "Rest: no";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Rest: ");
        for (int i = 0; i < rest.size(); i++) {
            stringBuilder.append(rest.get(i));
            if (i != rest.size() - 1) {
                stringBuilder.append("、");
            }
        }
        return stringBuilder.toString();
    }

    private String getFilePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + mEditText.getText().toString() + ".txt";
    }

}
