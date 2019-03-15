package com.example.stepped_01.Pedometer;

public class Pedometer {
    private int steps;
    private double kilometers;
    private double calorie;

    public Pedometer() {
    }

    public Pedometer(int steps) {
        this.steps = steps;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public double getTotalkilometers() {
        return kilometers;
    }

    public void setTkilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public void increaseSteps(){
        this.steps++;
    }
}
