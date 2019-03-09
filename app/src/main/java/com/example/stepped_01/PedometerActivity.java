package com.example.stepped_01;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stepped_01.Util.SharedPrefUtility;

public class PedometerActivity extends AppCompatActivity {

    private ProgressBar pedometerProgressBar;
    private TextView stepsTextView;
    private TextView kilometerTextView;
    private TextView minutesTextView;
    private TextView totalCalorieTextView;
    private Button setGoalsButton;
    private TextView goalsTextView;
    private Button weeklyProgressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        init();
        setInitialGoals();

        loadSteps();

        setGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertUserForStepGoals();
            }
        });
    }


    private void alertUserForStepGoals(){
        final View view = getLayoutInflater().inflate(R.layout.custom_alert_dialog, null);
        final EditText stepsAlertTextView = view.findViewById(R.id.stepsAlertTextView);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(PedometerActivity.this);
        builder1.setTitle("Set your step goals: ");
        builder1.setMessage("The more you give the better");

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        goalsTextView.setText(stepsAlertTextView.getText().toString());
                        saveStepGoals();
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
        alert11.setView(view);
        alert11.show();
    }



    private void init(){
        pedometerProgressBar = findViewById(R.id.pedometerProgressBar);
        stepsTextView = findViewById(R.id.stepsTextView);
        kilometerTextView = findViewById(R.id.kilometerTextView);
        minutesTextView = findViewById(R.id.kilometerTextView);
        totalCalorieTextView = findViewById(R.id.totalCalorieTextView);
        setGoalsButton = findViewById(R.id.setGoalsButton);
        goalsTextView = findViewById(R.id.goalsTextView);
        weeklyProgressButton = findViewById(R.id.weeklyProgressButton);

        pedometerProgressBar.setMax(10000);
    }

    private void saveStepGoals() {
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefUtility.SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(SharedPrefUtility.STEP_GOALS, (int) Double.parseDouble(goalsTextView.getText().toString()));

        editor.commit();
        editor.apply();
    }

    private void setInitialGoals(){
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefUtility.SHARED_PREF, MODE_PRIVATE);
        int goals = sharedPreferences.getInt(SharedPrefUtility.STEP_GOALS, 10000);
        pedometerProgressBar.setMax(goals);
        goalsTextView.setText(String.valueOf(goals));
    }

    private void loadSteps(){
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefUtility.SHARED_PREF, MODE_PRIVATE);
        int steps = sharedPreferences.getInt(SharedPrefUtility.STEPS, 0);
        pedometerProgressBar.setProgress(steps);
        stepsTextView.setText(String.valueOf(steps));
    }
}
