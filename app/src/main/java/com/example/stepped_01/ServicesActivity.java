package com.example.stepped_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class ServicesActivity extends AppCompatActivity {

    private CardView pedometerId;
    private CardView bmiId;
    private CardView timerId;
    private CardView alarmId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        init();

        bmiId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServicesActivity.this, BmiCalculatorActivity.class));
            }
        });
    }

    private void init(){
        pedometerId = findViewById(R.id.pedometerId);
        bmiId = findViewById(R.id.bmiId);
        timerId = findViewById(R.id.timerId);
        alarmId =findViewById(R.id.alarmId);
    }
}
