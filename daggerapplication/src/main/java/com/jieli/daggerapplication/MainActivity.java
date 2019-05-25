package com.jieli.daggerapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.jieli.daggerapplication.hello.HelloActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   AndroidInjection.inject(this);
    }

    public void toHello(View view) {
        startActivity(new Intent(this, HelloActivity.class));
    }
}
