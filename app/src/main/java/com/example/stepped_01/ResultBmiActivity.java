package com.example.stepped_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResultBmiActivity extends AppCompatActivity {

    private Button backToHomeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_bmi);
        init();

        backToHomeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultBmiActivity.this, ServicesActivity.class));
            }
        });
    }

    private void init(){
        backToHomeId = findViewById(R.id.backToHomeId);
    }
}
