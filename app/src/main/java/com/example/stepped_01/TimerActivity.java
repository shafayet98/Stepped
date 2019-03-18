package com.example.stepped_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TimerActivity extends AppCompatActivity {

    private Button timerStartId;
    private EditText hourId, minId, secId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        init();

        timerStartId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimerActivity.this, TimerStartActivity.class);
                intent.putExtra("hour", Integer.parseInt(hourId.getText().toString()));
                intent.putExtra("min", Integer.parseInt(minId.getText().toString()));
                intent.putExtra("sec", Integer.parseInt(secId.getText().toString()));
                startActivity(intent);
            }
        });
    }

    public void init(){
        timerStartId = findViewById(R.id.timerStartId);
        hourId = findViewById(R.id.hourId);
        minId = findViewById(R.id.minId);
        secId = findViewById(R.id.secId);
    }

    private void validation(){

    }
}
