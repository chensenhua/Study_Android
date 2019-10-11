package com.sen.study_android.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.LruCache
import android.util.TypedValue
import com.bumptech.glide.disklrucache.DiskLruCache
import com.sen.study_android.BuildConfig
import com.sen.study_android.R
import kotlinx.android.synthetic.main.activity_bitmap_test.*

class BitmapTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_test)


        var assetManager = assets.open("img1.jpg");


        var option = BitmapFactory.Options();
        option.inJustDecodeBounds = true;
//        option.inSampleSize = 4
        option.inPreferredConfig = Bitmap.Config.RGB_565
        //   option.inDensity = 480
        // option.inScreenDensity = 1920
        option.inTargetDensity = 120//影响bitmap长宽


        var bitmap1 = BitmapFactory.decodeStream(assetManager, null, option);

        Log.e("option outHeight", option.outHeight.toString())
        Log.e("option outWidth", option.outWidth.toString())
        Log.e("option inTargetDensity", option.inTargetDensity.toString())
        Log.e("option inDensity", option.inDensity.toString())


        option.inJustDecodeBounds = false;
        var assetManager2 = assets.open("img1.jpg");
        var bitmap2 = BitmapFactory.decodeStream(assetManager2, null, option);




        Log.e("bitmap", bitmap2.height.toString())
        Log.e("bitmap", bitmap2.width.toString())

        iv_img1.setImageBitmap(bitmap2);


        var maxMemeory: Long = Runtime.getRuntime().maxMemory() / 1024 / 8;


        var lruCache: LruCache<String, Bitmap> =
            object : LruCache<String, Bitmap>(maxMemeory.toInt()) {

                override fun sizeOf(key: String?, value: Bitmap?): Int {
                    return super.sizeOf(key, value)
                }
            };

        lruCache.put("bitmap2", bitmap2);

        var bitmap3 = lruCache.get("bitamp2");


        Log.e("bitmap3", bitmap3.height.toString())
        Log.e("bitmap3", bitmap3.width.toString())

        var disLruCache =DiskLruCache.open(cacheDir,BuildConfig.VERSION_CODE,1,1024*1024*100);//100M


    }
}
