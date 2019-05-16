package ac692x_case.jieli.com.ipcapplication.binderpool;

import android.os.RemoteException;
import android.util.Log;

public class ComputeImpl extends ICompute.Stub {
    private int count;
    @Override
    public void doWork() throws RemoteException {
        Log.e("ComputeImpl","--------doWork---------"+(++count));
    }
}
