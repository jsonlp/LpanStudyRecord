package com.lpan.study.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lpan.study.model.MiddleClassInfo;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/6/6.
 */

public class CalculateAlphaFragment extends BaseFragment implements View.OnClickListener {

    private static final long YEAR = (1 << 12) - 1;//年份 最大年份到 4095   111111111111

    private static final long CLASS = (1 << 7) - 1;//最大127  1111111

    private EditText mEditText;

    private TextView mTextView;

    private EditText mEditText2;

    private TextView mTextView2;

    private TextView mTextView3;

    private TextView mTextView4;

    private EditText mEditText3, mEditText4, mEditText5, mEditText6, mEditText7;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_calculate_alpha;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mEditText = (EditText) view.findViewById(R.id.edit1);
        mEditText2 = (EditText) view.findViewById(R.id.edit2);
        mEditText3 = (EditText) view.findViewById(R.id.edit3);
        mEditText4 = (EditText) view.findViewById(R.id.edit4);
        mEditText5 = (EditText) view.findViewById(R.id.edit5);
        mEditText6 = (EditText) view.findViewById(R.id.edit6);
        mEditText7 = (EditText) view.findViewById(R.id.edit7);

        mTextView = (TextView) view.findViewById(R.id.text1);
        mTextView2 = (TextView) view.findViewById(R.id.text2);
        mTextView3 = (TextView) view.findViewById(R.id.text3);
        mTextView4 = (TextView) view.findViewById(R.id.text4);

        mTextView2.setOnClickListener(this);
        mTextView4.setOnClickListener(this);

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

        mEditText2.setText("121132554209");
        mEditText3.setText("2000");
        mEditText4.setText("1");
        mEditText5.setText("2");
        mEditText6.setText("3");
        mEditText7.setText("4");

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text2:
                String str = mEditText2.getText().toString();
                long l = Long.parseLong(str);
                MiddleClassInfo middleSchoolInfo = analysisSchoolCfg(l);
                mTextView3.setText(middleSchoolInfo.toString());
                break;

            case R.id.text4:
                MiddleClassInfo schoolInfo = new MiddleClassInfo(Long.parseLong(mEditText3.getText().toString()),
                        Long.parseLong(mEditText4.getText().toString()),
                        Long.parseLong(mEditText5.getText().toString()),
                        Long.parseLong(mEditText6.getText().toString()),
                        Long.parseLong(mEditText7.getText().toString()));
                long l1 = mergeCfgToId(schoolInfo);
                mTextView3.setText(String.valueOf(l1));
                mEditText2.setText(String.valueOf(l1));

                break;
        }

    }


    public static long mergeCfgToId(MiddleClassInfo classInfo) {
        long year = classInfo.getYear();
        long oneYear = classInfo.getFirstGrade();
        long twoYear = classInfo.getSecondGrade();
        long threeClass = classInfo.getThirdGrade();
        long fourClass = classInfo.getFourthGrade();

        long v = year;
        v += oneYear << 12;
        v += twoYear << 19;
        v += threeClass << 26;
        v += fourClass << 33;

        return v;
    }

    public MiddleClassInfo analysisSchoolCfg(long value) {
        long year = value & YEAR;
        value = value >> 12;

        long oneYear = value & CLASS;
        value = value >> 7;

        long twoYear = value & CLASS;
        value = value >> 7;

        long threeYear = value & CLASS;
        value = value >> 7;

        long fourYear = value & CLASS;

        return new MiddleClassInfo(year, oneYear, twoYear, threeYear, fourYear);
    }

}
