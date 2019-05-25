package com.jieli.daggerandroidapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.jieli.daggerandroidapplication.bean.Dog;
import dagger.android.AndroidInjection;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {


    @Inject
    Dog dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.e("sen",dog.toString());
    }
}
