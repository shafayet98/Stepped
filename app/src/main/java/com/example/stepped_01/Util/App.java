package com.example.stepped_01.Util;

import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.example.stepped_01.Pedometer.PedometerService;
import com.example.stepped_01.ServicesActivity;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        startService();
        Notifications.createNotificationChannel(getSystemService(NotificationManager.class));
    }

    public void startService() {

        Intent serviceIntent = new Intent(this, PedometerService.class);
        ContextCompat.startForegroundService(this, serviceIntent);

//        startService(serviceIntent);
//        startService(new Intent(this, PedometerService.class));
    }
}
