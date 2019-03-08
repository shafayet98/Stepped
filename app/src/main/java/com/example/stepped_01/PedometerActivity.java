package com.example.stepped_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    }
}
