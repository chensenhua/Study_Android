package com.sen.study_android.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.sen.study_android.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;


public class FilterOperatorActivity extends AppCompatActivity {
    private String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_operator);
    }

    public void debounce(View view) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("a");
                Thread.sleep(500);

                emitter.onNext("b");
                Thread.sleep(1500);

                emitter.onNext("c");
                Thread.sleep(500);

                emitter.onNext("e");
                Thread.sleep(1500);

                emitter.onNext("f");
                Thread.sleep(500);


                emitter.onNext("j");


            }
        })
                .subscribeOn(Schedulers.io())
                .debounce(1, TimeUnit.SECONDS)
                .subscribe(s -> Log.i(tag, "debounce-->" + s));
    }


    /**
     * 过滤操作符
     *
     * @param view
     */
    public void distinct(View view) {

        Observable.just(1, 2, 3, 3, 4)
                .distinct()
                .subscribe(i -> Log.i(tag, "distinct  1-->" + i));


        Observable.just(1, 2, 3, 2, 3, 4)
                .distinct(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        //过滤条件
                        return integer % 2;
                    }
                })
                .subscribe(i -> Log.i(tag, "distinct  2->" + i));

    }


    /**
     * 只过了和上一个数据相等的数据
     *
     * @param view
     */
    public void distinctUntilChange(View view) {

        Observable.just(1, 2, 2, 3, 1, 4, 4)
                .distinctUntilChanged()
                .subscribe(i -> Log.i(tag, "distinctUntilChange   ->" + i));
    }

    /**
     * 只发射第i个数据
     *
     * @param view
     */
    public void elementAt(View view) {
        Observable.just(1, 2, 3, 4, 5)
                .elementAt(2)
                .subscribe(i -> Log.i(tag, "elementAt   ->" + i));
    }

    /**
     * 按条件过滤数据
     *
     * @param view
     */
    public void filter(View view) {

        Observable.just(1, 2, 3, 4)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 2;
                    }
                }).subscribe(i -> Log.i(tag, "filter   ->" + i));
    }

    /**
     * 只发射第一个数据
     *
     * @param view
     */

    public void first(View view) {
        Observable.just(1, 2, 3, 4)
                .first(0)
                .subscribe(i -> Log.i(tag, "first   ->" + i));


    }


    /**
     * 只发射第一个数据,与first的区别：返回值不同
     *
     * @param view
     */

    public void firstElement(View view) {
        Observable.just(1, 2, 3, 4)
                .firstElement()
                .subscribe(i -> Log.i(tag, "firstElement   ->" + i));


    }

    /**
     * 不关心数据发射，当数据发射完成后执行complete
     *
     * @param view
     */
    public void ignoreElement(View view) {
        Observable.just(1, 2, 3, 4)
                .ignoreElements()
                .subscribe(() -> Log.i(tag, "ignoreElement----->"));
    }

    public void last(View view) {
        Observable.just(1, 2, 3, 4)
                .last(2)
                .subscribe(i -> Log.i(tag, "last----->" + i));


        Observable.just(1, 2, 3, 4)
                .lastElement()
                .subscribe(i -> Log.i(tag, "lastElement----->" + i));
    }


    /**
     * 类型过滤
     *
     * @param view
     */
    public void ofType(View view) {

        Observable<Float> o = Observable.just(1, 1.2f, 2)
                .ofType(Float.class);

        o.subscribe(i -> Log.i(tag, "ofType----->" + i));

    }

    public void simple(View view) {
        Observable<Integer> o = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(500);
                emitter.onNext(2);
                Thread.sleep(500);
                emitter.onNext(3);
                Thread.sleep(500);
                emitter.onNext(4);
                Thread.sleep(500);
                emitter.onNext(5);
                Thread.sleep(500);
            }
        }).sample(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io());

        o.subscribe(i -> Log.i(tag, "simple----->" + i));
    }


    /**
     * 跳过n个数据
     *
     * @param view
     */
    public void skip(View view) {

        Observable.just(1, 2, 3, 4, 5)
                .skip(2)
                .subscribe(i -> Log.i(tag, "skip----->" + i));

        Observable.just(1, 2, 3, 4, 5)
                .skipLast(2)
                .subscribe(i -> Log.i(tag, "skipLast----->" + i));
    }

    /**
     * 只取n个数据
     *
     * @param view
     */
    public void take(View view) {

        Observable.just(1, 2, 3, 4, 5)
                .take(2)
                .subscribe(i -> Log.i(tag, "take----->" + i));

        Observable.just(1, 2, 3, 4, 5)
                .takeLast(2)
                .subscribe(i -> Log.i(tag, "takeLast----->" + i));
    }

    /**
     * 过滤超时的任务
     *
     * @param view
     */

    public void timeout(View view) {


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter)     {


                try {
                    Thread.sleep(400);
                    emitter.onNext(2);
                    Thread.sleep(800);
                    emitter.onNext(3);
                    Thread.sleep(1200);
                    emitter.onNext(4);
                    emitter.onComplete();
                }catch (Exception e){

                }


            }
        })
                .timeout(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(i -> Log.i(tag, "timeout----->" + i),
                        e->Log.i(tag,"tieout err="+e.getMessage()));
    }
}
