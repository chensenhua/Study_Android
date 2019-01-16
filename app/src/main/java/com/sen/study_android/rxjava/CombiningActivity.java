package com.sen.study_android.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.sen.study_android.R;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.concurrent.TimeUnit;

public class CombiningActivity extends AppCompatActivity {

    private String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combining);
    }


    /**
     * 在Observable发射的发射数据插入数据
     *
     * @param view
     */
    public void startWith(View view) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("startwith --->1");
                emitter.onNext("startwith --->2");
                emitter.onNext("startwith --->3");
                emitter.onNext("startwith --->4");
                emitter.onComplete();
            }
        }).startWith("tartwith --->insert")
                .subscribe(s -> Log.i(tag, s));
    }


    /**
     * 将两个Observable的数据合并在一起，并一个个发射，数据量等于两个Observable的发射的和
     *
     * @param view
     */
    public void merge(View view) {

        Observable.just(1, 2)
                .mergeWith(Observable.just(3, 4))
                .mergeWith(Observable.just(5, 6))
                .subscribe(i -> Log.i(tag, "merge-->" + i));
    }


    /**
     * 将两个将两个Observable的数据结合在一起并发送
     *
     * @param view
     */
    public void zip(View view) {
        Observable<Integer> observable = Observable.just(1, 2, 3);
        Observable<Integer> observable1 = Observable.just(4, 5, 6);
        observable.zipWith(observable1, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {
                return "zip---> (x,y)-->=(" + integer + "," + integer2 + ")";
            }
        }).subscribe(s -> Log.i(tag, s));

    }


    /**
     * 将n个Observable的最新发射的数据结合在一起后发射
     * <p>
     * * @param view
     */

    public void combinLatest(View view) {

        Observable<Long> one = Observable.intervalRange(1, 5, 0, 2, TimeUnit.SECONDS);
        Observable<Long> two = Observable.intervalRange(6, 5, 0, 1, TimeUnit.SECONDS);
        Observable.combineLatest(one, two, new BiFunction<Long, Long, String>() {
            @Override
            public String apply(Long aLong, Long aLong2) throws Exception {
                return "combinLatest---> (x,y)-->=(" + aLong + "," + aLong2 + ")";
            }
        }).subscribe(s -> Log.i(tag, s));


    }


    /**
     * 将多个Observable数据合并发送，如果有新的Observable发射数据，上一个Observable则永远停止发数
     *
     * @param view
     */
    public void switchOpertor(View view) {

        Observable<Long> one = Observable.intervalRange(1, 5, 0, 2, TimeUnit.SECONDS);
        Observable<Long> two = Observable.intervalRange(6, 5, 0, 1, TimeUnit.SECONDS);
        Observable.switchOnNext(one.map(new Function<Long, Observable<String>>() {
            @Override
            public Observable<String> apply(Long aLong) throws Exception {

                Log.i(tag, "switchOpertor  map --->" + aLong);
                return two.map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return "switchOpertor   --->" + aLong;
                    }
                });
            }
        })).subscribe(new Consumer<String>() {
            @Override
            public void accept(String integer) throws Exception {
                Log.i(tag, integer);
            }


        });

    }

    public void join(View view) {



        Observable<Long> ob =Observable.intervalRange(1,5,0,1,TimeUnit.SECONDS);

        Observable.just("hello")
                .join(ob,

                        new Function<String, Observable<Long>>() {
                            @Override
                            public Observable<Long> apply(String s) throws Exception {

                                Log.i(tag, "left-->"+s + "");
                                return Observable.intervalRange(1,5,0,1,TimeUnit.SECONDS);
                            }
                        },

                        new Function<Long, ObservableSource<Long>>() {
                            @Override
                            public ObservableSource<Long> apply(Long aLong) throws Exception {

                                Log.i(tag, "right-->"+aLong + "");
                                return Observable.timer(2000, TimeUnit.MILLISECONDS);
                            }
                        },




                        //结合上面发射的数据
                        (s, integer) ->  "left="+s + "\tright="+integer
                        )
        .subscribe(s->Log.i(tag,s))

        ;




    }
}
