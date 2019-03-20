package com.example.stepped_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stepped_01.Util.TimerUtil;

public class TimerActivity extends AppCompatActivity {

    private Button timerStartId;
    private EditText hourId, minId, secId;
    private Button clearTimerId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        init();

        timerStartId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()){
                    Intent intent = new Intent(TimerActivity.this, TimerStartActivity.class);
                    intent.putExtra(TimerUtil.HOUR, Integer.parseInt(hourId.getText().toString()));
                    intent.putExtra(TimerUtil.MINUTE, Integer.parseInt(minId.getText().toString()));
                    intent.putExtra(TimerUtil.SECOND, Integer.parseInt(secId.getText().toString()));
                    startActivity(intent);
                }else{
                    Toast.makeText(TimerActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearTimerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hourId.setText("");
                minId.setText("");
                secId.setText("");
            }
        });
    }

    public void init(){
        timerStartId = findViewById(R.id.timerStartId);
        hourId = findViewById(R.id.hourId);
        minId = findViewById(R.id.minId);
        secId = findViewById(R.id.secId);
        clearTimerId = findViewById(R.id.clearTimerId);
    }

    private boolean validation(){
        if(!hourId.getText().toString().equals("") && !minId.getText().toString().equals("") && !secId.getText().toString().equals("")){
                return true;
        }
        return false;
    }
}
