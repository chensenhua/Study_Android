package com.sen.study_android.rxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.sen.study_android.FilterOperatorActivity;
import com.sen.study_android.R;
import io.reactivex.*;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import io.reactivex.internal.schedulers.SchedulerPoolFactory;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class RxJavaStudyActivity extends AppCompatActivity {

    private String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_study);

    }


    public void helloRxjava(View view) {
        Flowable.fromArray(new String[]{"one", "two", "three"}).subscribe(s -> Log.i(tag, "helloRxjava-->" + s));

       /* Flowable.fromArray(new String[]{"one","two","three"}).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(tag,"helloRxjava-->"+s)
            }
        });*/
    }

    public void createObservables(View view) {

        Observable<String> one = Observable.fromArray("a", "b", "c");

        Observable<String> two = Observable.just("1", "2", "3");

        Observable<String> three = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                emitter.onNext("A");
                emitter.onNext("B");
                emitter.onNext("C");
                emitter.onComplete();

            }
        });

//        Observable<String> three1=Observable.create(emitter -> {
//            emitter.onNext("A");
//            emitter.onNext("B");
//            emitter.onNext("C");
//            emitter.onComplete();
//        });


        one.subscribe(s -> Log.i(tag, "createObservables one-->" + s));
        two.subscribe(s -> Log.i(tag, "createObservables two-->" + s));
        three.subscribe(s -> Log.i(tag, "createObservables three-->" + s));

    }

    public void createFlowable(View view) {

        Flowable<String> one = Flowable.fromArray("a", "b", "c");
        one.subscribe(s -> Log.i(tag, "createFlowable one -->" + s));

        Flowable<String> two = Flowable.just("1", "2", "3");
        two.subscribe(s -> Log.i(tag, "createFlowable two-->" + s));


        Flowable<String> three = Flowable.fromPublisher(new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> s) {
                s.onNext("A");
                s.onNext("B");
                s.onNext("C");

            }
        });
        three.subscribe(s -> Log.i(tag, "createFlowable three-->" + s));

        Flowable<String> four = Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "call";
            }
        });

        four.subscribe(s -> Log.i(tag, "createFlowable four-->" + s));


        Flowable<String> five = Flowable.just("a", "b");
        five.subscribe(s -> Log.i(tag, "createFlowable five-->" + s));


        Flowable<String> six = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> s) throws Exception {
                s.onNext("A");
                s.onNext("B");
                s.onNext("C");
            }
        }, BackpressureStrategy.DROP);


        six.subscribe(s -> Log.i(tag, "createFlowable six-->" + s));

    }

    public void createSingle(View view) {

        Single<String> single = Single.just("single");
        single.subscribe(s -> Log.i(tag, "create single--->" + s));
    }


    /**
     * AsyncSubject:只有当调用onComplete时才会发射最后一个数据，如果发生错误则不会发射数据，仅仅传递错误
     *
     * @param view
     */
    public void createAsyncSubject(View view) {
        AsyncSubject asyncSubject = AsyncSubject.create();
        asyncSubject.onNext("a");
        asyncSubject.onNext("b");

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(70);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                asyncSubject.onNext("c");
                asyncSubject.onComplete();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                asyncSubject.subscribe(s -> Log.i(tag, "create AsyncSubject 2--->" + s));

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                asyncSubject.subscribe(s -> Log.i(tag, "create AsyncSubject 3--->" + s));


                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                asyncSubject.subscribe(s -> Log.i(tag, "create AsyncSubject 4--->" + s));
            }
        }.start();

    }


    /**
     * BehaviorSubject会缓存最后一个发射的数据，当有订阅新的观察者时，会将最后的发射数据发送给订阅的观察者
     *
     * @param view
     */
    public void createBehaviorSubject(View view) {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.createDefault("default");

        behaviorSubject.subscribe(s -> Log.i(tag, "createBehaviorSubject 1-->" + s));
        behaviorSubject.onNext("1");
        behaviorSubject.subscribe(s -> Log.i(tag, "createBehaviorSubject 2-->" + s));
        behaviorSubject.onNext("2");
        behaviorSubject.onComplete();

        behaviorSubject.subscribe(s -> Log.i(tag, "createBehaviorSubject 3-->" + s));
        behaviorSubject.onNext("3");
    }


    /**
     * PublishSubject,观察者只能订阅发生订阅时间之后Observable的事件
     *
     * @param view
     */
    public void createPublishSubject(View view) {
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.onNext("a");
        publishSubject.subscribe(s -> Log.i(tag, "createPublishSubject  1-->" + s));
        publishSubject.onNext("b");
        publishSubject.subscribe(s -> Log.i(tag, "createPublishSubject  2-->" + s));
        publishSubject.onNext("c");
        publishSubject.onComplete();
    }

    /**
     * ReplaySubject 会发射所有已发射过的数据给观察者
     *
     * @param view
     */

    public void createReplaySubject(View view) {

        ReplaySubject<String> replaySubject = ReplaySubject.create();
        replaySubject.onNext("a");
        replaySubject.subscribe(s -> Log.i(tag, "createReplaySubject  1-->" + s));
        replaySubject.onNext("b");
        replaySubject.subscribe(s -> Log.i(tag, "createReplaySubject  2-->" + s));
        replaySubject.onNext("c");
        replaySubject.subscribe(s -> Log.i(tag, "createReplaySubject  3-->" + s));
        replaySubject.onComplete();

    }

    public void scheduler(View view) {

    }

    public void asyncOperators(View view) {
        startActivity(new Intent(this, AsyncOperatorsActivity.class));

    }


    public void createOperators(View view) {
        startActivity(new Intent(this, CreateOpratorsActivity.class));
    }

    public void transformOperators(View view) {
        startActivity(new Intent(this, TransformOperatorActivity.class));
    }

    public void filterOperators(View view) {

        startActivity(new Intent(this, FilterOperatorActivity.class));
    }

    public void combiningOperators(View view) {

    }
}
