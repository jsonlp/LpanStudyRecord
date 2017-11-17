package com.lpan.study.presenter.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Environment;

import com.lpan.study.context.AppContext;
import com.lpan.study.contract.AddlogoContract;
import com.lpan.study.utils.BitmapUtils;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.Toaster;
import com.lpan.study.utils.ViewUtils;

import java.io.File;
import java.io.FileOutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lpan on 2017/11/15.
 */

public class AddlogoPresenter implements AddlogoContract.Presenter {

    private AddlogoContract.View mView;

    public AddlogoPresenter(AddlogoContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void addLogo(final int image, final int logo) {

        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Bitmap> e) throws Exception {
                Bitmap imageBm = BitmapUtils.readBitmapById(AppContext.getContext(), image);
                Bitmap logoBm = BitmapUtils.readBitmapById(AppContext.getContext(), logo);

                Bitmap b1 = BitmapUtils.centerSquareScaleBitmap(imageBm, ViewUtils.dp2px(AppContext.getContext(), 40));
                Bitmap b2 = BitmapUtils.resizeImage(logoBm, ViewUtils.dp2px(AppContext.getContext(), 14), ViewUtils.dp2px(AppContext.getContext(), 10));

                Bitmap bitmap3 = Bitmap.createBitmap(b1.getWidth(), b1.getHeight(), b1.getConfig());
                Canvas canvas = new Canvas(bitmap3);
                canvas.drawBitmap(b1, new Matrix(), null);
                canvas.drawBitmap(b2, ViewUtils.dp2px(AppContext.getContext(), 26), ViewUtils.dp2px(AppContext.getContext(), 30), null);  //120、350为bitmap2写入点的x、y坐标
                //将合并后的bitmap3保存为png图片到本地
                FileOutputStream out = null;
                String path = "";
                path = getVideoPath() + File.separator + "image3.png";
                out = new FileOutputStream(path);
                bitmap3.compress(Bitmap.CompressFormat.PNG, 90, out);
                Toaster.toastShort(path);
                out.close();

                e.onNext(bitmap3);
            }
        })
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        mView.showImage(bitmap);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (Log.DEBUG) {
                            Log.d("AddlogoPresenter", "accept--------" + throwable);
                        }
                    }
                });
    }

    private String getVideoPath() {
        return getVideoFileDir().getAbsolutePath();
    }

    private File getVideoFileDir() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/" + "Jiemoapp" + "/");
        if (dir.exists()) {
            return dir;
        }
        return null;
    }
}
