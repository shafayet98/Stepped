package com.example.stepped_01.Bmi;

public class BmiCalculations {
    private Bmi bmi;
    private String bmiNumber;

    public BmiCalculations() {

    }

    public String getBmiNumber() {
        bmiNumber = String.format("%.1f", calculateBmi());
        return bmiNumber;
    }

    public BmiCalculations(Bmi bmi) {
        this.bmi = bmi;
    }

    private double convertToMeter(double height){
        return height / 100;
    }

    public double calculateBmi(){
        return bmi.getWeight() / Math.pow(convertToMeter(bmi.getHeight()), 2);
    }

    public String getResultOnBmi(){
        double value = calculateBmi();
        if(value < 18.5){
            return "Underweight";
        }else if(value >= 18.5 && value <= 24.9){
            return "Normal";
        }else if(value >= 25 && value <= 29.9){
            return "Overweight";
        }else if(value >= 30){
            return "Obese";
        }
        return "";
    }
}
