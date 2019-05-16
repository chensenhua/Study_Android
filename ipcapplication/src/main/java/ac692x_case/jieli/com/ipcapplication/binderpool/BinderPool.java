package ac692x_case.jieli.com.ipcapplication.binderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

public class BinderPool {
    private static volatile BinderPool sInstance;
    private Context mContext;
    private IBinderPool mBinderPool;
    private CountDownLatch countDownLatch;

    private BinderPool(Context context) {
        mContext = context.getApplicationContext();
        connectBinderPoolService();
    }

    private void connectBinderPoolService() {
        countDownLatch = new CountDownLatch(1);
        mContext.bindService(new Intent(mContext, BinderPoolservice.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("sen", "-----------connectBinderPoolService sccess--------");
                mBinderPool = IBinderPool.Stub.asInterface(service);
                countDownLatch.countDown();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("sen", "-----------connectBinderPoolService disconnected--------");
            }
        }, Context.BIND_AUTO_CREATE);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public IBinder findBinder(int code) {
        if (mBinderPool != null) {
            try {
                return mBinderPool.findBinder(code);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static BinderPool getInsance(Context context) {
        if (sInstance == null) {
            synchronized (BinderPool.class) {
                if (sInstance == null) {
                    sInstance = new BinderPool(context);
                }
            }
        }
        return sInstance;
    }


    static class BindrPoolImpl extends IBinderPool.Stub {
        private ComputeImpl compute;
        private SecurityCenterImpl securityCenter;

        @Override
        public IBinder findBinder(int code) throws RemoteException {
            IBinder binder = null;

            switch (code) {
                case 0:
                    if (compute == null) {
                        compute = new ComputeImpl();
                    }
                    binder = compute;
                    break;
                case 1:
                    if (securityCenter == null) {
                        securityCenter = new SecurityCenterImpl();
                    }

                    binder = securityCenter;
                    break;
            }
            return binder;
        }
    }

}
