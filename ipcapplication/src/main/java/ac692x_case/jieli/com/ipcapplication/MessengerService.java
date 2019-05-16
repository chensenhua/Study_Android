package ac692x_case.jieli.com.ipcapplication;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;

public class MessengerService extends Service {

    private Messenger messenger=new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("MessengerService","msg-->"+msg.toString());
            try {
                msg.replyTo.send(Message.obtain(null,1));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    });
    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return  messenger.getBinder();
    }
}
