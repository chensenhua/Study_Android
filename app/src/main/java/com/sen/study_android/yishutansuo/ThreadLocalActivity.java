package com.sen.study_android.yishutansuo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.sen.study_android.R;

public class ThreadLocalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_local);


        for(int i=0;i<10;i++){
            new Thread("threadlocal test thread-->"+i){
                @Override
                public void run() {
                    threadLocal.set(getName());
                    Log.e("sen","thread name="+getName()+"\tthreadLocal value="+threadLocal.get());

                }
            }.start();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("sen","thread  main  =" +"\tthreadLocal value="+threadLocal.get());

            }
        },2000);
    }



    private ThreadLocal<String> threadLocal=new ThreadLocal<>();

}
