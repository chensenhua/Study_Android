package ac692x_case.jieli.com.ipcapplication.services;

import ac692x_case.jieli.com.ipcapplication.aidl.User;
import android.os.Binder;

public class UserBinder extends Binder {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
