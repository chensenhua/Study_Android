package ac692x_case.jieli.com.ipcapplication;

import ac692x_case.jieli.com.ipcapplication.aidl.Book;
import ac692x_case.jieli.com.ipcapplication.aidl.IBookManager;
import ac692x_case.jieli.com.ipcapplication.aidl.IMyAidlInterface;
import ac692x_case.jieli.com.ipcapplication.aidl.User;
import ac692x_case.jieli.com.ipcapplication.binderpool.*;
import ac692x_case.jieli.com.ipcapplication.services.BinderService;
import ac692x_case.jieli.com.ipcapplication.services.UserBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferencesHelper.putStringValue(this, "sen", "main");
        Log.e("main", "SingleClass-->" + SingleClass.getInstance().toString());


        testBookAidl();
        testMessenger();
        testBinderPool();
        testBinderService();

    }

    private void testBinderService() {
//        bindService(new Intent(this, BinderService.class), new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//                UserBinder userBinder= (UserBinder) service;
//                User user=  new User();
//                user.setName("test");
//                userBinder.setUser(user);
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//
//            }
//        }, Context.BIND_AUTO_CREATE);


//        startService(new Intent(this, BinderService.class));
    }


    private void testBinderPool() {
        new Thread() {
            @Override
            public void run() {
                ICompute compute = ComputeImpl.asInterface(BinderPool.getInsance(getApplication()).findBinder(0));
                ISecurityCenter securityCenter = SecurityCenterImpl.asInterface(BinderPool.getInsance(getApplication()).findBinder(1));
                try {
                    compute.doWork();
                    securityCenter.doWork();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    private void testBookAidl() {
        bindService(new Intent(this, BookManagerService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IBookManager iBookManager = IBookManager.Stub.asInterface(service);
                Book book = new Book();
                book.setId(0);
                book.setName("book");
                try {
                    iBookManager.registerListener(new IMyAidlInterface.Stub() {

                        @Override
                        public void onBookChange(List<Book> books) throws RemoteException {
                            Log.e("main", "onBookChange-->" + books.toString());

                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    iBookManager.addBook(book);
                    Log.e("main", "BookManager.getInstance().getBooks()-->" + iBookManager.getBooks().toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);

        IBookManager iBookManager=IBookManager.Stub.asInterface(bookManager.asBinder());
        Book book = new Book();
        book.setId(1);
        book.setName("book1");
        try {
            iBookManager.addBook(book);
            Log.e("main", "BookManager.getInstance().getBooks() 1-->" + iBookManager.getBooks().toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }



    public static IBookManager bookManager =  new IBookManager.Stub() {
        private    List<Book> books=new ArrayList<>();
        @Override
        public List<Book> getBooks() throws RemoteException {
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
         books.add(book);
        }

        @Override
        public void setUser(User user) throws RemoteException {

        }

        @Override
        public User getUser() throws RemoteException {
            return null;
        }

        @Override
        public void registerListener(IMyAidlInterface listener) throws RemoteException {

        }

        @Override
        public void unregisterListener(IMyAidlInterface listener) throws RemoteException {

        }
    };


    private void testMessenger() {

        final Messenger client = new Messenger(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e("MessengerClient", "msg-->" + msg.toString());
            }
        });


        bindService(new Intent(this, MessengerService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Messenger messenger = new Messenger(service);


                Message msg = Message.obtain(null, 0);
                Bundle bundle = new Bundle();
                bundle.putString("sen", "testMessenger--->)");
                msg.setData(bundle);
                msg.replyTo = client;
                try {
                    messenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }


    public void toMain2Activity(View view) {

        startActivity(new Intent(this, Main2Activity.class));
    }




}
