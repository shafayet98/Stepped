package com.example.stepped_01.Pedometer;

import android.content.SharedPreferences;

import com.example.stepped_01.Util.SharedPrefUtility;

import static android.content.Context.MODE_PRIVATE;

public class Pedometer {
    private int steps;
    private String kilometers;
    private String calorie;
    private String minutes;

    public Pedometer() {
    }

    public Pedometer(int steps) {
        this.steps = steps;
    }

    public Pedometer(int steps, String kilometers, String calorie, String minutes){
        this.steps = steps;
        this.kilometers = kilometers;
        this.calorie = calorie;
        this.minutes = minutes;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void increaseSteps(){
        this.steps++;
    }

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }
}
