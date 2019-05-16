package com.sen.study_android.okHttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.sen.study_android.R;

import java.io.IOException;

public class okHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
    }

    public void testGet(View view) throws IOException {
        OkHttpManager.getInstance().testGet();
    }
}
