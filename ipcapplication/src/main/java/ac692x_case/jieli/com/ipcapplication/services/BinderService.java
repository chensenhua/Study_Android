package ac692x_case.jieli.com.ipcapplication.services;

import ac692x_case.jieli.com.ipcapplication.aidl.User;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class BinderService extends Service {
    private static final String TAG = "BinderService";

    public BinderService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand------and startId = " + startId);
        Log.e(TAG, "onStartCommand------and intent = " + intent);

        return START_REDELIVER_INTENT ;
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new UserBinder();
    }


}
