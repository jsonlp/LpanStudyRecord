package com.lpan.study.fragment;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lpan.R;
import com.lpan.study.fragment.base.BaseActionbarFragment;
import com.lpan.study.view.actionbar.ActionbarConfig;

import java.util.Hashtable;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lpan on 2018/12/25.
 */
public class QRCodeCreateFragment extends BaseActionbarFragment {

    @BindView(R.id.edit1) EditText mEdit1;
    @BindView(R.id.button1) Button mButton1;
    @BindView(R.id.qr_code_image) ImageView mQrCodeImage;

    @Override
    protected ActionbarConfig getActionbarConfig() {
        return getDefaultActionbar("二维码");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_qr_code_create;
    }

    @OnClick({R.id.button1, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                String content = mEdit1.getText().toString();
                Bitmap bitmap = creatQRCode(content, 600, 600, 0xFFFFFFFF, 0xFF000000);
                if (bitmap != null) {
                    mQrCodeImage.setImageBitmap(bitmap);
                }
                break;

            case R.id.button2:

                break;
        }

    }

    private Bitmap creatQRCode(CharSequence content, int QR_WIDTH, int QR_HEIGHT, int backgroundColor, int codeColor) {
        Bitmap bitmap = null;
        try {
            // 判断URL合法性
            if (content == null || "".equals(content) || content.length() < 1) {
                return null;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(content + "", BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = codeColor;
                    } else {
                        pixels[y * QR_WIDTH + x] = backgroundColor;
                    }
                }
            }
            // 生成二维码图片的格式，使用ARGB_8888
            bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
