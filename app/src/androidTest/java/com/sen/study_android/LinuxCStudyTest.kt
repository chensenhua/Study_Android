package com.sen.study_android

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.sen.study_android.linux_c.HelloLinuxC

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LinuxCStudyTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        helloLinuxC()

        HelloLinuxC().testOpenFile(appContext);
    }

    fun helloLinuxC() {
        HelloLinuxC().testHelloLinex()
    }


}
