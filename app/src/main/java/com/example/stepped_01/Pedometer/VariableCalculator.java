package com.example.stepped_01.Pedometer;

import java.util.Locale;

public class VariableCalculator {

    public static String getTotalCalories(int steps){
        String str = String.format(Locale.getDefault(),"%.2f", steps * 0.05);
        return str;
    }

    public static String getTotalKilometers(int steps){
        String str = String.format(Locale.getDefault(),"%.2f", ((steps * 55)/ 100) / 1000.0);
        return str;
    }
//    public static String getTotalMinutes(int steps){
//        String str = String.format(Locale.getDefault(),"%.2f", steps * 0.0076923);
//        return str;
//    }
}
