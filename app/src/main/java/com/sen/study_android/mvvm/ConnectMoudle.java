package com.sen.study_android.mvvm;

import android.arch.lifecycle.ViewModel;
import com.sen.study_android.utils.Slog;

public class ConnectMoudle extends ViewModel {
    private String tag=getClass().getSimpleName();

    @Override
    protected void onCleared() {

        super.onCleared();
        Thread.dumpStack();
        Slog.i(tag,"----onCleared---");

    }
}
