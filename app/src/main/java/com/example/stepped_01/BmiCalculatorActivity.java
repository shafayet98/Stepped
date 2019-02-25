package com.example.stepped_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BmiCalculatorActivity extends AppCompatActivity {


    private Button calculateBmiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        init();

        calculateBmiId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BmiCalculatorActivity.this, ResultBmiActivity.class));
            }
        });

    }

    public void init(){

        calculateBmiId = findViewById(R.id.calculateBmiId);

    }

}
