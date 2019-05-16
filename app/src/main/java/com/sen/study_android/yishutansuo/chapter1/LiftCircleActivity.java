package com.sen.study_android.yishutansuo.chapter1;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import com.sen.study_android.R;

public class LiftCircleActivity extends AppCompatActivity {
    private String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift_circle);
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.e(tag, "onStop");

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(tag, "onStart : "+findViewById(R.id.lift_circle_text).getWidth());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(tag, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(tag, "onResume:"+findViewById(R.id.lift_circle_text).getWidth());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(tag, "onDestroy");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(tag, "onAttachedToWindow:"+findViewById(R.id.lift_circle_text).getWidth());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(tag, "onConfigurationChanged:"+findViewById(R.id.lift_circle_text).getWidth());
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(tag, "onDetachedFromWindow");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            Log.e(tag, "onRestoreInstanceState-->  " + savedInstanceState.getString("test"));

        } else {
            Log.e(tag, "onRestoreInstanceState--> savedInstanceState==null");
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("test", "onSaveInstanceState");
        Log.e(tag, "onSaveInstanceState");
    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.e(tag, "onPostCreate");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.e(tag, "onPostResume");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(tag, "onRestart");
    }


    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.e(tag, "onUserLeaveHint");
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Log.e(tag, "onUserInteraction");
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        Log.e(tag, "onMultiWindowModeChanged");
    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.e(tag, "onAttachFragment");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e(tag, "onCreateOptionsMenu:"+findViewById(R.id.lift_circle_text).getWidth());
        return super.onCreateOptionsMenu(menu);

    }


    public void enterNext(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
