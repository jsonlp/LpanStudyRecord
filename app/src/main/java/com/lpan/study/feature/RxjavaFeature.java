package com.lpan.study.feature;

import com.lpan.study.utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lpan on 2018/5/24.
 */

public interface RxjavaFeature {

    default Observer getBaseObserver(StringBuilder stringBuilder) {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                if (Log.DEBUG) {
                    Log.d("RxjavaFeature", "onSubscribe--------");
                }
                stringBuilder.append("onSubscribe");
                stringBuilder.append("\n");
            }

            @Override
            public void onNext(String s) {
                if (Log.DEBUG) {
                    Log.d("RxjavaFeature", "onNext--------" + s);
                }
                stringBuilder.append(s);
                stringBuilder.append("\n");
            }

            @Override
            public void onError(Throwable e) {
                if (Log.DEBUG) {
                    Log.d("RxjavaFeature", "onError--------");
                }
                stringBuilder.append("onError");
                stringBuilder.append("\n");
            }

            @Override
            public void onComplete() {
                if (Log.DEBUG) {
                    Log.d("RxjavaFeature", "onComplete--------");
                }
                stringBuilder.append("onComplete");
                stringBuilder.append("\n");
            }
        };
    }

    default String create(StringBuilder stringBuilder) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("create1");
                e.onNext("create2");
                e.onNext("create3");

            }
        }).subscribe(getBaseObserver(stringBuilder));
        return stringBuilder.toString();
    }

    default String just(StringBuilder stringBuilder) {
        Observable.just("just1", "just2", "just3")
                .subscribe(getBaseObserver(stringBuilder));
        return stringBuilder.toString();
    }

    default String fromArray(StringBuilder stringBuilder) {
        String[] strings = new String[]{"fromArray1", "fromArray2", "fromArray3"};
        Observable.fromArray(strings)
                .subscribe(getBaseObserver(stringBuilder));
        return stringBuilder.toString();
    }

    default String fromIterable(StringBuilder stringBuilder) {
        List<String> list = new ArrayList<>();
        list.add("fromIterable1");
        list.add("fromIterable2");
        list.add("fromIterable3");

        Observable.fromIterable(list)
                .subscribe(getBaseObserver(stringBuilder));
        return stringBuilder.toString();
    }

    default String never(StringBuilder stringBuilder) {
        Observable.never().subscribe(getBaseObserver(stringBuilder));
        return stringBuilder.toString();
    }

    default String error(StringBuilder stringBuilder) {
        Observable.error(new RuntimeException()).subscribe(getBaseObserver(stringBuilder));
        return stringBuilder.toString();
    }

    default String empty(StringBuilder stringBuilder) {
        Observable.empty().subscribe(getBaseObserver(stringBuilder));
        return stringBuilder.toString();
    }


    default String timer(StringBuilder stringBuilder) {
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onSubscribe--------");
                        }
                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onNext--------");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onError--------");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onComplete--------");
                        }
                    }
                });
        return stringBuilder.toString();
    }

    default String interval(StringBuilder stringBuilder) {
        final Disposable[] disposable = {null};
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable[0] = d;
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onSubscribe--------");
                        }
                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onNext--------" + aLong);
                        }
                        if (aLong > 10) {
                            disposable[0].dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onError--------");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onComplete--------");
                        }
                    }
                });
        return stringBuilder.toString();
    }

    default String intervalRange(StringBuilder stringBuilder) {
        Observable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onSubscribe--------");
                        }
                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onNext--------" + aLong);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onError--------");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onComplete--------");
                        }
                    }
                });
        return stringBuilder.toString();
    }

    default String range(StringBuilder stringBuilder) {
        Observable.range(0, 10)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onSubscribe--------");
                        }
                    }

                    @Override
                    public void onNext(Integer integer) {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onNext--------" + integer);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onError--------");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (Log.DEBUG) {
                            Log.d("RxjavaFeature", "onComplete--------");
                        }
                    }
                });
        return stringBuilder.toString();
    }

}
