package com.sen.study_android.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StatsLog;
import android.view.View;
import com.sen.study_android.R;
import com.sen.study_android.utils.Slog;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public class ErrorHandlerActivity extends AppCompatActivity {

    private String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_handler);
    }


    /**
     * 在Error发射前插入操作
     *
     * @param view
     */
    public void doOnError(View view) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("do on error 1");
                emitter.onNext("do on error 2");
                throw new RuntimeException("doOnError");
            }
        }).doOnError(e -> Slog.i(tag, e.getMessage()))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Slog.i(tag, "onNext-->" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Slog.i(tag, "onError-->" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Slog.i(tag, "onComplete-->");
                    }
                });
    }


    /**
     * 将error转发到onComplete9
     *
     * @param view
     */
    public void onErrorComplete(View view) {

        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                Slog.i(tag, "  onErrorComplete throw exception ");
                throw new RuntimeException(" throw onErrorComplete exception");
            }
        }).onErrorComplete().subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Slog.i(tag, "onErrorComplete onSubscribe");
            }

            @Override
            public void onComplete() {
                Slog.i(tag, "onErrorComplete onComplete");
            }

            @Override
            public void onError(Throwable e) {
                Slog.i(tag, "onErrorComplete onError-->" + e.getMessage());

            }
        });
    }

    /**
     * 遇到错误时发射下一个Observable的数据
     *
     * @param view
     */

    public void onErrorResumeNext(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                if (true) {
                    throw new RuntimeException("onErrorResumeNext");
                }
                emitter.onNext(3);
            }
        })
                .onErrorResumeNext(Observable.just(0, 10))
                .subscribe(i -> Slog.i(tag, "onErrorResumeNext-->" + i));
    }


    /**
     * 遇到错误时发射一个特殊的数据项并正常停止
     *
     * @param view
     */
    public void onErrorReturn(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                emitter.onNext(1);
                emitter.onNext(2);
                if (true) {
                    throw new RuntimeException("onErrorReturn occur");
                }
                emitter.onNext(3);

            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                return 0;
            }
        })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Slog.i(tag, "onErrorReturn   onNext-->" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Slog.i(tag, "onErrorReturn   onError-->" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Slog.i(tag, "onErrorReturn   onComplete-->");
                    }
                });

    }

    /**
     * 当发生错误的时候，会发射一个特定的项并正常结束
     *
     * @param view
     */
    public void onErrorReturnItem(View view) {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Thread.sleep(10);
                emitter.onNext(1);
                emitter.onNext(2);
                if (true)
                    throw new IllegalArgumentException("onErrorReturnItem");
                emitter.onNext(3);
            }
        }, BackpressureStrategy.BUFFER)
                .onErrorReturnItem(5)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Slog.i(tag, " onSubscribe--");
                        s.request(55);

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Slog.i(tag, "onNext--" + integer);

                    }

                    @Override
                    public void onError(Throwable t) {
                        Slog.i(tag, "onErrorReturnItem--" + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Slog.i(tag, "onComplete--");
                    }
                });
    }


    /**
     * 当发生错误时重复注册Observable
     *
     * @param view
     */
    public void retry(View view) {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            private int count = 0;

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);

                if (count < 2) {
                    count++;
                    throw new RuntimeException("retry exception");
                }

            }
        }).retry(4)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Slog.i(tag, "retry onNext--" + integer);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Slog.i(tag, "retry onError--" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Slog.i(tag, "retry onComplete--");
                    }
                });
    }

    /**
     * 当错误发生时一直注册当前观察者，知道条件为true
     *
     * @param view
     */
    public void retryUntil(View view) {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                throw new RuntimeException("retryUntil  occur");

            }
        }).retryUntil(new BooleanSupplier() {

            private int count = 0;

            @Override
            public boolean getAsBoolean() throws Exception {
                return count++ > 1;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Slog.i(tag, "retryUntil onNext--" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Slog.i(tag, "retryUntil onError--" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Slog.i(tag, "retryUntil onComplete--");

            }
        });
    }


    /**
     * 当错误发生后和retryWhen产生事件时，重新订阅事件
     * @param view
     */
    public void retryWhen(View view) {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                Slog.i(tag,"retryWhen-->  subscribe");
                emitter.onNext(1);
                Thread.sleep(1200);
                emitter.onNext(2);
                Thread.sleep(1200);
                if (true) {
                    throw new RuntimeException("retryWhen  occur");
                }
                emitter.onNext(3);

            }
        }).subscribeOn(Schedulers.newThread()).retryWhen(new Function<Observable<Throwable>, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(Observable<Throwable> throwableObservable) throws Exception {

                Slog.i(tag, "retryWhen --" + throwableObservable.toString());
                Observable<Long> observable= Observable.interval(1,TimeUnit.SECONDS);
                observable.subscribe(s-> Slog.i(tag,"retryWhen handle error "+s));
                return observable;
            }
        }) .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Slog.i(tag, "retryWhen onSubscribe--" + d.toString());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Slog.i(tag, "retryWhen onNext--" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Slog.i(tag, "retryWhen onError--" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Slog.i(tag, "retryWhen onComplete--");

                    }
                });
    }
}
