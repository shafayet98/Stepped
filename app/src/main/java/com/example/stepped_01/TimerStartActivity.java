package com.example.stepped_01;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.stepped_01.Util.TimerUtil;

import java.util.concurrent.TimeUnit;

public class TimerStartActivity extends AppCompatActivity {

    private int hour, min, sec, totalMilli, progress;
    private int finalHour, finalMin, finalSec;
    private boolean isPaused = false;
    private int remaining = 0;
    private int totalMin = 0;
    private long resetHour, resetMin, resetSec;



    private TextView showTimerText;
    private CountDownTimer countDownTimer;
    private Button pauseId;
    private TextView resetId;
    private Button backButtonId;
    private ProgressBar timerProgressBar;

    private static final String FORMAT = "%02d:%02d:%02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_start);
        init();

        countDownTimer = new CountDownTimer(totalMin, 1000) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {
                showTimerText.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                remaining += 1000;
                progress += 1000;
                timerProgressBar.setProgress(progress);
            }

            public void onFinish() {
                showTimerText.setText("done!");
                pauseId.setEnabled(false);
                pauseId.setTextColor(Color.parseColor("#d7ddd9"));
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
                                progress += 1000;
                                timerProgressBar.setProgress(progress);
                            }
                            public void onFinish() {
                                showTimerText.setText("done!");
                                pauseId.setEnabled(false);
                                pauseId.setTextColor(Color.parseColor("#d7ddd9"));
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
                totalMin = totalMilli;
                remaining = 0;
                showTimerText.setText("" + String.format(FORMAT, resetHour, resetMin, resetSec));
                pauseId.setText("Start");
                isPaused = true;
                progress = 0;
                timerProgressBar.setProgress(progress);
                pauseId.setEnabled(true);
                pauseId.setTextColor(Color.parseColor("#e12525"));
            }
        });

        backButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init(){
        showTimerText = findViewById(R.id.showTimerText);
        pauseId = findViewById(R.id.pauseId);
        resetId = findViewById(R.id.resetId);
        backButtonId = findViewById(R.id.backButtonId);
        timerProgressBar = findViewById(R.id.timerProgressBar);

        hour = getIntent().getIntExtra(TimerUtil.HOUR, 0);
        min = getIntent().getIntExtra(TimerUtil.MINUTE, 0);
        sec = getIntent().getIntExtra(TimerUtil.SECOND, 0);

        finalHour = hour * 3600000;
        finalMin = min * 60000;
        finalSec = sec * 1000;
        totalMin = finalHour + finalMin + finalSec;
        totalMilli = totalMin;

        resetHour = TimeUnit.MILLISECONDS.toHours(totalMilli);
        resetMin = TimeUnit.MILLISECONDS.toMinutes(totalMilli) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(totalMilli));
        resetSec = TimeUnit.MILLISECONDS.toSeconds(totalMilli) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(totalMilli));

        timerProgressBar.setMax(totalMilli);
    }
}
