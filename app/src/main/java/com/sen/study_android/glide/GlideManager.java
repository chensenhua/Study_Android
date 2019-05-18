package com.sen.study_android.glide;

import android.content.Context;
import com.bumptech.glide.Glide;

public class GlideManager {

    private static GlideManager mInstance=new GlideManager();

    public static GlideManager getInstance() {
        return mInstance;
    }

    public void loadView(Context context){
//        Glide.with(context).asBitmap().load("").
    }
}
