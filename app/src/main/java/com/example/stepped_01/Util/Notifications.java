package com.example.stepped_01.Util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notifications {

    public static final String NOTIFICATION_CHANNEL_1_ID = "PEDOMETER_NOTIFICATION";
    public static final String NOTIFICATION_CHANNEL_1_NAME = "Stepped";

    public static void createNotificationChannel(NotificationManager notificationManager){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    NOTIFICATION_CHANNEL_1_ID,
                    NOTIFICATION_CHANNEL_1_NAME,
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");
            channel1.setSound(null, null);

            NotificationManager manager = notificationManager;
            manager.createNotificationChannel(channel1);
        }
    }
}
