package com.sen.study_android.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.sen.study_android.R;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Function;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


public class CreateOpratorsActivity extends AppCompatActivity {
    private String tag=getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_oprators);
    }

    public void createOperator(View view) {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("createOperator  Observable 1");
            emitter.onNext("createOperator Observable 2");
            emitter.onComplete();

        }).subscribe(s->Log.i(tag,s)) ;


        Flowable.create((FlowableOnSubscribe<String>) emitter -> {
            emitter.onNext("createOperator  Flowable 1");
            emitter.onNext("createOperator Flowable 2");
        },BackpressureStrategy.DROP).subscribe(s->Log.i(tag,s));



    }


    /**
     * defer操作符为每一个订阅者生成独立的Observable
     * @param view
     */

    public void deferOperator(View view) {
        Observable<String> observable=   Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                return new ObservableSource<String>() {
                    @Override
                    public void subscribe(Observer<? super String> observer) {
                        observer.onNext("deferOperator--1--->"+this.toString());
                        observer.onNext("deferOperator--2--->"+this.toString()
                        );
                    }
                };
            }
        });

        observable.subscribe(s->Log.e(tag,s));
        observable.subscribe(s->Log.e(tag,s));
    }


    /**
     * 发射一个不发射任何数据但会发射终止的Observable
     * @param view
     */
    public void emptyOperator(View view) {
        Observable<String> observable=Observable.empty();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(tag,"emptyOperator--->onSubscribe-->"+d);
            }

            @Override
            public void onNext(String s) {
                Log.e(tag,"emptyOperator--->onNext-->"+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(tag,"emptyOperator--->onError-->"+e.getMessage());

            }

            @Override
            public void onComplete() {

                Log.e(tag,"emptyOperator--->onComplete-->" );

            }
        });


        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(tag,"emptyOperator 1--->onSubscribe-->"+d);
            }

            @Override
            public void onNext(String s) {
                Log.e(tag,"emptyOperator 1--->onNext-->"+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(tag,"emptyOperator 1--->onError-->"+e.getMessage());

            }

            @Override
            public void onComplete() {

                Log.e(tag,"emptyOperator 1 --->onComplete-->" );

            }
        });
    }


    /**
     * never 创建一个不发射数据也不发射终止的Observable
     * @param view
     */

    public void neverOperator(View view) {

        Observable<String> observable=Observable.never();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(tag,"never--->onSubscribe-->"+d);
            }

            @Override
            public void onNext(String s) {
                Log.e(tag,"never--->onNext-->"+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(tag,"never--->onError-->"+e.getMessage());

            }

            @Override
            public void onComplete() {

                Log.e(tag,"never--->onComplete-->" );

            }
        });


        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(tag,"never 1--->onSubscribe-->"+d);
            }

            @Override
            public void onNext(String s) {
                Log.e(tag,"never 1--->onNext-->"+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(tag,"never 1--->onError-->"+e.getMessage());

            }

            @Override
            public void onComplete() {

                Log.e(tag,"never 1--->onComplete-->" );

            }
        });


    }


    /**
     * error创建一个不发射数据但发射一个错误终止的Obsetvable
     * @param view
     */
    public void errorOperator(View view) {
        Observable<String> observable=Observable.error(new RuntimeException("errorOperator"));
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(tag,"errorOperator--->onSubscribe-->"+d);
            }

            @Override
            public void onNext(String s) {
                Log.e(tag,"errorOperator--->onNext-->"+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(tag,"errorOperator--->onError-->"+e.getMessage());

            }

            @Override
            public void onComplete() {

                Log.e(tag,"errorOperator--->onComplete-->" );

            }
        });



        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(tag,"errorOperator 1--->onSubscribe -->"+d);
            }

            @Override
            public void onNext(String s) {
                Log.e(tag,"errorOperator 1--->onNext-->"+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(tag,"errorOperator 1--->onError-->"+e.getMessage());

            }

            @Override
            public void onComplete() {

                Log.e(tag,"errorOperator 1--->onComplete-->" );

            }
        });
    }


    /**
     * from操作符，通过数据源创建Observable
     * @param view
     */
    public void fromOperator(View view) {
        Observable<String> observable=Observable.fromArray("fromOperator-->1","fromOperator-->2");
        observable.subscribe(s->Log.i(tag,s));
    }

    /**
     * interval 操作符创建一个定时的Observable
     * @param view
     */
    public void intervalOperator(View view) {
        Log.i(tag,"intervalOperator-->init" );
        Observable<Long> observable=Observable.interval( 2,2,TimeUnit.SECONDS);

        observable.subscribe(s->Log.i(tag,"intervalOperator-->"+s));

    }
    /**
     * just操作符，通过数据源创建Observable,与from的区别：just不会解析参数，如数组，只会直接发送数组而不是发送数组里面的内容
     * @param view
     */

    public void justOperator(View view) {

        Observable<String> observable=Observable.just("justOperator-->1","justOperator-->2");
        observable.subscribe(s->Log.i(tag,s));
    }

    /**
     *range操作符创建一个范围内的通过数据源创建Observable
     * @param view
     */
    public void rangeOperator(View view) {
        Observable.range(1,10).subscribe(i->Log.i(tag," rangeOperator  ---index-->"+i));
    }

    /**
     * repeate 操作符，创建一个重复发射的Observable
     * @param view
     */
    public void repeatOperator(View view) {

        Observable observable=Observable.just("1").repeat(5);
        observable.subscribe(s->Log.e(tag,"repeatOperator--"+s));

    }

    /**
     * timer操作符，创建一个按时一定时间发送一个简单数字0的Observable
     * @param view
     */

    public void timerOperator(View view) {
        Log.e(tag,"timerOperator--init");
        Observable observable=Observable.timer(1,TimeUnit.SECONDS);
        observable.subscribe(s->Log.e(tag,"timerOperator--"+s));
    }
}
