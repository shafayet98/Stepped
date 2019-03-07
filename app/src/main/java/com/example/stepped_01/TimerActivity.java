package com.example.stepped_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TimerActivity extends AppCompatActivity {

    private Button timerStartId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        init();

        timerStartId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TimerActivity.this,TimerStartActivity.class));
            }
        });
    }

    public void init(){
        timerStartId = findViewById(R.id.timerStartId);
    }
}
