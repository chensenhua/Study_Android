package ac692x_case.jieli.com.ipcapplication.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BinderPoolservice extends Service {


    public BinderPoolservice() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new BinderPool.BindrPoolImpl();
    }
}
