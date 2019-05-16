package com.sen.study_android.window;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import com.sen.study_android.R;

import static android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

public class WindowTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_test);
    }


    public void showFloatButton(View view) {
        Log.e("sen", "showFloatButton");


        Button mFloatingButton = new Button(this);
//        mFloatingButton.getLayoutParams().width=ViewGroup.LayoutParams.MATCH_PARENT;
//        mFloatingButton.getLayoutParams().height=ViewGroup.LayoutParams.MATCH_PARENT;
        mFloatingButton.setBackgroundColor(Color.BLUE);

        mFloatingButton.setText("floatingButton");
        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT
                , WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                0,
                PixelFormat.TRANSPARENT
        );
        layoutParams.gravity = Gravity.LEFT|Gravity.TOP;


        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.flags =

                         WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        | WindowManager.LayoutParams.FLAG_DIM_BEHIND


        ;
        layoutParams.dimAmount = 0.5f;


        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);


       //Window window=new PhoneWindow();


        windowManager.addView(mFloatingButton, layoutParams);




        mFloatingButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("sen",event.toString());
                if (MotionEvent.ACTION_MOVE == event.getAction()) {
                    layoutParams.x = (int) event.getRawX();
                    layoutParams.y = (int) event.getRawY();
                    windowManager.updateViewLayout(mFloatingButton, layoutParams);


                }
                return false;
            }
        });
    }
}
