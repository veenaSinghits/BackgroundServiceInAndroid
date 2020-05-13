package com.example.withoutui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;
import static android.content.Intent.ACTION_BOOT_COMPLETED;

public class hello extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_hello);
        Toast.makeText(getBaseContext(), "Hello........", Toast.LENGTH_LONG).show();
        Log.v(TAG,"entered");
//        Intent serviceIntent = new Intent(getApplication(),service.class);
//        startService(serviceIntent);
        Intent intent = new Intent(getApplication(),Mainclass.class);
        sendBroadcast(intent);
    }
}
