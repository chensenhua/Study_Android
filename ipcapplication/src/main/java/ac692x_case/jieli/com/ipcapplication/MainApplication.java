package ac692x_case.jieli.com.ipcapplication;

import android.app.Application;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("sen", Process.myPid()+"");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Log.e("sen", getProcessName());
        }
    }
}
