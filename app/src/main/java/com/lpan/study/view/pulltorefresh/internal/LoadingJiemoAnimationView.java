package com.lpan.study.view.pulltorefresh.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lpan.study.utils.CollectionUtils;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sks on 2015/5/20.
 */
public class LoadingJiemoAnimationView extends LinearLayout {

    private ImageView mJiemoImage;

    private TextView mJiemoText;

    private List<String> texts;

    private int maxIndex;

    private Random random;

    private String loadingText;

    private AnimationDrawable mAnimationDrawable;

    public LoadingJiemoAnimationView(Context context) {
        super(context);
        init(context);
    }

    public LoadingJiemoAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public LoadingJiemoAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingJiemoAnimationView(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
//        texts = context.getResources().getStringArray(R.array.refresh_text);
        texts = new ArrayList<>();
//        String[] local = context.getResources().getStringArray(R.array.refresh_text);
        List<String> loadingTips = new ArrayList<>();
        loadingTips.add("1111");
        loadingTips.add("2222");
        loadingTips.add("3333");

        if(!CollectionUtils.isEmpty(loadingTips)){
            texts.addAll(loadingTips);
        }
//        for (int i = 0; i < local.length; i++) {
//            texts.add(local[i]);
//        }
        maxIndex = texts.size();
        random = new Random();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mJiemoImage = (ImageView) findViewById(R.id.item);
//        mAnimationDrawable = (AnimationDrawable) AppContext.getContext().getResources()
//                .getDrawable(R.drawable.jiemo_loading_animation);
//        mJiemoImage.setImageDrawable(mAnimationDrawable);
        mJiemoText = (TextView) findViewById(R.id.text);

    }

    public void startLoadAnimation() {
        loadingText = texts.get(random.nextInt(maxIndex));
        mJiemoText.setText(loadingText);
        if (mAnimationDrawable != null) {
            post(new Runnable() {

                @Override
                public void run() {
                    mAnimationDrawable.start();
                }
            });

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //            mLoadingWuyaAnimationView.stopLoadAnimation();
            setAlpha(1f);
        }
    }

    public void continueLoadingAnimation() {
        if(TextUtils.isEmpty(loadingText)){
            loadingText = texts.get(random.nextInt(maxIndex));
            mJiemoText.setText(loadingText);
        }
        if (mAnimationDrawable != null) {
            post(new Runnable() {

                @Override
                public void run() {
                    mAnimationDrawable.start();
                }
            });

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //            mLoadingWuyaAnimationView.stopLoadAnimation();
            setAlpha(1f);
        }
    }

    public void stopLoadAnimation() {

        if (mAnimationDrawable != null) {
            mAnimationDrawable.stop();
        }
        loadingText = "";

    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility != VISIBLE) {
            stopLoadAnimation();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void setAlpha(float alpha) {
        super.setAlpha(alpha);
    }

}
