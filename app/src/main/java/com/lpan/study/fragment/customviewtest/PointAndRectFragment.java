package com.lpan.study.fragment.customviewtest;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.utils.BitmapUtils;
import com.test.lpanstudyrecord.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lpan on 2017/10/18.
 */

public class PointAndRectFragment extends ButterKnifeFragment implements View.OnClickListener {
    @BindView(R.id.text1)
    TextView mPoint1Tv1;

    @BindView(R.id.text2)
    TextView mPoint1Tv2;

    @BindView(R.id.text3)
    TextView mPoint1Tv3;

    @BindView(R.id.text4)
    TextView mPoint1Tv4;

    @BindView(R.id.text5)
    TextView mPoint1Tv5;

    @BindView(R.id.text6)
    TextView mPoint1Tv6;

    @BindView(R.id.text7)
    TextView mPoint1Tv7;

    @BindView(R.id.text8)
    TextView mPoint1Tv8;

    @BindView(R.id.text9)
    TextView mPoint1Tv9;

    @BindView(R.id.text10)
    TextView mPoint1Tv10;

    @BindView(R.id.text11)
    TextView mPoint1Tv11;

    @BindView(R.id.text12)
    TextView mPoint1Tv12;

    @BindView(R.id.text13)
    TextView mPoint1Tv13;

    @BindView(R.id.text14)
    TextView mPoint1Tv14;

    @BindView(R.id.text15)
    TextView mPoint1Tv15;

    @BindView(R.id.text16)
    TextView mPoint1Tv16;

    @BindView(R.id.text17)
    TextView mPoint1Tv17;

    @BindView(R.id.image1)
    ImageView mRectIv1;


    private Point point = new Point(1, 1);
    private PointF pointf = new PointF(1, 1);

    private Rect rect = new Rect(1, 1, 4, 3);
    private Rect empty = new Rect(1, 1, 1, 1);

    private Rect rect1 = new Rect(0, 0, 4, 4);
    private Rect rect2 = new Rect(2, 2, 6, 6);

    private RectF rectF = new RectF(12.4f, 20.8f, 100.3f, 202.9f);


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_point_and_rect;
    }

    @Override
    protected boolean withActionBar() {
        return true;
    }

    @Override
    protected String getActionBarTitle() {
        return "Point and Rect class";
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3,
            R.id.text4, R.id.text5, R.id.text6,
            R.id.text7, R.id.text8, R.id.text9,
            R.id.text10, R.id.text11, R.id.text12,
            R.id.text13, R.id.text14, R.id.text15,
            R.id.text16, R.id.text17})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                point.negate();
                mPoint1Tv1.setText("x=" + point.x + "  y=" + point.y);
                break;
            case R.id.text2:
                point.offset(2, 3);
                mPoint1Tv2.setText("x=" + point.x + "  y=" + point.y);
                break;

            case R.id.text3:
                float length = pointf.length();
                mPoint1Tv3.setText("length=" + length);
                break;

            case R.id.text4:
                mPoint1Tv4.setText(empty.isEmpty() + "");
                break;

            case R.id.text5:
                mPoint1Tv5.setText("width=" + rect.width() + "  height=" + rect.height());
                break;

            case R.id.text6:
                mPoint1Tv6.setText("exactCenterX=" + rect.exactCenterX() + "  exactCenterY=" + rect.exactCenterY());

                break;

            case R.id.text7:
                rect.setEmpty();
                mPoint1Tv7.setText(rect.toString());

                resetRect();
                break;

            case R.id.text8:
                rect.set(10, 15, 30, 25);
                mPoint1Tv8.setText(rect.toString());
                resetRect();

                break;

            case R.id.text9:
                rect.offset(1, 2);
                mPoint1Tv9.setText(rect.toString());
                resetRect();

                break;
            case R.id.text10:
                rect.offsetTo(1, 2);
                mPoint1Tv10.setText(rect.toString());
                resetRect();

                break;

            case R.id.text11:
                rect.inset(1, 1);
                mPoint1Tv11.setText(rect.toString());
                resetRect();

                break;

            case R.id.text12:

                mPoint1Tv12.setText(rect.contains(2, 2) + "");
                resetRect();

                break;

            case R.id.text13:
                mPoint1Tv13.setText(rect.contains(1, 2, 1, 2) + "");
                resetRect();

                break;

            case R.id.text14:
                boolean interect = rect1.intersect(rect2);
                mPoint1Tv14.setText("interect=" + interect + "  rect1=" + rect1.toString());
                resetRect();

                break;

            case R.id.text15:
                rect1.union(rect2);
                mPoint1Tv15.setText("  rect1=" + rect1.toString());
                resetRect();

                break;

            case R.id.text16:

                rectF = new RectF(rect);
                mPoint1Tv16.setText("  rectF=" + rectF.toString());
                resetRect();

                break;

            case R.id.text17:
                rectF.round(rect);
                mPoint1Tv17.setText("  rect=" + rect.toString());
                resetRect();

                break;

        }
    }

    private void resetRect() {
        rect.set(new Rect(1, 1, 4, 3));
        rect1 = new Rect(0, 0, 4, 4);
        rect2 = new Rect(2, 2, 6, 6);
        rectF.set(new RectF(12.4f, 20.8f, 100.3f, 202.9f));
    }

    @Override
    protected void initData() {
        super.initData();
        Bitmap bitmap = BitmapUtils.readBitmapById(getActivity(), R.drawable.rect_01);
        Bitmap bitmap1 = BitmapUtils.scaleBitmapFullScreenWidth(bitmap);
        mRectIv1.setImageBitmap(bitmap1);

        //1111
    }
}
