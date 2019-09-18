package com.sen.study_android.kotlin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.sen.study_android.R;

public class KotlinTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotlin_test);

    }

    public void toCoroutinestTest(View view) {
        startActivity(new Intent(this, CoroutinesTestActivity.class));
    }
}
