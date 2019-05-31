package com.sen.study_android.mvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.sen.study_android.R;

public class MvvmTestActivity extends AppCompatActivity {

    private ConnectMoudle  mConnectMoudle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm_test);
        mConnectMoudle= ViewModelProviders.of(this).get(ConnectMoudle.class);
    }
}
