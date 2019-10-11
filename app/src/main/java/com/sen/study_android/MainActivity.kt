package com.sen.study_android

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityOptionsCompat
import android.transition.AutoTransition
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.util.Log
import android.view.View
import android.view.Window
import com.google.gson.annotations.Expose
import com.sen.study_android.animators.AnimatorsActivity
import com.sen.study_android.asnytask.AsyncTaskTestActivity
import com.sen.study_android.bitmap.BitmapTestActivity
import com.sen.study_android.kotlin.KotlinTestActivity
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

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(R.layout.activity_main)
        var foo = Foo();
        Log.e("sen", foo.toString())



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val slide = Slide()
            slide.duration = 2000
            window.exitTransition = slide
            window.enterTransition = slide
            window.sharedElementEnterTransition = slide
            window.sharedElementExitTransition = slide
            window.allowEnterTransitionOverlap=false;

        }



        this.window?.decorView?.apply {
            systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    // Set the content to appear under the system bars so that the
                    // content doesn't resize when the system bars hide and show.
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }

        this.window?.decorView?.setOnSystemUiVisibilityChangeListener {
            window?.decorView?.apply {
                systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN)
            }
        }

        this.actionBar?.hide()

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
            startActivity(Intent(this, okHttpActivity::class.java))
        }


        to_mvvm_study.setOnClickListener {
            startActivity(Intent(this, MvvmTestActivity::class.java))
        }

        to_animator_study.setOnClickListener {
         //   startActivity(Intent(this, AnimatorsActivity::class.java))
            startActivity(Intent(this, AnimatorsActivity::class.java),ActivityOptionsCompat.makeSceneTransitionAnimation(this,it,it.transitionName).toBundle())
        }

        to_kotlin_study.setOnClickListener {
            startActivity(Intent(this,KotlinTestActivity::class.java))
        }

        btn_to_bitmap.setOnClickListener {
            startActivity(Intent(this,BitmapTestActivity::class.java));
        }


    }
}
