package com.sen.study_android.yishutansuo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.*;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import com.sen.study_android.MainActivity;
import com.sen.study_android.R;

public class RemoteViewStudy extends AppCompatActivity {

    private TestReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_view_study);
        IntentFilter filter = new IntentFilter();
        filter.addAction(action);
        filter.addAction(action2);

        myReceiver = new TestReceiver();
        Log.e("sen","registerReceiver");
      registerReceiver(myReceiver, filter);


    }


    public void showNititication(View view) {

        Log.e("sen", "showNititication");

        Intent intent = new Intent(this, RemoteViewStudy.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "test";
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationChannel.setDescription("测试");
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            manager.createNotificationChannel(notificationChannel);
            builder = new Notification.Builder(this, channelId);
        } else {
            builder = new Notification.Builder(this);
        }
        builder.setSmallIcon(R.mipmap.ic_launcher);

        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        builder.setContentText("default nitifuication");
        builder.setContentTitle("tip");
        builder.setWhen(System.currentTimeMillis() + 3000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setColor(Color.RED);
        }


        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setCustomContentView(createRemoteViews());
        } else {
            builder.setContent(createRemoteViews());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setCustomBigContentView(createRemoteViews());
        }


        Notification notification = builder.build();


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(10002, notification);

    }

    private String action = "com.sen.notification";
    private String action2 = "com.sen.notification2";


    private RemoteViews createRemoteViews() {
        Intent intent = new Intent( );
        intent.setAction(action);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.remoteview);
        remoteViews.setTextViewText(R.id.remote_view_text, "test remote view");
        remoteViews.setOnClickPendingIntent(R.id.remote_view_button, pendingIntent);

        return remoteViews;
    }

    @Override
    protected void onDestroy() {
         unregisterReceiver(myReceiver);
        super.onDestroy();
    }

    public void sendBradCase(View view) {
        Intent intent = new Intent( );
        intent.setAction(action2);
        intent.putExtra("sen","test");
        sendBroadcast(intent);
        Log.e("sen", "sendBroadcast");
    }

      class TestReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("sen","TestReceiver-->"+intent.getAction());
        }
    }
}
