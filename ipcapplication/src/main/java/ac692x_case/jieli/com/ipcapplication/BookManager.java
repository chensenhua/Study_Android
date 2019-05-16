package ac692x_case.jieli.com.ipcapplication;

import ac692x_case.jieli.com.ipcapplication.aidl.Book;
import ac692x_case.jieli.com.ipcapplication.aidl.IBookManager;
import ac692x_case.jieli.com.ipcapplication.aidl.IMyAidlInterface;
import ac692x_case.jieli.com.ipcapplication.aidl.User;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public final class BookManager {
    private List<Book> books = new ArrayList<>();
    private User mUser;
    private static BookManager instance = new BookManager();

    public static BookManager getInstance() {
        return instance;
    }

    private Binder binder = new Binder();


    public List<Book> getBooks() throws RemoteException {
        return binder.getBooks();
    }


    public void addBook(Book book) throws RemoteException {
        binder.addBook(book);
    }


    public void setUser(User user) throws RemoteException {
        binder.setUser(user);
    }


    public User getUser() throws RemoteException {
        return binder.getUser();
    }


    private final class Binder extends IBookManager.Stub {
        @Override
        public List<Book> getBooks() throws RemoteException {
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Log.e("BookManager",Thread.currentThread().getName());
            books.add(book);
        }

        @Override
        public void setUser(User user) throws RemoteException {
              mUser = user;
        }

        @Override
        public User getUser() throws RemoteException {
            return mUser;
        }

        @Override
        public void registerListener(IMyAidlInterface listener) throws RemoteException {

        }

        @Override
        public void unregisterListener(IMyAidlInterface listener) throws RemoteException {

        }
    }


}
