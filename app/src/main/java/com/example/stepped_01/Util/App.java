package com.example.stepped_01.Util;

import android.app.Application;
import android.app.NotificationManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Notifications.createNotificationChannel(getSystemService(NotificationManager.class));
    }
}
