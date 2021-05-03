package com.example.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private static final String CHANNEL_ID = "MY_CHANNEL";
    public static final String  CHANNRL_NAME= "AJ_NOTIFICATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateMyChannel();
    }

    public void ShowNotification(View view) {

        String title = "COVID-19 Info";
        String content ="Please staty at home, COVID-19 is outside. Please staty at home, COVID-19 is outside.Please staty at home, COVID-19 is outside. Please staty at home, COVID-19 is outside. Before you can deliver the notification on Android 8.0 and higher, you must register your app's notification channel with the system by passing an instance of NotificationChannel to createNotificationChannel().";

        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

        //notification created
        NotificationCompat.Builder builder =  new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // to show notification
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify("101",1,builder.build());
    }

    private  void CreateMyChannel()
    {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    CHANNRL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    public void ShowReplyNotification(View view) {

        String title = "COVID-19 Info";
        String content ="Please staty at home, COVID-19 is outside. Please staty at home, COVID-19 is outside.Please staty at home, COVID-19 is outside. Please staty at home, COVID-19 is outside. Before you can deliver the notification on Android 8.0 and higher, you must register your app's notification channel with the system by passing an instance of NotificationChannel to createNotificationChannel().";

        Intent intent = new Intent(this,Main2Activity.class);

        PendingIntent replyPendingIntent = PendingIntent.getActivity(this,0,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteInput remoteInput = new RemoteInput.Builder("Reply_Key")
                .setLabel("Reply Here Please.")
                .build();

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                R.drawable.reply_icon,
                "Reply Me",
                replyPendingIntent)
                .addRemoteInput(remoteInput)
                .build();


        //notification created
        NotificationCompat.Builder builder =  new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(title)
                .setContentText(content)
                .addAction(action)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // to show notification
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify("101",1000,builder.build());

    }
}
