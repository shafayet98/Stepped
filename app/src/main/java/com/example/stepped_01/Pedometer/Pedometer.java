package com.example.stepped_01.Pedometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

public class Pedometer implements SensorEventListener {

    public static SensorManager sensorManager;
    public static Sensor sensor;
    public float steps;


    public Pedometer(SensorManager manager) {
        if(sensorManager == null){
            sensorManager = manager;
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        steps = sensorEvent.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void registerSensor(Context context){
        if(checkSensor()){
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(context, "Sensor not found", Toast.LENGTH_SHORT).show();
        }
    }
    public void unRegisterSensor(){
        sensorManager.unregisterListener(this);
    }

    private boolean checkSensor(){
        return sensor == null ? false : true;
    }
}
