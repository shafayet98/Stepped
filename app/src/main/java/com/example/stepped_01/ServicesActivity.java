package com.example.stepped_01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class ServicesActivity extends AppCompatActivity {

    private CardView pedometerId;
    private CardView bmiId;
    private CardView timerId;
    private CardView alarmId;

    private static final String SHARED_PREF = "SHARED_PREF";
    private static final String BMI = "BMI";
    private static final String RESULT = "RESULT";

    private String bmi, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        init();
        getBmi();

        bmiId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bmi.equals("")){
                    alertUserForBmi();
                }else{
                    goToBmiActivity();
                }
            }
        });
    }

    private void init(){
        pedometerId = findViewById(R.id.pedometerId);
        bmiId = findViewById(R.id.bmiId);
        timerId = findViewById(R.id.timerId);
        alarmId =findViewById(R.id.alarmId);
    }

    private void getBmi(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        bmi = sharedPreferences.getString(BMI, "");
        result = sharedPreferences.getString(RESULT, "");
    }

    private void alertUserForBmi(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ServicesActivity.this);
        builder1.setTitle("Your previous Bmi was: " + bmi);
        builder1.setMessage("would you like to calculate again?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       goToBmiActivity();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void goToBmiActivity(){
        startActivity(new Intent(ServicesActivity.this, BmiCalculatorActivity.class));
    }
}
