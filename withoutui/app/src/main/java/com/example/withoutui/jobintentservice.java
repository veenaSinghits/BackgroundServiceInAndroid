package com.example.withoutui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import java.io.IOException;

public class jobintentservice extends JobIntentService {
    public static final int JOB_ID = 0x01;
    private server varserver;
    private String TAG = "jobintentservice";



    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, jobintentservice.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        // your code
        Log.v(TAG,"inside jobintentservice");
        try {
            varserver = new server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
