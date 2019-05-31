package com.sen.study_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sen.study_android.asnytask.AsyncTaskTestActivity
import com.sen.study_android.mvvm.MvvmTestActivity
import com.sen.study_android.okHttp.okHttpActivity
import com.sen.study_android.rxjava.RxJavaStudyActivity
import com.sen.study_android.utils.Foo
import com.sen.study_android.window.WindowTestActivity
import com.sen.study_android.yishutansuo.RemoteViewStudy
import com.sen.study_android.yishutansuo.ThreadHandlerActivity
import com.sen.study_android.yishutansuo.ThreadLocalActivity
import com.sen.study_android.yishutansuo.chapter1.LiftCircleActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var foo = Foo();
        Log.e("sen", foo.toString())



        btn_to_rxjava.setOnClickListener {
            startActivity(Intent(this, RxJavaStudyActivity::class.java))
        }
        btn_to_remoteviews.setOnClickListener {
            startActivity(Intent(this, RemoteViewStudy::class.java))
        }
        btn_to_threadLocal.setOnClickListener {
            startActivity(Intent(this, ThreadLocalActivity::class.java))
        }
        btn_to_threadhandler.setOnClickListener {
            startActivity(Intent(this, ThreadHandlerActivity::class.java))
        }
        btn_to_lifecircle.setOnClickListener {
            startActivity(Intent(this, LiftCircleActivity::class.java))
        }

        to_window_study.setOnClickListener {
            startActivity(Intent(this, WindowTestActivity::class.java))
        }

        to_asynctask.setOnClickListener {
            startActivity(Intent(this, AsyncTaskTestActivity::class.java))
        }
        to_okhttp_study.setOnClickListener {
            startActivity(Intent(this,  okHttpActivity::class.java))
        }


        to_mvvm_study.setOnClickListener {
            startActivity(Intent(this,  MvvmTestActivity::class.java))
        }


    }
}
