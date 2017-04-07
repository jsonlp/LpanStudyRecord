package com.lpan.study.view;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.lpan.study.context.AppContext;
import com.lpan.study.listener.MentionSpanClickListener;
import com.lpan.study.model.UserInfo;
import com.test.lpanstudyrecord.R;


/**
 * Created by longbeach1 on 16/4/27.
 */
public class MentionClickableSpan extends ClickableSpan {

    private UserInfo mUser;

    private MentionSpanClickListener listener;

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setFakeBoldText(false);
        ds.setColor(AppContext.getContext().getResources().getColor(R.color.red));
    }

    public MentionClickableSpan(UserInfo mUser, MentionSpanClickListener listener) {
        this.mUser = mUser;
        this.listener = listener;
    }

    @Override
    public void onClick(View widget) {
        if (listener != null) {
            listener.onMentionNameClick(mUser);
        }
    }
}
