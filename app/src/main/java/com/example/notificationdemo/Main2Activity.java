package com.example.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    TextView tvDetail, tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvDetail = findViewById(R.id.tvDetails);
        tvTitle = findViewById(R.id.tvTitle);


//        String title =  getIntent().getStringExtra("title");
//        String content =  getIntent().getStringExtra("content");

      Bundle remoteInput =    RemoteInput.getResultsFromIntent (this.getIntent());
        if (remoteInput!=null)
        {
            tvDetail.setText(remoteInput.getString("Reply_Key"));
        }

        NotificationManager notificationManager =  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel("101",1000);

//        tvTitle.setText(title);
//        tvDetail.setText(content);
    }
}
