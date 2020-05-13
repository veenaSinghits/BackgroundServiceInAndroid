package com.example.withoutui;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class Mainclass extends BroadcastReceiver {

    public void onReceive(Context context, Intent arg1) {
        Log.v(TAG, "in main class");
        //if (Intent.ACTION_BOOT_COMPLETED.equals(arg1.getAction())) {
            //jobintentservice.enqueueWork(context, new Intent());
             Intent intent = new Intent(context,service.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
            Log.v(TAG,"inside if");
             } else {
             context.startService(intent);
             Log.v(TAG,"inside else");
            }

            Log.i("Autostart", "started");
       // }


    }
}
