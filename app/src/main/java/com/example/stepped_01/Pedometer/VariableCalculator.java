package com.example.stepped_01.Pedometer;

public class VariableCalculator {

    public static String getTotalCalories(int steps){
        return String.valueOf(Math.ceil(steps * 0.05));
    }

    public static String getTotalKilometers(int steps){
        return String.valueOf(((steps * 75)/ 100) / 1000);
    }
}
