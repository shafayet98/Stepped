package com.example.stepped_01;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stepped_01.Util.TImerUtil;

import java.text.Format;
import java.util.concurrent.TimeUnit;

public class TimerStartActivity extends AppCompatActivity {

    private int hour, min, sec, totalmilis;
    private int finalHour, finalMin, finalSec;
    private boolean isPaused = false;
    private int remaining = 0;
    private int totalMin = 0;

    private TextView showTimerText;
    private CountDownTimer countDownTimer;
    private Button pauseId;
    private TextView resetId;
    private Button backButtonId;

    private static final String FORMAT = "%02d:%02d:%02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_start);

        showTimerText = findViewById(R.id.showTimerText);
        pauseId = findViewById(R.id.pauseId);
        resetId = findViewById(R.id.resetId);
        backButtonId = findViewById(R.id.backButtonId);

        hour = getIntent().getIntExtra(TImerUtil.HOUR, 0);
        min = getIntent().getIntExtra(TImerUtil.MINUTE, 0);
        sec = getIntent().getIntExtra(TImerUtil.SECOND, 0);

        finalHour = hour * 3600000;
        finalMin = min * 60000;
        finalSec = sec * 1000;
        totalMin = finalHour + finalMin + finalSec;
        totalmilis = totalMin;

        countDownTimer = new CountDownTimer(totalMin, 1000) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {
                showTimerText.setText("" + String.format(FORMAT,
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

        pauseId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pauseId.getText().toString().equals("Pause")){
                    countDownTimer.cancel();
                    isPaused = true;
                    totalMin = totalMin - remaining;
                    remaining = 0;
                    pauseId.setText("Start");
                    countDownTimer = null;
                    return;
                }

                if(pauseId.getText().toString().equals("Start")){
                    if(isPaused && countDownTimer == null){
                        int r = totalMin - remaining;
                        remaining = 0;
                        pauseId.setText("Pause");
                        countDownTimer = new CountDownTimer(r, 1000) { // adjust the milli seconds here
                            public void onTick(long millisUntilFinished) {
                                showTimerText.setText("" + String.format(FORMAT,
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

        resetId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countDownTimer != null){
                    countDownTimer.cancel();
                }
                countDownTimer = null;
                totalMin = totalmilis;
                remaining = 0;
                showTimerText.setText("" + String.format(FORMAT, hour, min, sec));
                pauseId.setText("Start");
                isPaused = true;
            }
        });

        backButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
