package com.lpan.study.utils;

import com.lpan.study.model.ImageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lpan on 2017/11/14.
 */

public class RxjavaTestUtils {

    public static void start() {
//        base();

//        changThread();

//        map();

//        flatMap();

//        zip();

//        backPressure();

//        filter();

        sample();
    }


    private static void base() {
        Observable<List<ImageInfo>> observable = Observable.create(new ObservableOnSubscribe<List<ImageInfo>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ImageInfo>> e) throws Exception {
                long start = System.currentTimeMillis();
                List<ImageInfo> imageInfos = ScanUtils.scanImages();
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "subscribe--------cost=" + (System.currentTimeMillis() - start) + "  thread name:" + Thread.currentThread().getName());
                }
                e.onNext(imageInfos);
            }
        });

        Observer<List<ImageInfo>> observer = new Observer<List<ImageInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "onSubscribe--------");
                }
            }

            @Override
            public void onNext(List<ImageInfo> list) {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "onNext--------thread name:" + Thread.currentThread().getName());
                }
                for (ImageInfo imageInfo : list) {
                    String url = imageInfo.getUrl();
                    if (Log.DEBUG) {
                        Log.d("RxjavaTestUtils", "onNext--------url=" + url);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "onError--------" + e.getMessage());
                }
            }

            @Override
            public void onComplete() {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "onComplete--------");
                }
            }
        };
        observable.subscribe(observer);
    }

    private static void changThread() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "subscribe--------first " + " thread name:" + Thread.currentThread().getName());
                }
                e.onNext("first");
                e.onNext("second");

            }
        }).subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (Log.DEBUG) {
                            Log.d("RxjavaTestUtils", "accept--------1" + s + "thread name:" + Thread.currentThread().getName());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (Log.DEBUG) {
                            Log.d("RxjavaTestUtils", "accept--------2" + s + "thread name:" + Thread.currentThread().getName());
                        }
                    }
                })
                .subscribe();
    }

    private static void map() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("100");
                e.onNext("200");
                e.onNext("三百");

            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(@NonNull String s) throws Exception {

                return Integer.valueOf(s);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "onSubscribe--------");
                }
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "onNext--------" + integer);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "onError--------" + e.getMessage());
                }
            }

            @Override
            public void onComplete() {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "onComplete--------");
                }
            }
        });
    }


    private static void flatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "apply--------" + integer);
                }
                final List<String> list = new ArrayList<String>();
                for (int i = 0; i < 3; i++) {
                    list.add(" value = " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "accept--------s=" + s);
                }
            }
        });
    }

    private static void registerAndLogin() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("register");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //如果注册失败

                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull String s) throws Exception {
                        //注册成功 发送登录请求

                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    private static void zip() {
        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.d("RxjavaTestUtils", "subscribe--------1");
                e.onNext(1);


                Log.d("RxjavaTestUtils", "subscribe--------2");
                e.onNext(2);


                Log.d("RxjavaTestUtils", "subscribe--------3");
                e.onNext(3);


                Log.d("RxjavaTestUtils", "subscribe--------4");
                e.onNext(4);


                Log.d("RxjavaTestUtils", "subscribe--------onComplete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                Log.d("RxjavaTestUtils", "subscribe--------a");
                e.onNext("a");


                Log.d("RxjavaTestUtils", "subscribe--------b");
                e.onNext("b");


                Log.d("RxjavaTestUtils", "subscribe--------c");
                e.onNext("c");


                Log.d("RxjavaTestUtils", "subscribe--------onComplete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());

        Observable.zip(integerObservable, stringObservable, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "onSubscribe--------");
                }
            }

            @Override
            public void onNext(@NonNull String s) {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "onNext--------s=" + s);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "onError--------");
                }
            }

            @Override
            public void onComplete() {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "onComplete--------");
                }
            }
        });

    }

    private static void backPressure() {
        //zip
//        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//                for (int i = 0; ; i++) {
//                    e.onNext(i);
//                }
//            }
//        }).subscribeOn(Schedulers.io());
//
//        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
//                e.onNext("a");
//            }
//        }).subscribeOn(Schedulers.io());
//
//        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
//            @Override
//            public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
//                return integer + s;
//            }
//
//        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                if (Log.DEBUG) {
//                    Log.d("RxjavaTestUtils", "accept--------");
//                }
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                if (Log.DEBUG) {
//                    Log.d("RxjavaTestUtils", "accept--------" + throwable);
//                }
//            }
//        });


//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
//                for (int j = 0; ; j++) {
//                    e.onNext(j + "");
//                }
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Thread.sleep(1000);
//                        if (Log.DEBUG) {
//                            Log.d("RxjavaTestUtils", "accept--------s=" + s);
//                        }
//                    }
//                });
    }

    private static void filter() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 10000; i++) {
                    e.onNext(i);
                }
            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(@NonNull Integer integer) throws Exception {
                return integer % 100 == 0;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                if (Log.DEBUG) {
                    Log.d("RxjavaTestUtils", "accept--------i=" + integer);
                }
            }
        });
    }

    private static void sample() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 10000; i++) {
                    e.onNext(i);
                }
            }
        }).sample(5, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        if (Log.DEBUG) {
                            Log.d("RxjavaTestUtils", "accept--------" + integer);
                        }
                    }
                });
    }
}
