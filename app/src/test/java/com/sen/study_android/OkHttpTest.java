package com.sen.study_android;

import com.sen.study_android.okHttp.OkHttpManager;
import org.junit.Test;


public class OkHttpTest {
    @Test
    public void testHttpGet(){
        OkHttpManager.getInstance().testGet();
    }
}
