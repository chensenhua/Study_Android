package ac692x_case.jieli.com.ipcapplication;

import ac692x_case.jieli.com.ipcapplication.aidl.Book;
import ac692x_case.jieli.com.ipcapplication.aidl.IBookManager;
import ac692x_case.jieli.com.ipcapplication.aidl.User;
import ac692x_case.jieli.com.ipcapplication.binderpool.*;
import ac692x_case.jieli.com.ipcapplication.services.BinderService;
import ac692x_case.jieli.com.ipcapplication.services.UserBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.e("SharedPreferences", PreferencesHelper.getSharedPreferences(this).getString("sen", ""));

        Log.e("main2", "SingleClass-->" + SingleClass.getInstance().toString());
        testBookAidl();
        testBinderPool();
        testBinderService();
    }



    private void testBinderService(){
        bindService(new Intent(this, BinderService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
             //   UserBinder userBinder= (UserBinder) service;
       //  Log.e("testBinderService",userBinder.getUser().toString());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }


    private void testBinderPool() {
        new Thread(){
            @Override
            public void run() {
                ICompute compute =  ComputeImpl.asInterface(BinderPool.getInsance(getApplication()).findBinder(0));
                ISecurityCenter securityCenter =   SecurityCenterImpl.asInterface(BinderPool.getInsance(getApplication()).findBinder(1));
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
                book.setId(1);
                book.setName("book--1");
                try {
                    iBookManager.addBook(book);
                    Log.e("main2", "BookManager.getInstance().getBooks()-->" + iBookManager.getBooks().toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);

        IBookManager iBookManager=IBookManager.Stub.asInterface(MainActivity.bookManager.asBinder());
        try {
            Log.e("main2", "BookManager.getInstance().getBooks()-->" + iBookManager.getBooks().toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

}