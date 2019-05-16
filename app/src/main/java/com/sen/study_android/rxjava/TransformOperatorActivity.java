package com.sen.study_android.rxjava;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.sen.study_android.R;
import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.*;
import io.reactivex.observables.GroupedObservable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TransformOperatorActivity extends AppCompatActivity {
    private String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform_operator);
    }

    /**
     * buffer操作符缓存Observable发射的数据后转换为另外一个Observable发射这些数据的集合
     *
     * @param view
     */

    public void buffer(View view) {
        Observable.range(0, 10).buffer(4).subscribe(i -> Log.i(tag, " buffer --->" + i));
//        buffer --->[0, 1, 2, 3]
//       buffer --->[4, 5, 6, 7]
//       buffer --->[8, 9]

        Observable.range(0, 10).buffer(4, 2).subscribe(i -> Log.i(tag, " buffer 1--->" + i));


        Observable.range(0, 10).buffer(new ObservableSource<Integer>() {
            @Override
            public void subscribe(Observer<? super Integer> observer) {
                observer.onNext(1);
                observer.onNext(2);

            }
        }).subscribe(i -> Log.i(tag, " buffer 2--->" + i));


    }

    /**
     * flatMap将一个发射的数据Observable变换为多个Observables，然后将他们发射的数据合并后放进一个单独的Observale
     *
     * @param view
     */
    public void flatMap(View view) {

        Observable.just("1", "2", "3").flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(final String s) throws Exception {
                return new ObservableSource<String>() {
                    @Override
                    public void subscribe(Observer<? super String> observer) {
                        Log.i(tag, "flatMap 1 ObservableSource ");
                        observer.onNext("flatMap 1- -->" + s);
                        observer.onNext("flatMap 1 1- -->" + s);
                    }
                };
            }
        }).subscribe(s -> Log.i(tag, s));


        Observable.just(1, 2, 3).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just("flatMap 2 just-->" + integer);
            }
        }).subscribe(s -> Log.i(tag, s));



    }

    /**
     * flatMapCompleable 将Observable变换为多个 CompletableSource，最终只发射一个comlete或者error指令
     *
     * @param view
     */

    public void flatMapCompleable(View view) {

        Observable.just(1, 2, 3).flatMapCompletable(new Function<Integer, CompletableSource>() {
            @Override
            public CompletableSource apply(Integer integer) throws Exception {
                Log.i(tag, "flatMapCompleable 1 CompletableSource-->" + integer);
                return Completable.complete();
            }
        }).subscribe(new Action() {
            @Override
            public void run() throws Exception {
                Log.i(tag, "flatMapCompleable Action");
            }
        });


        Observable.just(1, 2, 3).flatMapCompletable(new Function<Integer, CompletableSource>() {
            @Override
            public CompletableSource apply(Integer integer) throws Exception {
                Log.i(tag, "flatMapCompleable 2 CompletableSource-->" + integer);
                return Completable.timer(integer, TimeUnit.SECONDS).doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(tag, "flatMapCompleable 2 doOnComplete-->" + integer);
                    }
                });
            }
        }).subscribe(new Action() {
            @Override
            public void run() throws Exception {
                Log.i(tag, "flatMapCompleable Action");
            }
        });
    }

    /**
     * flatMapIterable，将一个发射数据的Observable转换为发射多个发射迭代器数据的Observable
     *
     * @param view
     */
    public void flatMapIterable(View view) {

        Observable.just(1, 2, 3)
                .flatMapIterable(new Function<Integer, Iterable<String>>() {
                    @Override
                    public Iterable<String> apply(Integer integer) throws Exception {
                        return Arrays.asList("flatMapIterable-->" + integer + "  a", "flatMapIterable-->" + integer + " b");
                    }
                }).subscribe(it -> Log.i(tag, it));
    }

    public void flatMapMaybe(View view) {
        Observable.just(1, 2, 3).flatMapMaybe(new Function<Integer, MaybeSource<String>>() {
            @Override
            public MaybeSource<String> apply(Integer integer) throws Exception {
                return Maybe.just("flatMapMaybe  one  " + integer);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(tag, "flatMapMaybe  -->onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.i(tag, "flatMapMaybe  -->onNext-->" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(tag, "flatMapMaybe  -->onError-->" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(tag, "flatMapMaybe  -->onComplete-->");
            }
        });
    }

    /**
     * @param view
     */

    public void cast(View view) {
        Observable.just(1, 2, 3, 4.3f)
                .filter(new Predicate<Number>() {
                    @Override
                    public boolean test(Number number) throws Exception {
                        return Integer.class.isInstance(number);
                    }
                })
                .cast(Float.class)
                .subscribe(f -> Log.i(tag, "cast  ---int to float-->" + f));
    }

    /**
     * concatMap按次序合并Observable发射的数据
     *
     * @param view
     */
    public void concatMap(View view) {
        Observable.just(1, 2, 3)
                .concatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just("concatMap-->" + integer);
                    }
                }).subscribe(r -> Log.i(tag, r));


        Observable<Integer> sender = Observable.just(1, 2, 3).concatMap(new Function<Integer, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(@NonNull Integer integer) throws Exception {
                Log.i(tag, "concatMap--->  subscribe  2  -->" + integer);
                // 将多个数据合并

                List<Integer> list = new ArrayList<Integer>();

                list.add(integer);

                return new ObservableSource<Integer>() {
                    @Override
                    public void subscribe(Observer<? super Integer> observer) {
                        observer.onNext(integer);
                    }
                };    //使用fromIterable()，遍历集合
            }
        });


        // 用Consumer接收数据
        Consumer receiver = new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer o) throws Exception {

                // 接收的数据是有序的
                Log.i("zzz", "o:  " + o);

                // 1
                // 2
                // 3

            }
        };

        sender.subscribe(receiver);


    }

    /**
     * 将原始Observable发射的数据进行分组
     * @param view
     */
    public void groupBy(View view) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return ((integer % 2 == 0) ? "group one" : "group two");
                    }
                }).subscribe(new Observer<GroupedObservable<String, Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GroupedObservable<String, Integer> stringIntegerGroupedObservable) {


             Log.i(tag,"key="+stringIntegerGroupedObservable.getKey()+"\tvalue="+stringIntegerGroupedObservable.toList());

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    /**
     * 将数据转换
     * @param view
     */
    public void map(View view) {
        Observable.just(1,2,3)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                            return integer*integer;
                    }
                }).subscribe(r->Log.i(tag,"map--->"+r));
    }

    /**
     * 对发射的数据进行扫描，将当前的结果和上一次的结果合并转换为新的数据
     * @param view
     */
    public void sacn(View view) {
        Observable.just(1,2,3,4)
                .scan(1, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer+integer2;
                    }
                }).subscribe(r->Log.i(tag,"sacn--->"+r));
    }

    /**
     * 将原始Observabl发射的数据转换为数据组再发送
     * @param view
     */
    public void window(View view) {
        Observable.range(2,12)
                .window(3)

                .subscribe(new Consumer<Observable<Integer>>() {
                    @Override
                    public void accept(Observable<Integer> integerObservable) throws Exception {

                        integerObservable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.i(tag,"window--->"+integer+"\t"+integerObservable.toString());
                            }
                        });
                    }
                });
    }
}
