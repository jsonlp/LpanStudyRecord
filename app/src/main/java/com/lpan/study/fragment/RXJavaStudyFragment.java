package com.lpan.study.fragment;

import android.view.View;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.feature.RxjavaFeature;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.http.RetrofitService;
import com.lpan.study.model.GirlData;
import com.lpan.study.model.Meta;
import com.lpan.study.model.UserInfo;
import com.lpan.study.utils.Log;


import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lpan on 2018/5/24.
 */

public class RXJavaStudyFragment extends ButterKnifeFragment implements RxjavaFeature {


    @BindView(R.id.create_bt) TextView mCreateBt;
    @BindView(R.id.just_bt) TextView mJustBt;
    @BindView(R.id.from_array_bt) TextView mFromArrayBt;
    @BindView(R.id.fromIterable_bt) TextView mFromIterableBt;
    @BindView(R.id.never_bt) TextView mNeverBt;
    @BindView(R.id.empty_bt) TextView mEmptyBt;
    @BindView(R.id.error_bt) TextView mErrorBt;
    @BindView(R.id.defer_bt) TextView mDeferBt;
    @BindView(R.id.timer_bt) TextView mTimerBt;
    @BindView(R.id.interval_bt) TextView mIntervalBt;
    @BindView(R.id.interval_range_bt) TextView mIntervalRangeBt;
    @BindView(R.id.range_bt) TextView mRangeBt;
    @BindView(R.id.range_long_bt) TextView mRangeLongBt;
    @BindView(R.id.desc) TextView mDesc;

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_rx_java;
    }

    @OnClick({R.id.create_bt, R.id.just_bt, R.id.from_array_bt, R.id.fromIterable_bt,
            R.id.never_bt, R.id.empty_bt, R.id.error_bt,
            R.id.defer_bt, R.id.timer_bt, R.id.interval_bt,
            R.id.interval_range_bt, R.id.range_bt, R.id.range_long_bt,
            R.id.map_bt, R.id.flat_map_bt, R.id.concat_map_bt,

            R.id.desc})
    public void onViewClicked(View view) {
        String message = "";
        switch (view.getId()) {
            case R.id.create_bt:
                message = create(new StringBuilder());
                break;
            case R.id.just_bt:
                message = just(new StringBuilder());

                break;
            case R.id.from_array_bt:
                message = fromArray(new StringBuilder());

                break;
            case R.id.fromIterable_bt:
                message = fromIterable(new StringBuilder());

                break;
            case R.id.never_bt:
                message = never(new StringBuilder());

                break;
            case R.id.empty_bt:
                message = empty(new StringBuilder());

                break;
            case R.id.error_bt:
                message = error(new StringBuilder());

                break;
            case R.id.defer_bt:

                break;
            case R.id.timer_bt:
                message = timer(new StringBuilder());

                break;
            case R.id.interval_bt:
                message = interval(new StringBuilder());

                break;
            case R.id.interval_range_bt:
                message = intervalRange(new StringBuilder());

                break;
            case R.id.range_bt:
                message = range(new StringBuilder());

                break;
            case R.id.range_long_bt:
                break;

            case R.id.map_bt:
                fetchGirl();
                break;

            case R.id.flat_map_bt:
                flatMap();
                break;

            case R.id.concat_map_bt:
                break;
            case R.id.desc:
                mDesc.setText("");
                break;
        }
        mDesc.setText(message);
    }

    /**
     * 注册后自动登录
     */
    private void flatMap() {
        Observable<UserInfo> register = Observable.create(new ObservableOnSubscribe<UserInfo>() {
            @Override
            public void subscribe(ObservableEmitter<UserInfo> e) throws Exception {
                UserInfo userInfo = new UserInfo();
                userInfo.setName("张三");
                userInfo.setId("1223");
                e.onNext(userInfo);
            }
        });
        Observable<Meta> login = Observable.create(new ObservableOnSubscribe<Meta>() {
            @Override
            public void subscribe(ObservableEmitter<Meta> e) throws Exception {
                Meta meta = new Meta();
                meta.setCode(200);
                meta.setDesc("登陆成功");
                e.onNext(meta);
            }
        });

        register.
                flatMap(new Function<UserInfo, ObservableSource<Meta>>() {
                    @Override
                    public ObservableSource<Meta> apply(UserInfo userInfo) throws Exception {
                        if (userInfo != null) {
                            if (Log.DEBUG) {
                                Log.d("RXJavaStudyFragment", "apply--------注册成功");
                            }
                        }
                        return login;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Meta>() {
                    @Override
                    public void accept(Meta meta) throws Exception {
                        if (Log.DEBUG) {
                            Log.d("RXJavaStudyFragment", "accept--------" + meta.getDesc());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (Log.DEBUG) {
                            Log.d("RXJavaStudyFragment", "accept--------" + throwable.getMessage());
                        }
                    }
                });


    }

    private void fetchGirl() {
        RetrofitService.getGirlService().getBeauties(10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GirlData>() {
                    @Override
                    public void accept(GirlData girlData) throws Exception {
                        if (Log.DEBUG) {
                            Log.d("RXJavaStudyFragment", "accept--------" + girlData.toString());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (Log.DEBUG) {
                            Log.d("RXJavaStudyFragment", "accept--------" + throwable.getMessage());
                        }
                    }
                });
    }
}
