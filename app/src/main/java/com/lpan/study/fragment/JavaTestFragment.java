package com.lpan.study.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.RxjavaTestUtils;
import com.lpan.R;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lpan on 2017/10/9.
 * 参考:http://blog.csdn.net/xiaochunyong/article/details/7748713
 */

public class JavaTestFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.text1)
    TextView mByteResult;

    @BindView(R.id.edit1)
    EditText mByteInput;

    @BindView(R.id.edit2)
    EditText mFirstNumInput;

    @BindView(R.id.edit3)
    EditText mOperatorsInput;

    @BindView(R.id.edit4)
    EditText mSecondNumInput;

    @BindView(R.id.edit5)
    EditText mTimeInput;

    @BindView(R.id.text2)
    TextView mOperatorsDo;

    @BindView(R.id.text3)
    TextView mOperatorsResult;

    @BindView(R.id.text4)
    TextView mRxjavaTest;

    @BindView(R.id.text5)
    TextView mTimeFormat;

    @BindView(R.id.text6)
    TextView mGetDegree;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_java_test;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        mByteInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    return;
                }
                String string = s.toString();
                Integer integer = -1;
                try {
                    integer = Integer.valueOf(string);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (integer == -1) {
                    mByteResult.setText("只能输入数字/-");
                } else {
                    mByteResult.setText(Integer.toBinaryString(integer));
                }
            }
        });
    }

    @Override
    protected boolean useButterknife() {
        return true;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text4, R.id.text5, R.id.text6})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.text2:
                String result = getResult(Integer.valueOf(mFirstNumInput.getText().toString())
                        , Integer.valueOf(TextUtils.isEmpty(mSecondNumInput.getText().toString()) ? "0" : mSecondNumInput.getText().toString())
                        , mOperatorsInput.getText().toString());
                mOperatorsResult.setText(result);
                break;
            case R.id.text4:
                rxjavaTest();
                break;

            case R.id.text5:
                String s = formatTime(Long.valueOf(mTimeInput.getText().toString()));
                long justNow = System.currentTimeMillis() - 1000 * 60 * 4;
                long today = System.currentTimeMillis() - 1000 * 60 * 60;
                long yesterday = 1516784078837l + 1000 * 60 * 60 * 24;
                long date = 1506784078837l;
                s = formatTime(yesterday);
                mTimeFormat.setText(s);
                break;

            case R.id.text6:
                getDegree();
                getDistance(1, 1, 3, 3);
                break;

            default:
                break;
        }
    }

    private void rxjavaTest() {
        RxjavaTestUtils.start();
    }

    private String getResult(int firstNum, int secondNum, String operators) {
        int result = -1;
        if (TextUtils.equals(operators, "<<")) {
            // 1左移动
            result = firstNum << secondNum;
        } else if (TextUtils.equals(operators, ">>")) {
            // 2右移动
            result = firstNum >> secondNum;
        } else if (TextUtils.equals(operators, ">>>")) {
            // 3无符号右移
            result = firstNum >>> secondNum;
        } else if (TextUtils.equals(operators, "&")) {
            // 4 位与&
            result = firstNum & secondNum;
        } else if (TextUtils.equals(operators, "|")) {
            // 5 位或|
            result = firstNum | secondNum;
        } else if (TextUtils.equals(operators, "^")) {
            // 6 位异或^
            result = firstNum ^ secondNum;
        } else if (TextUtils.equals(operators, "~")) {
            // 7 位非~
            result = ~firstNum;
        }

        return result + "";
    }

    private String formatTime(long time) {
        long now = System.currentTimeMillis();
        long d = now - time;

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0);
        int firstDayOffNew = calendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        long lastDayTime = calendar.getTimeInMillis();
        if (d <= 1000 * 60 * 5) {
            return "刚刚";
        } else if (d < time - lastDayTime) {
            return new SimpleDateFormat("HH:mm", Locale.CHINA).format(time);
        } else if (time > lastDayTime) {
            return "昨天";
        }

        return new SimpleDateFormat("MM月dd日", Locale.CHINA).format(time);
    }

    private void getDegree() {
        double atan = Math.atan(1.732);
        double v = Math.toDegrees(atan);
        if (Log.DEBUG) {
            Log.d("JavaTestFragment", "getDegree--------a=" + v);
        }
    }

    private void getDistance(int startX, int startY, int endX, int endY) {
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);

        double v = Math.pow(dx, 2) + Math.pow(dy, 2);
        double sqrt = Math.sqrt(v);
    }


}
