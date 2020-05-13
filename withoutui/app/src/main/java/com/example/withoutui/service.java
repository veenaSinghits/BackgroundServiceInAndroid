package com.example.withoutui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.io.IOException;

public class service extends Service {
    private static final String TAG = "MyService";
    private server varserver;
    MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("")
                    .setContentText("").build();

            startForeground(1, notification);
        }
        player = MediaPlayer.create(this, Uri.parse("http://www.dev2qa.com/demo/media/test.mp3"));
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                killMediaPlayer();
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int stratid) {
        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
        try {
            varserver = new server();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onStart");

        return START_STICKY;
    }

    public void onDestroy() {
        player.stop();
        player.release();
        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy");
    }


    public static String add() {
        String name1 = "veena";
        return name1;
    }

    private  void killMediaPlayer() {
        if (player != null) {
            try {
                player.reset();
                player.release();
                player = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
