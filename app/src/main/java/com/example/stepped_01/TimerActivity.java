package com.example.stepped_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stepped_01.Util.TImerUtil;

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
                if(validation()){
                    Intent intent = new Intent(TimerActivity.this, TimerStartActivity.class);
                    intent.putExtra(TImerUtil.HOUR, Integer.parseInt(hourId.getText().toString()));
                    intent.putExtra(TImerUtil.MINUTE, Integer.parseInt(minId.getText().toString()));
                    intent.putExtra(TImerUtil.SECOND, Integer.parseInt(secId.getText().toString()));
                    startActivity(intent);
                }else{
                    Toast.makeText(TimerActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void init(){
        timerStartId = findViewById(R.id.timerStartId);
        hourId = findViewById(R.id.hourId);
        minId = findViewById(R.id.minId);
        secId = findViewById(R.id.secId);
    }

    private boolean validation(){
        if(!hourId.getText().toString().equals("") && !minId.getText().toString().equals("") && !secId.getText().toString().equals("")){
            if(hourId.getText().toString() != null && minId.getText().toString() != null && secId.getText().toString() != null){
                return true;
            }
        }
        return false;
    }
}
