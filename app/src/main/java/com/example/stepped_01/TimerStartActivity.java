package com.example.stepped_01;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class TimerStartActivity extends AppCompatActivity {

    int hour, min, sec;
    int finalHour, finalMin, finalSec;
    private TextView showTimerText;
    private CountDownTimer countDownTimer;
    private boolean isPaused = false;
    int remaining = 0;
    int totalMil = 0;
    private Button pauseId;
    private static final String FORMAT = "%02d:%02d:%02d";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_start);

        showTimerText = findViewById(R.id.showTimerText);
        pauseId = findViewById(R.id.pauseId);

        int hour = getIntent().getIntExtra("hour", 0);
        int min = getIntent().getIntExtra("min", 0);
        int sec = getIntent().getIntExtra("sec", 0);


        finalHour = hour*3600000;
        finalMin = min*60000;
        finalSec = sec*1000;

        totalMil = finalHour+finalMin+finalSec;

        countDownTimer = new CountDownTimer(totalMil, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                showTimerText.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                remaining += 1000;
            }

            public void onFinish() {
                showTimerText.setText("done!");
            }
        }.start();



//        Toast.makeText(getApplicationContext(), hour + " " + min + " "+ sec + "", Toast.LENGTH_SHORT).show();

        pauseId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countDownTimer != null && pauseId.getText().toString().equals("Pause")){
                    countDownTimer.cancel();
                    isPaused = true;
                    totalMil = totalMil - remaining;
                    remaining = 0;
                    pauseId.setText("Start");
                    countDownTimer = null;
                    return;
                }

                if(pauseId.getText().toString().equals("Start")){
                    if(isPaused && countDownTimer == null){
                        int r = totalMil - remaining;
                        remaining = 0;
                        pauseId.setText("Pause");
                        countDownTimer = new CountDownTimer(r, 1000) { // adjust the milli seconds here

                            public void onTick(long millisUntilFinished) {

                                showTimerText.setText(""+String.format(FORMAT,
                                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                                remaining += 1000;
                            }

                            public void onFinish() {
                                showTimerText.setText("done!");
                            }
                        }.start();
                    }
                }
            }
        });




    }
}
