package com.lpan.study.fragment;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2016/12/27.
 */
public class ClickableSpanDemoFragment extends BaseFragment {

    private static final String TIPS = "大大大看得开";

    private static final String TIPS1 = "好友 %u 互动了";

    private static final String TIPS2 = "好友 %u,%u 等都在互动";

    private static final String MODLE = "%u";

    private static final String NAME1 = "张三";

    private static final String NAME2 = "李四";

    private TextView mTextView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_clickable_span;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mTextView = (TextView) view.findViewById(R.id.text);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        mTextView.setText(addClickableSpan(TIPS1, MODLE, NAME1, NAME2), TextView.BufferType.SPANNABLE);

    }

    private SpannableStringBuilder addClickableSpan(String str, String modle, final String name1, final String name2) {
        if (!TextUtils.isEmpty(str)) {
            SpannableStringBuilder ssb = new SpannableStringBuilder(str);
            if (str.contains(modle)) {
                String[] strs = str.split(modle);
                if (strs.length < 2) { //没有

                } else if (strs.length < 3) {//1个
                    ssb.clear();
                    ssb.append(strs[0]);
                    int index1 = ssb.toString().length();
                    ssb.append(name1);
                    setSpanListener(ssb, name1, index1, index1 + name1.length());
                    ssb.append(strs[strs.length - 1]);
                } else {//2个
                    ssb.clear();
                    ssb.append(strs[0]);
                    int index2 = ssb.toString().length();
                    ssb.append(name1);
                    setSpanListener(ssb, name1, index2, index2 + name1.length());

                    ssb.append(strs[1]);
                    int index3 = ssb.toString().length();

                    ssb.append(name2);
                    setSpanListener(ssb, name2, index3, index3 + name2.length());
                    ssb.append(strs[strs.length - 1]);
                }
            }
            return ssb;
        }
        return null;
    }

    private void setSpanListener(SpannableStringBuilder ssb, final String newStr, int start, int end) {
        ssb.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getView().getContext(), newStr,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED); // 设置文本颜色
                ds.setUnderlineText(false);
            }
        }, start, end, 0);
    }


}
