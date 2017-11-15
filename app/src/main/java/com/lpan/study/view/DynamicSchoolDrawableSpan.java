package com.lpan.study.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.DynamicDrawableSpan;

import com.lpan.study.context.AppContext;
import com.lpan.R;

/**
 * Created by lpan on 2017/8/31.
 */

public class DynamicSchoolDrawableSpan extends DynamicDrawableSpan {

    private String mSchoolName;

    private String mTag;

    public DynamicSchoolDrawableSpan(String schoolName,String tag){
        this.mSchoolName = schoolName;
        this.mTag = tag;

    }
    @Override
    public Drawable getDrawable() {

        Bitmap bitmap = getBitmap();

        BitmapDrawable drawable = new BitmapDrawable(AppContext.getContext().getResources(),bitmap);
        drawable.setBounds(0,0,bitmap.getWidth(),bitmap.getHeight());
        return drawable;
    }

    private Bitmap getBitmap() {

        SpannableString string = new SpannableString(mSchoolName+mTag);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(AppContext.getContext().getResources().getColor(R.color.black));
        paint.setTextSize(40);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

        float width = paint.measureText(mSchoolName+mTag);
        Bitmap bitmap = Bitmap.createBitmap((int) width, (int) (fontMetrics.descent - fontMetrics.ascent), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(string,0,string.length(),0,-fontMetrics.ascent,paint);

        return bitmap;
    }
}
