package com.sen.study_android;

import com.sen.study_android.okHttp.OkHttpManager;
import org.junit.Test;

import java.io.IOException;


public class OkHttpTest {
    @Test
    public void testHttpGet() throws IOException {
        OkHttpManager.getInstance().testGet();
    }
}
