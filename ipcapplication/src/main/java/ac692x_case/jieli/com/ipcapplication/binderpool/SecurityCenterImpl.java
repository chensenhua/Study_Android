package ac692x_case.jieli.com.ipcapplication.binderpool;

import android.os.RemoteException;
import android.util.Log;

public class SecurityCenterImpl extends ISecurityCenter.Stub {
    private int count;
    @Override
    public void doWork() throws RemoteException {
        Log.e("SecurityCenterImpl","--------doWork---------"+(++count));
    }
}
