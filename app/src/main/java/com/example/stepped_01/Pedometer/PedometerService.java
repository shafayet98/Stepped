package com.example.stepped_01.Pedometer;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import com.example.stepped_01.PedometerActivity;
import com.example.stepped_01.R;
import com.example.stepped_01.ServicesActivity;
import com.example.stepped_01.Util.Notifications;
import com.example.stepped_01.Util.SharedPrefUtility;

import java.lang.ref.WeakReference;

public class PedometerService extends Service implements SensorEventListener {

    private SensorManager sensorManager = null;
    private Sensor sensor = null;
    public static Pedometer pedometer = new Pedometer();
    NotificationManagerCompat notificationManagerCompat;

    final NotificationCompat.Builder notification = new NotificationCompat.Builder(this, Notifications.NOTIFICATION_CHANNEL_1_ID);
    //SensorEventNotificationTask task = new SensorEventNotificationTask();

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1, notification.build());
        }
        pedometer = new Pedometer(loadSteps());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true); //true will remove notification
        }
        unRegisterSensor();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_UI);
        registerSensor();

        notificationManagerCompat = notificationManagerCompat.from(this);
        Intent notificationIntent = new Intent(this, ServicesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        notification.setContentTitle("Stepped")
                .setContentText("Steps: " + pedometer.getSteps())
                .setSmallIcon(R.drawable.running)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setProgress(readInitialGoals(), pedometer.getSteps(), false)
                .setDefaults(0);


        startForeground(1, notification.build());
        notificationManagerCompat.notify(1, notification.build());
        return START_NOT_STICKY;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        pedometer.increaseSteps();
        notification.setProgress(readInitialGoals(), pedometer.getSteps(), false);
        notification.setContentText("Steps: " + pedometer.getSteps());
        notificationManagerCompat.notify(1, notification.build());
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

//    public static class SensorEventLoggerTask extends
//            AsyncTask<SensorEvent, Void, Void> {
//
//        WeakReference<PedometerActivity> pedometerActivityWeakReference;
//
//        public SensorEventLoggerTask(PedometerActivity activity) {
//        this.pedometerActivityWeakReference = new WeakReference<>(activity);
//    }
//
//    @Override
//    protected Void doInBackground(SensorEvent... events) {
//
//        pedometer.increaseSteps();
//        return null;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//        PedometerActivity activity = pedometerActivityWeakReference.get();
//
//        activity.pedometerProgressBar.setProgress(pedometer.getSteps());
//        activity.stepsTextView.setText(pedometer.getSteps());
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//    }
//}

//    public class SensorEventNotificationTask extends
//            AsyncTask<Integer, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Integer... events) {
//            //notificationManagerCompat.notify(1, notification.build());
//            return null;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            //notificationManagerCompat.notify(1, notification.build());
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//            notification.setProgress(readInitialGoals(), pedometer.getSteps(), false);
//            notification.setContentText("Steps: " + pedometer.getSteps());
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            notificationManagerCompat.notify(1, notification.build());
//        }
//    }

}
