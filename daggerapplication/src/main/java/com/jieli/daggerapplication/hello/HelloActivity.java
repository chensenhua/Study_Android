package com.jieli.daggerapplication.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jieli.daggerapplication.R;

import javax.inject.Inject;

public class HelloActivity extends AppCompatActivity {
    @Inject
    Pot pot;

    Pot pot1;


    @Inject
    Flower flower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        DaggerHelloActivityComponent.builder().build().inject(this);
        String show = pot.show();
        Toast.makeText(HelloActivity.this, show, Toast.LENGTH_SHORT).show();
    }

    @Inject
    public void setPot1(Pot pot1) {
        this.pot1 = pot1;
    }
}
