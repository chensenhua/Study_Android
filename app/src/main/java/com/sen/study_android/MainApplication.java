package com.sen.study_android;

import android.app.Application;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Thread.dumpStack();
    }
}
