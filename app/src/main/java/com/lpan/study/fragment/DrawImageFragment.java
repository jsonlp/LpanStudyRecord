package com.lpan.study.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.study.context.AppContext;
import com.lpan.study.utils.BitmapUtils;
import com.lpan.study.utils.FileUtils;
import com.lpan.study.utils.Toaster;
import com.lpan.study.utils.ViewUtils;
import com.test.lpanstudyrecord.R;

import java.io.File;

/**
 * Created by lpan on 2017/3/14.
 */

public class DrawImageFragment extends BaseFragment implements View.OnClickListener {

    private String leftName = "张三";

    private String rightName = "李四";

    private String leftSchool = "郑州大学";

    private String rightSchool = "广州大学";

    private String tip1 = "一样的兴趣，相似的轨迹";

    private String tip2 = "与你心有灵犀的同学都在芥末上等你";

    private static final int ONE_DP = ViewUtils.dp2px(AppContext.getContext(), 1);


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_draw_image;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);


        Bitmap leftBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girl1);
        Bitmap rightBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girl2);
        Bitmap bg = BitmapFactory.decodeResource(getResources(), R.mipmap.heart_eachother_bg);

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * @describe 可以将View的内容 保存bitmap
     * @author lpan
     * @time 2017/3/14 18:31
     */
    private Bitmap drawView(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    /**
     * @describe 绘制分享图片
     * @author lpan
     * @time 2017/3/14 18:32
     */
    private Bitmap saveShareBitmap(String mKeyword) {

        Bitmap shareBitmap = Bitmap.createBitmap(750, 1082, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(shareBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, 750, 1082, paint);


        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.jiemo_icon);
        Bitmap qrcode = BitmapFactory.decodeResource(getResources(), R.mipmap.download_qr_code);
        Rect iconRect = new Rect(40, 40, 110, 110);
        canvas.drawBitmap(icon, null, iconRect, null);

        paint.setColor(Color.rgb(51, 51, 51));
        paint.setTextSize(30);
        canvas.drawText("芥末校园", 130, 70 - paint.getFontMetricsInt().descent, paint);

        paint.setTextSize(26);
        paint.setColor(Color.parseColor("#999999"));
        canvas.drawText("找同学，上芥末", 130, 110 - paint.getFontMetricsInt().descent, paint);

        paint.setColor(Color.rgb(102, 102, 102));
        canvas.drawLine(40, 170, 286, 171, paint);
        canvas.drawLine(482, 170, 710, 171, paint);

        paint.setTextSize(34);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("寻人启事", 375, 190 - paint.getFontMetricsInt().descent, paint);

        paint.setColor(Color.rgb(51, 51, 51));
        paint.setTextSize(400 / mKeyword.length());
        paint.setFakeBoldText(true);
        while (paint.measureText(mKeyword) < 600 && paint.getTextSize() < 200) {
            paint.setTextSize(paint.getTextSize() + 4);
        }
        paint.setTextSize(paint.getTextSize() - 4);
        if (paint.getTextSize() > 200) {
            paint.setTextSize(200);
        }
        canvas.drawText(mKeyword, 375, 390 - paint.getFontMetricsInt().ascent, paint);


        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.rgb(51, 51, 51));
        textPaint.setTextSize(34);
        StaticLayout layout = new StaticLayout("天了噜！！！" + mKeyword + "，有好多童鞋在芥末里寻找你，请速速前来", textPaint, 550, Layout.Alignment.ALIGN_CENTER, 1, 0, true);
        canvas.translate(100, 430 - paint.getFontMetricsInt().ascent);
        layout.draw(canvas);

        canvas.translate(-100, -430 + paint.getFontMetricsInt().ascent);

        Rect rect = new Rect(608, 906, 708, 1006);
        canvas.drawBitmap(qrcode, null, rect, null);
        rect = new Rect(648, 946, 668, 966);
        canvas.drawBitmap(icon, null, rect, null);

        paint.setColor(Color.BLACK);
        paint.setTextSize(26);
        paint.setFakeBoldText(false);
        canvas.drawText("扫码下载", 658, 1042 - paint.getFontMetricsInt().descent, paint);
        return shareBitmap;

    }

    private Bitmap saveMatchBitmap(String leftName, String rightName, String leftSchool, String rightSchool, String tip1, String tip2, Bitmap leftBitmap, Bitmap rightBitmap, Bitmap bg) {
        Bitmap shareBitmap = Bitmap.createBitmap(1080, 1960, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(shareBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);

        //画背景
        Rect iconRect = new Rect(0, 0, 1080, 1960);
        canvas.drawBitmap(bg, null, iconRect, null);

        //画标题1
        paint.setTextSize(50);
        paint.setColor(Color.RED);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(tip1, 30 * ONE_DP, 60 * ONE_DP, paint);

        return shareBitmap;
    }
}
