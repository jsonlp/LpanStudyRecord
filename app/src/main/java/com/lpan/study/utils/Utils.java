package com.lpan.study.utils;

import android.content.Context;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lpan.study.context.AppContext;
import com.lpan.study.listener.MentionSpanClickListener;
import com.lpan.study.model.UserInfo;
import com.lpan.study.view.MentionClickableSpan;
import com.lpan.study.view.SpanClickMoveMethod;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lpan on 2017/2/8.
 */

public class Utils {

    public static final long ONE_KB = 1024;

    private static Pattern mMentionPattern = Pattern.compile("\\{@[a-z0-9\\-_]{22}\\}", Pattern.CASE_INSENSITIVE);

    public static final SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");

    /**
     * 转换毫秒数成“分、秒”，如“01:53”。若超过60分钟则显示“时、分、秒”，如“01:01:30
     *
     * @param
     */
    public static String converLongTimeToStr(long time) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;

        long hour = (time) / hh;
        long minute = (time - hour * hh) / mi;
        long second = (time - hour * hh - minute * mi) / ss;

        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;
        if (hour > 0) {
            return strHour + ":" + strMinute + ":" + strSecond;
        } else {
            return strMinute + ":" + strSecond;
        }
    }

    public static String formatTime(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String str = dateFormat.format(time);
        return str;
    }

    public static double formatSizeToKB(long size) {
        double d = (double) size / ONE_KB;
        return (double) Math.round(d * 100) / 100;
    }

    public static double formatSizeToMB(long size) {
        double d = (double) size / ONE_KB / ONE_KB;
        return (double) Math.round(d * 100) / 100;
    }

    public static double formatSizeToGB(long size) {
        double d = (double) size / ONE_KB / ONE_KB / ONE_KB;
        return (double) Math.round(d * 100) / 100;
    }

    public static CharSequence setMentionText(Context context, TextView textView, String content, List<UserInfo> list, boolean append, MentionSpanClickListener listener) {

        if (!TextUtils.isEmpty(content) && content.contains("\r")) {
            content = content.replace("\r", "\n");
        }

        if (TextUtils.isEmpty(content) || !content.contains("@") || CollectionUtils.isEmpty(list)) {


            SpannableString spannableString = new SpannableString(TextUtils.isEmpty(content) ? "" : content);
            if (!TextUtils.isEmpty(content)) {
//                EmojiconHandler.addEmojis(AppContext.getContext(), spannableString, (int) textView.getTextSize());
            }
            if (append) {
                textView.append(spannableString);
            } else {
                textView.setText(spannableString);
                textView.setMovementMethod(null);
            }
            return spannableString;
        }

        Matcher matcher = mMentionPattern.matcher(content);
        boolean find = false;
        UserInfo mentionUser = null;
        int end = 0;
        if (!append) {
            textView.setText(null);
        }

        while (matcher.find()) {
            find = true;
            String head = "";

            SpannableString heads = new SpannableString(content.substring(end, matcher.start()));
//            EmojiconHandler.addEmojis(AppContext.getContext(), heads, (int) textView.getTextSize());


            textView.append(heads);
            end += heads.length();

            mentionUser = getMentionUser(matcher.group(), list);
            if (mentionUser != null) {
                SpannableString part;
                part = new SpannableString("@" + mentionUser.getName() + " ");
                MentionClickableSpan span = new MentionClickableSpan(mentionUser, listener);
                part.setSpan(span, 0, part.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//                EmojiconHandler.addEmojis(AppContext.getContext(), part, (int) textView.getTextSize());
                textView.append(part);
            } else {
                SpannableString group = new SpannableString(matcher.group());
//                EmojiconHandler.addEmojis(AppContext.getContext(), group, (int) textView.getTextSize());
                textView.append(group);
            }

            end += matcher.group().length();
        }
        SpannableString ends = new SpannableString(content.substring(end));
//        EmojiconHandler.addEmojis(AppContext.getContext(), ends, (int) textView.getTextSize());
        textView.append(ends);

        if (Build.VERSION.SDK_INT > 10) {
            textView.setTextIsSelectable(find);
        }
        if (find) {
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
            textView.setMovementMethod(SpanClickMoveMethod.getInstance());
        } else if (!append) {
            textView.setMovementMethod(null);
        }

        return textView.getText();
    }

    private static UserInfo getMentionUser(String mentionId, List<UserInfo> list) {

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        for (UserInfo userInfo : list) {
            if (mentionId.contains(userInfo.getId())) {
                return userInfo;
            }
        }

        return null;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasJellyBeanMR2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasKitkat() {
        return Build.VERSION.SDK_INT >= 19/*Build.VERSION_CODES.KITKAT*/;
    }

    public static boolean hasIceCreamSandwich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
