package com.lpan.study.fragment.customviewtest;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.utils.BitmapUtils;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.ViewUtils;
import com.lpan.study.view.MatrixImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liaopan on 2017/12/14 10:53.
 */

public class MatrixFragment extends ButterKnifeFragment implements View.OnClickListener {

    @BindView(R.id.text1)
    TextView mTranslate;

    @BindView(R.id.text2)
    TextView mTranslate2;

    @BindView(R.id.image1)
    MatrixImageView mImage1;

    @BindView(R.id.maskView)
    View mMask;


    Bitmap mSrcBitmap;

    private int width;

    private int height;

    private Matrix matrix;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_matrix;
    }

    @Override
    protected void initData() {
        super.initData();
        mSrcBitmap = BitmapUtils.readBitmapById(AppContext.getContext(), R.drawable.battle);
        width = mSrcBitmap.getWidth();
        height = mSrcBitmap.getHeight();
        if (Log.DEBUG) {
            Log.d("MatrixFragment", "initData--------width=" + width + "  height=" + height);
        }
        mImage1.setTouchable(true);
        mImage1.getLayoutParams().width = (int) (ViewUtils.getScreenWidth(AppContext.getContext()));
        mImage1.getLayoutParams().height = (int) (ViewUtils.getScreenWidth(AppContext.getContext()));

        mMask.getLayoutParams().width = (int) (ViewUtils.getScreenWidth(AppContext.getContext()));
        mMask.getLayoutParams().height = (int) (ViewUtils.getScreenWidth(AppContext.getContext()));

        mImage1.setBitmap(mSrcBitmap, ViewUtils.getScreenWidth(AppContext.getContext()), ViewUtils.getScreenWidth(AppContext.getContext()));
        matrix = new Matrix();
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.image1})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                resetMatrix();
                matrix.setTranslate(100, height);
                mImage1.setMatrix(matrix);

                getWidthHeight(mSrcBitmap, matrix);
                break;

            case R.id.text2:
                resetMatrix();
                matrix.setScale(1.5f, 1.5f, width / 2, height / 2);
                mImage1.setMatrix(matrix);
                getWidthHeight(mSrcBitmap, matrix);

                break;
            case R.id.text3:
                resetMatrix();
                matrix.setRotate(45);
                mImage1.setMatrix(matrix);
                getWidthHeight(mSrcBitmap, matrix);

                break;

            case R.id.image1:
//                resetMatrix();
//                mImage1.setMatrix(matrix);
//                getWidthHeight(mSrcBitmap,matrix);

                break;
        }
    }

    private void resetMatrix() {
        matrix.reset();
    }

    private void getWidthHeight(Bitmap bitmap, Matrix matrix) {
        float[] currentWidthAndHeight = mImage1.getPreviewWidthHeight(bitmap, matrix);
        if (Log.DEBUG) {
            Log.d("MatrixFragment", "getWidthHeight--------width=" + currentWidthAndHeight[0] + "  height=" + currentWidthAndHeight[1]);
        }
    }
}
