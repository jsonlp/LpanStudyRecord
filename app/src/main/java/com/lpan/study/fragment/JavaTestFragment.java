package com.lpan.study.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.utils.RxjavaTestUtils;
import com.lpan.R;


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

    @BindView(R.id.text2)
    TextView mOperatorsDo;

    @BindView(R.id.text3)
    TextView mOperatorsResult;

//    @BindView(R.id.text4)
//    TextView mRxjavaTest;

    @BindView(R.id.image1)
    ImageView mImage1;


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

    @OnClick({R.id.text1, R.id.text2, R.id.text4})
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




}
