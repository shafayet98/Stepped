package com.example.stepped_01.Util;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.hardware.SensorManager;

import com.example.stepped_01.Pedometer.Pedometer;

public class App extends Application {

    public static Pedometer pedometer;

    @Override
    public void onCreate() {
        super.onCreate();
        Notifications.createNotificationChannel(getSystemService(NotificationManager.class));
        pedometer = new Pedometer((SensorManager) getSystemService(Context.SENSOR_SERVICE));
    }
}
