package ac692x_case.jieli.com.myapplication;

import android.app.Application;
import android.util.Log;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("sen","---onZCreate=====");
    }
}
