package com.sen.study_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sen.study_android.rxjava.RxJavaStudyActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_to_rxjava.setOnClickListener {
         startActivity(Intent(this,RxJavaStudyActivity::class.java))
        }
    }
}
