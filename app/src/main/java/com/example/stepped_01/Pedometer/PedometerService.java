package com.example.stepped_01.Pedometer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.stepped_01.R;
import com.example.stepped_01.ServicesActivity;
import com.example.stepped_01.Util.Notifications;
import com.example.stepped_01.Util.SharedPrefUtility;

public class PedometerService extends Service implements SensorEventListener {

    private SensorManager sensorManager = null;
    private Sensor sensor = null;
    public static Pedometer pedometer = new Pedometer();
    NotificationManagerCompat notificationManagerCompat;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManagerCompat = notificationManagerCompat.from(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegisterSensor();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        registerSensor();

        Intent notificationIntent = new Intent(this, ServicesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        final NotificationCompat.Builder notification = new NotificationCompat.Builder(this, Notifications.NOTIFICATION_CHANNEL_1_ID)
                .setContentTitle("Stepped")
                .setContentText("Steps: " + pedometer.getSteps())
                .setSmallIcon(R.drawable.running)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setProgress(readInitialGoals(), pedometer.getSteps(), false);

        startForeground(1, notification.build());
        notificationManagerCompat.notify(1, notification.build());

        new Thread(new Runnable() {
            @Override
            public void run() {
                notification.setProgress(readInitialGoals(), pedometer.getSteps(), false);
                notificationManagerCompat.notify(1, notification.build());
            }
        });

        return START_STICKY;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        pedometer.setSteps((int)sensorEvent.values[0]);
        saveSteps();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private boolean checkSensor(){
        return sensor == null ? false : true;
    }

    private void registerSensor(){
        if(checkSensor()){
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }else{

        }
    }
    private void unRegisterSensor(){
        sensorManager.unregisterListener(this);
    }

    private void saveSteps(){
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefUtility.SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putInt(SharedPrefUtility.STEPS, pedometer.getSteps());
        editor.apply();
        editor.commit();
    }

    private int readInitialGoals(){
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefUtility.SHARED_PREF, MODE_PRIVATE);
        int goals = sharedPreferences.getInt(SharedPrefUtility.STEP_GOALS, 10000);

        return goals;
    }

    private int loadSteps(){
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefUtility.SHARED_PREF, MODE_PRIVATE);
        int steps = sharedPreferences.getInt(SharedPrefUtility.STEPS, 0);
        return steps;
    }

}
