package com.lpan.study.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/6/6.
 */

public class CalculateAlphaFragment extends BaseFragment {

    private EditText mEditText;

    private TextView mTextView;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_calculate_alpha;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mEditText = (EditText) view.findViewById(R.id.edit1);
        mTextView = (TextView) view.findViewById(R.id.text1);
    }

    @Override
    protected void initData() {
        super.initData();

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null) {
                    return;
                }
                String input = s.toString();
                if (TextUtils.isEmpty(input)) {
                    return;
                }
                mTextView.setText(calculate(Integer.parseInt(input)));
            }
        });
    }

    private String calculate(int value) {
        float temp = 255 * value * 1.0f / 100f;
        int round = Math.round(temp);//四舍五入
        String hexString = Integer.toHexString(round);
        if (hexString.length() < 2) {
            hexString += "0";
        }
        return hexString.toUpperCase();
    }
}
