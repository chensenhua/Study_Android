package com.sen.study_android.utils;

import android.util.Log;


public class Slog   {


    public static void i(String tag,String msg){
        Log.i(tag,msg);
    }


    public static void e(String tag,String msg){
        Log.e(tag,msg);
    }
}
