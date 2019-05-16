package com.sen.study_android.yishutansuo;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.sen.study_android.R;

public class ThreadHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_handler);

        HandlerThread handlerThread=new HandlerThread("handlerthread");
        handlerThread.start();
        Handler handler=new Handler(handlerThread.getLooper());


        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.e("sen","test HandlerThread-->"+Thread.currentThread().getName());
            }
        });
    }

}
