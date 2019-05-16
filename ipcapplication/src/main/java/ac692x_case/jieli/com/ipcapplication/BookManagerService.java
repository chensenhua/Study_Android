package ac692x_case.jieli.com.ipcapplication;

import ac692x_case.jieli.com.ipcapplication.aidl.Book;
import ac692x_case.jieli.com.ipcapplication.aidl.IBookManager;
import ac692x_case.jieli.com.ipcapplication.aidl.IMyAidlInterface;
import ac692x_case.jieli.com.ipcapplication.aidl.User;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import java.util.List;

public class BookManagerService extends Service {

    private RemoteCallbackList<IMyAidlInterface> list = new RemoteCallbackList<>();


    private void handlerBookChange() {
        int N = list.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IMyAidlInterface iMyAidlInterface = list.getBroadcastItem(i);
            if (iMyAidlInterface != null) {
                try {
                    iMyAidlInterface.onBookChange(BookManager.getInstance().getBooks());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        list.finishBroadcast();
    }

    private IBinder iBookManager = new IBookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            return BookManager.getInstance().getBooks();
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            BookManager.getInstance().addBook(book);
            handlerBookChange();

        }

        @Override
        public void setUser(User user) throws RemoteException {
            BookManager.getInstance().setUser(user);
        }

        @Override
        public User getUser() throws RemoteException {
            return BookManager.getInstance().getUser();
        }

        @Override
        public void registerListener(IMyAidlInterface listener) throws RemoteException {
            list.register(listener);

        }

        @Override
        public void unregisterListener(IMyAidlInterface listener) throws RemoteException {
            list.unregister(listener);

        }
    };

    public BookManagerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBookManager;
    }
}
