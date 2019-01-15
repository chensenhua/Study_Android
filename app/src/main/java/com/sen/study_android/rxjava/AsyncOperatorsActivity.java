package com.sen.study_android.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.sen.study_android.R;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class AsyncOperatorsActivity extends AppCompatActivity {
    private String tag=getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_operators);
    }

    /**
     * 在发射数据源的前面插入数据
     * @param view
     */
    public void startOperators(View view) {

        Flowable<String> flowable=Flowable.just("a").startWith("b");
        flowable.subscribe(s-> Log.i(tag,"startOperators----->1-->"+s));


        /**
         * 这种情况，just操作符的数据会无效
         */
        Observable<String> flowable1=Observable.just("1").startWith(new ObservableSource<String>() {
            @Override
            public void subscribe(Observer<? super String> observer) {
                observer.onNext("2");
            }
        });

        flowable1.subscribe(s->  Log.i(tag,"startOperators----->2-->"+s));




    }

    public void toAsyncOperators(View view) {

    }
}
