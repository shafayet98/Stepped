package com.example.stepped_01;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultBmiActivity extends AppCompatActivity {

    private Button backToHomeId;
    private TextView resultBmiId;
    private TextView bmiId;

    private String bmiValue;
    private String resultValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_bmi);
        init();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            bmiValue = extras.getString("Bmi");
            resultValue = extras.getString("Result");
        }

        bmiId.setText(bmiValue);
        resultBmiId.setText(resultValue);
        resultBmiId.setTextColor(checkBmiResult());

        backToHomeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultBmiActivity.this, ServicesActivity.class));
            }
        });
    }

    private void init(){
        backToHomeId = findViewById(R.id.backToHomeId);
        resultBmiId = findViewById(R.id.resultBmiId);
        bmiId = findViewById(R.id.bmiId);
    }

    private int checkBmiResult(){
        switch(resultValue){
            case "Underweight":
                return Color.RED;
            case "Normal":
                return Color.GREEN;
            case "Overweight":
                return Color.rgb(255, 165, 0);
            case "Obese":
                return Color.RED;
        }
        return 1;
    }
}
