package com.sen.study_android.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.sen.study_android.R
import kotlinx.android.synthetic.main.activity_coroutines_test.*
import kotlinx.coroutines.*
import kotlin.sequences.sequence as sequence1

class CoroutinesTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_test)
        setup(count_setup)
        down_setup.setOnClickListener {
            count_setup.text = "setDown"
        }
        var list = (0..12)



        kotlin.sequences.generateSequence { }.toList();


    }

    fun setup(hello: TextView) {
        GlobalScope.launch(Dispatchers.Main) {
            // 在主线程中启动协程
            for (i in 10 downTo 1) { // 从 10 到 1 的倒计时
                hello.text = "Countdown $i ..." // 更新文本
                delay(20000) // 等待半秒钟
            }
            hello.text = "Done!"
        }
    }
}
