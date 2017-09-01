package com.lpan.study.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lpan.study.context.AppContext;
import com.lpan.study.listener.MentionSpanClickListener;
import com.lpan.study.model.UserInfo;
import com.lpan.study.utils.FragmentUtils;
import com.lpan.study.utils.Utils;
import com.lpan.study.utils.ViewUtils;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by lpan on 2016/12/27.
 */
public class ClickableSpanDemoFragment extends BaseFragment implements MentionSpanClickListener, View.OnClickListener {

    private static final String TIPS = "大大大看得开";

    private static final String TIPS1 = "好友 %u 互动了";

    private static final String TIPS2 = "好友 %u,%u 等都在互动";

    private static final String MODLE = "%u";

    private static final String NAME1 = "张三";

    private static final String NAME2 = "李四";

    private static final String TEXT1 = "张鲁一的表现 还好，但表演痕迹还是有些重，个别段落难免过火。{@UfG3rXh2PcDW16qv20-Hlg}王凯让人失望，尚不如演戏多年未开窍的林心如走心，对人物的理解和塑造出了很大偏差。";

    private static final String TEXT2 = "张鲁一的表现 还好，但表演痕迹还是有些重，个别段落难免过火。{#id1}王凯让人失望，尚不如演戏多年未开窍的林心如{#id2}走心，对人物的理解和塑造出了很大偏差。";

    private static final String TEXT3 = "张鲁一的表现 还好，但表演痕迹还是有些重，\n个别段落难免过火。王凯让人失望，尚不如演戏多年未开窍的林心如走心，对人物的理解和塑造出了很大偏差。";

    private String mSchoolName="郑州大学州大学郑州大学.  ";

    private String mTag="热门";

    private TextView mTextView;

    private TextView mTextView2,mTextView3;

    private TextView mContent;

    private TextView mAllDescButton;

    private List<UserInfo> mInfoList = new ArrayList<>();


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_clickable_span;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mTextView = (TextView) view.findViewById(R.id.text);
        mTextView2 = (TextView) view.findViewById(R.id.text1);
        mTextView3 = (TextView) view.findViewById(R.id.text3);

        mTextView2.setMovementMethod(LinkMovementMethod.getInstance());
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        mContent = (TextView) view.findViewById(R.id.text2);
        mAllDescButton = (TextView) view.findViewById(R.id.all_desc);
        mAllDescButton.setOnClickListener(this);


        UserInfo userInfo = new UserInfo();
        userInfo.setId("UfG3rXh2PcDW16qv20-Hlg");
        userInfo.setName("张三");
        mInfoList.add(userInfo);

        mTextView.setText(addClickableSpan(TIPS2, MODLE, NAME1, NAME2), TextView.BufferType.SPANNABLE);
//        mTextView2.setText(addClickableSpan2(TEXT1, "{", "#上山打老虎#"), TextView.BufferType.SPANNABLE);
        Utils.setMentionText(AppContext.getContext(), mTextView2, TEXT1, mInfoList, false, this);

        mTextView3.setText(addDrawableSpan(mSchoolName,mTag), TextView.BufferType.SPANNABLE);
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 1; i < 10; i++) {
//            stringBuilder.append(i);
//            stringBuilder.append(":");
//            stringBuilder.append(TEXT3);
//        }
//        mContent.setText(stringBuilder.toString());
        mContent.setText(TEXT3);
        mContent.post(new Runnable() {
            @Override
            public void run() {
                if (mContent.getLineCount() >= 5) {
                    mAllDescButton.setVisibility(View.VISIBLE);
                } else {
                    mAllDescButton.setVisibility(View.GONE);
                }
            }
        });

//        calculateString(TEXT3);

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

    private SpannableStringBuilder addDrawableSpan(String school,String tag){
        SpannableStringBuilder ssb = new SpannableStringBuilder(school+""+tag);
        ssb.setSpan(new DynamicDrawableSpan() {
            @Override
            public Drawable getDrawable() {
                Bitmap bitmap = getBitmap();

                BitmapDrawable drawable = new BitmapDrawable(AppContext.getContext().getResources(),bitmap);
                drawable.setBounds(0,0,bitmap.getWidth(),bitmap.getHeight());
                return drawable;
            }
        }, school.length(), school.length() + tag.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }

    private Bitmap getBitmap() {

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(AppContext.getContext().getResources().getColor(R.color.black));
        paint.setTextSize(ViewUtils.spToPx(AppContext.getContext(),20));
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

        float width = paint.measureText(mTag);
        Bitmap bitmap = Bitmap.createBitmap((int) width+ViewUtils.dp2px(AppContext.getContext(),20), (int) (fontMetrics.descent - fontMetrics.ascent), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        paint.setColor(AppContext.getContext().getResources().getColor(R.color.red));

        RectF re31 = new RectF(0,0,width+ViewUtils.dp2px(AppContext.getContext(),20),ViewUtils.dp2px(AppContext.getContext(),20));
        canvas.drawRoundRect(re31, ViewUtils.dp2px(AppContext.getContext(),8), ViewUtils.dp2px(AppContext.getContext(),8), paint);

        paint.setColor(AppContext.getContext().getResources().getColor(R.color.white));
        canvas.drawText(mTag,0,mTag.length(),ViewUtils.dp2px(AppContext.getContext(),10),-fontMetrics.ascent,paint);

        return bitmap;
    }


    private SpannableStringBuilder addClickableSpan2(String str, String modle, final String name1) {
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

        ssb.setSpan(new DynamicDrawableSpan() {
            @Override
            public Drawable getDrawable() {
                Bitmap bitmap = BitmapFactory.decodeResource(AppContext.getContext().getResources(),R.mipmap.ic_launcher);
                BitmapDrawable drawable = new BitmapDrawable(AppContext.getContext().getResources(),bitmap);
                drawable.setBounds(0,0,bitmap.getWidth(),bitmap.getHeight());
                return drawable;
            }
        }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }


    @Override
    public void onMentionNameClick(UserInfo userInfo) {
        Toast.makeText(getView().getContext(), userInfo.getName(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_desc:
                Bundle bundle = new Bundle();
                bundle.putString(TextDetailFragment.EXTRA_CONTENT, mContent.getText().toString());
                FragmentUtils.navigateToInNewActivity(getActivity(), new TextDetailFragment(), bundle);
                break;

        }
    }

    private void calculateString(String s){
        int wd = 0;//单词数
        int by = 0;//字符数
        int sentence = 0;//句子数
        int lines = 0;//行数
        char num = 10;  //Ascii换行
        char nums = 13; //Ascii回车
        for (int i = 0; i < s.length(); i++) { //得到所输入的数据的长度，并遍历
            char c = s.charAt(i);
            if (c == ' ') wd++; //单词
            if (c==num ||c==nums) ++lines;
            if (c == '.' || c == '!' || c == '?')
                if (i > 0 && s.charAt(i - 1) != '.' && s.charAt(i - 1) != '?' && s.charAt(i - 1) != '!') {
                    sentence++;
                    wd++;
                }
            by++;
        }
        ++lines;
        mTextView3.setText("字符:"+by+"\n单词:"+wd+"\n句子:"+sentence+"\n行数:"+lines);
    }

}
